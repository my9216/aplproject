package com.notification.generate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.notification.entity.Email;
import com.notification.service.EmailService;
import com.notification.service.ScanService;
import com.notification.util.FileUtil;
import com.notification.util.SimpleMailSender;

import net.sf.json.JSONObject;

public class BaseGenerate {
	@Resource(name = "emailService")
	private EmailService emailService;

	@Resource(name = "scanService")
	private ScanService scanService;

	@Value("#{propertiesBase}")
	Properties prop;

	protected void Generate(Map<String, String> generate) {
		List<Map<String, String>> list;
		try {
			//获取扫描邮件区间
			Map<String, String> mailTime = scanService.getMailTime(generate.get("mailType"));
			//主键
			String id = mailTime.get("ID");
			//开始时间
			String begin_time = mailTime.get("BEGIN_TIME");
			//结束时间
			String end_time = mailTime.get("END_TIME");
			mailTime.clear();
			mailTime.put("BEGIN_TIME", end_time);
			mailTime.put("MAIL_TYPE", generate.get("mailType"));
			// 修改扫描时间为最近的系统时间
			scanService.addMailTime(mailTime);
			mailTime.clear();
			mailTime.put("begin_time", begin_time);
			mailTime.put("end_time", end_time);
			//String sql = FileUtil.readFileByLine("sql/" + generate.get("file"));
			//sql = sql.replaceAll("begin_time", begin_time);
			//sql = sql.replaceAll("end_time", end_time);
			list = scanService.getGenerateList(generate.get("sqlname"),mailTime);
			Map<String, String> updataCount = new HashMap<String, String>();
			updataCount.put("ID", id);
			//记录本次扫描结束时间
			updataCount.put("END_TIME", end_time);
			//记录本次扫描的结果数
			updataCount.put("DATACOUNT", String.valueOf(list.size()));
			scanService.updateDatacount(updataCount);
			MailTemplate temp = new MailTemplate();
			for (int i = 0; i < list.size(); i++) {
				Email email = new Email();
				JSONObject json = JSONObject.fromObject(list.get(i));
				String address = "";
				String content = "";
				if ("Amend".equals(generate.get("mailType"))) {//针对Amend邮件生成规则
					//得到邮件发送对象
					address = prop.getProperty("address_amend");
					// 得到邮件正文内容
					content = temp.generateMail(prop, json, prop.getProperty(generate.get("template")));
				} else if ("error".equals(generate.get("mailType"))) {//针对Error邮件生成规则
					JSONObject obj2 = new JSONObject();
					//按照字符#@拆分字段拼接邮件内容
					String[] ERROR_DESC = json.getString("ERROR_DESC").split("#@");
					obj2.put("BPCODE", ERROR_DESC[0]);
					obj2.put("REFERENCENO", ERROR_DESC[1]);
					obj2.put("VESSEL", ERROR_DESC[2]);
					obj2.put("VOYAGE", ERROR_DESC[3]);
					obj2.put("BLNO", ERROR_DESC[4]);
					obj2.put("ERRORMESSAGE", ERROR_DESC[5]);
					// 得到邮件正文内容
					content = temp.generateMail(prop, obj2, prop.getProperty(generate.get("template")));
					address = json.getString("COMMUNICATION");
				} else {//其他邮件生成规则
					address = json.getString("COMMUNICATION");
					// 得到邮件正文内容
					content = temp.generateMail(prop, json, prop.getProperty(generate.get("template")));
				}
				address = prop.getProperty("address_amend");
				//得到邮件主题
				String subject = prop.getProperty(generate.get("subject"));
				String state = "error";
				String checkmail = SimpleMailSender.validateMail(address);
				String sendinfo = "Invalid address";
				if (!"".equals(checkmail)) {// 判断地址是否合法
					state = "wait";
					sendinfo = "";
					address = checkmail;
				}
				email.setSendinfo(sendinfo);
				email.setAddress(address);
				email.setContent(content);
				email.setStatus(state);
				email.setTitle(subject);
				email.setMail_type(generate.get("mailType"));
				//添加待发送邮件
				emailService.addEmail(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
