package com.apl.sipg.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.apl.network.TransferInterface;
import com.apl.sipg.form.Form;
import com.apl.sipg.util.FileUtil;
import com.apl.util.Generatexml;
import com.apl.util.Logger;
import com.bean.message.MessageBean;

import net.sf.json.JSONObject;

/*
 * 处理扫描操作
 */
public class ScanService {
	private ArrayList<String> headlist;
	private ArrayList<String> contentlist;
	private ArrayList<String> footlist;
	Logger logger = Logger.getLogger(ScanService.class);
	public void execute() throws Exception {
		Form form = new Form();
		File[] filelist = FileUtil.tolistFiles(form.getInputPath());
		for (File file : filelist) {//遍历input中所有文件
			try {
				FileUtil.fileChannelCopy(file.getPath(), form.getBackupPath() + file.getName());//将文件进行备份
				readFileByLines(file.toString());//按行读取文件
				FileUtil.delFile(file.getPath());//删除inut文件夹中的文件
				/* 拼接文件操作 */
				Map<String, Map<String, String>> head = resolveheadorfoot(headlist);
				ArrayList<JSONObject> content = resolvecontent(contentlist);
				Map<String, Map<String, String>> foot = resolveheadorfoot(footlist);

				Generatexml gen = new Generatexml();
				ArrayList<String> serialnum = new ArrayList<String>();// 流水号
				ArrayList<String> ctn = new ArrayList<String>();// 箱号
				ArrayList<String> bl = new ArrayList<String>();// 提单号
				ArrayList<String> vessel = new ArrayList<String>();//船号
				ArrayList<String> voyage = new ArrayList<String>();//航号
				ArrayList<String> status = new ArrayList<String>();// 状态
				for (int i = 0; i < content.size(); i++) {
					ArrayList<String> str = new ArrayList<String>();
					try {
						str = gen.createxml(head, content.get(i), foot);//生成放箱的xml
						if (str != null) {
							String xml = str.get(0);
							serialnum.add(str.get(1));
							bl.add(str.get(2));
							ctn.add(str.get(3));
							vessel.add(str.get(4));
							voyage.add(str.get(5));
							status.add(TransferInterface.postXMLtoSipg(xml, "CTNREL"));//进行放箱操作并获取状态
						}
					} catch (ParserConfigurationException e) {
						logger.error(e.getMessage());
						return;
					} catch (TransformerException e) {
						logger.error(e.getMessage());
						return;
					}
				}
				MessageBean bean = new MessageBean();
				try {
					bean.insert(serialnum, bl, ctn, head, content, foot, status, vessel, voyage);//插入放箱数据
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				FileUtil.fileChannelCopy(form.getBackupPath() + file.getName(), form.getErrorPath() + file.getName());//将文件放入错误文件夹
				FileUtil.delFile(form.getBackupPath() + file.getName());//删除备份文件夹的文件
			}
		}
	}
	
	/*
	 * 按行读取文件
	 */
	public void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			headlist = new ArrayList<String>();
			contentlist = new ArrayList<String>();
			footlist = new ArrayList<String>();
			String tempString = null;
			for (int i = 0; i < 3; i++) {
				tempString = reader.readLine();
				headlist.add(tempString);
			}
			while ((tempString = reader.readLine()) != null) {
				String line = tempString.substring(0, 2);
				if (line.equals("99")) {
					footlist.add(tempString);
					break;
				}
				contentlist.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					logger.error(e1.getMessage());
				}
			}
		}
	}

	public Map<String, Map<String, String>> resolveheadorfoot(ArrayList<String> headlistorfoot) {
		Map<String, Map<String, String>> resolveheadmap = new LinkedHashMap<String, Map<String, String>>();
		for (int i = 0; i < headlistorfoot.size(); i++) {
			Map<String, String> mapinfo = new LinkedHashMap<String, String>();
			String value = headlistorfoot.get(i);
			String line = value.substring(0, 2);
			String[] values = value.split("(?<=[^?]|[?][?]):");
			for (int j = 0; j < values.length; j++) {
				DecimalFormat df = new DecimalFormat("00");
				mapinfo.put(df.format(j), values[j]);
			}
			resolveheadmap.put(line, mapinfo);
		}
		return resolveheadmap;
	}

	/*
	 * 拆分文件
	 */
	public ArrayList<JSONObject> resolvecontent(ArrayList<String> contentlist) {
		ArrayList<JSONObject> resolvecontentmaplists = new ArrayList<JSONObject>();
		Map<String, Map<String, String>> resolvecontentmap = new LinkedHashMap<String, Map<String, String>>();

		for (int i = 0; i < contentlist.size(); i++) {
			Map<String, String> mapinfo = new LinkedHashMap<String, String>();
			String value = contentlist.get(i);
			String line = value.substring(0, 2);
			String[] values = value.split("(?<=[^?]|[?][?]):");
			if ("12".equals(line)) {
				resolvecontentmap = new LinkedHashMap<String, Map<String, String>>();
			}
			for (int j = 0; j < values.length; j++) {
				DecimalFormat df = new DecimalFormat("00");
				mapinfo.put(df.format(j), values[j]);
			}
			resolvecontentmap.put(line, mapinfo);
			if ("51".equals(line)) {
				JSONObject jsonobject = JSONObject.fromObject(resolvecontentmap);
				resolvecontentmaplists.add(jsonobject);
			}
		}
		return resolvecontentmaplists;
	}
}
