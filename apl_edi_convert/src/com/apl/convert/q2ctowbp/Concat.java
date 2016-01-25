package com.apl.convert.q2ctowbp;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.apl.convert.configuration.ConvertConfiguration;
import com.apl.convert.exception.DataException;

import net.sf.json.JSONObject;

/**
 * Convert 拼接类
 * 
 */
public class Concat {
	ConvertConfiguration con;
	String head;
	String center;
	StringBuffer content;
	List<Result> results;
	JSONObject resultJson;
	int curRow;

	public Concat(List<Result> results, ConvertConfiguration con) {
		this.content = new StringBuffer();
		this.head = "";
		this.center = "";
		this.results = results;
		this.con = con;
		this.curRow = 1;
	}

	/**
	 * 拼接主体方法
	 * 
	 * @return 生成的报文文件内容
	 */
	public String concating() {
		// loop Results
		for (int i = 0; i < results.size(); i++) {
			// convert Result to JSONObject
			resultJson = JSONObject.fromObject(results.get(i));
			if (i == 0)
				head = concatHeader();
			center = center.concat(concatCenter(results.get(i)));
		}
		content = new StringBuffer().append(head + center);
		concatFoot();
		return content.toString().trim();
	}

	/**
	 * 拼接Header部分内容
	 * 
	 * @return Header内容
	 */
	private String concatHeader() {
		// getHeadInfo
		String[] headProp = con.getHead().split(";");
		for (int i = 0; i < headProp.length; i++) {
			// getRowsInfo
			String options[] = headProp[i].split("-");
			concatRows(options);
		}
		String headTemp = content.toString();
		return headTemp;
	}

	/**
	 * concat the center
	 * 
	 * @param result
	 *            已处理好的XML结果对象
	 * @return Center内容
	 */
	public String concatCenter(Result result) {
		content = new StringBuffer();
		// get info of row
		String[] centerProp = con.getCenter().split(";");
		for (int i = 0; i < centerProp.length; i++) {
			String options[] = centerProp[i].split("-");
			// judge the necessity of row
			if (isEssentialRow(options, result)) {
				concatRows(options);
			}
			curRow = 1;
		}
		return content.toString();
	}

	/**
	 * concat the foot
	 */
	public void concatFoot() {
		String[] footProp = con.getFoot().split(";");
		for (int i = 0; i < footProp.length; i++) {
			String options[] = footProp[i].split("-");
			concatRows(options);
		}
	}

	/**
	 * judge the necessity of row
	 * 
	 * @param option
	 *            行属性数组
	 * @param result
	 *            已处理好的XML结果对象
	 * @return 是否必填行
	 */
	public boolean isEssentialRow(String[] option, Result result) {
		// 如果是非必填，则检查该行下的必填列是否有值，如果没有值，则该行不显示
		if ("C".equals(option[2])) {
			if ("".equals(getEssentialValue(option[3]))) {
				return false;
			}
		}
		if ("D".equals(option[2])) {
			if ("concat$48".equals(option[3])) {
				concat$48(result, option);
			}
			return false;
		}
		return true;
	}

	/**
	 * concat row function
	 * 
	 * @param options
	 *            行属性数组
	 */
	private void concatRows(String[] options) {
		StringBuffer rowContent = new StringBuffer();
		String RECORD_ID = options[0];
		// default number of columns
		int length = Integer.valueOf(options[1]);
		for (int j = 0; j < length; j++) {
			// get rule of column
			String[] colRegs = con.getMappingKey(RECORD_ID + "-" + (j + 1)).split("/");
			// get content of column
			String colContent = getContent(rowContent.toString(), colRegs, options);
			rowContent.append(colContent);
			// if it isnot the last column then combine with ":"
			if (j != length - 1) {
				rowContent.append(":");
			}
		}
		rowContent.append("'");
		content.append(rowContent.toString());
	}

	/**
	 * get content of column
	 * 
	 * @param rowContent
	 *            当前行已拼接好的内容
	 * @param colInfo
	 *            列属性数组
	 * @param options
	 *            行属性数组
	 * @return 行内容
	 */
	public String getContent(String rowContent, String[] colInfo, String[] options) {
		String colContent = "";
		if ("C".equals(colInfo[0])) {
			// if the identifying of the column is "C" then the column is
			// constant
			colContent = colInfo[1].equals("%NULL%") ? "" : colInfo[1];
			// "P" is variable
		} else if ("P".equals(colInfo[0])) {
			// the max char number of a column
			int maxChar = Integer.valueOf(colInfo[2]);
			// the max recycable number of the columns
			int maxCol = Integer.valueOf(colInfo[3]);
			// get value of row
			colContent = getEssentialValue(colInfo[1]);
			// if the max recycable number is more than 1 then concat
			// sequentially
			// else substring by the max char number
			if (maxCol > 1) {
				colContent = concatCol(rowContent, colContent, colInfo, options);
			} else {
				colContent = colContent.substring(0, colContent.length() > maxChar ? maxChar : colContent.length());
				// 如果截取后的Content的最后一位为'?'并且倒数第二个字符不为'?'，则要将最后的'?'截取掉
				// 如果最后一个字符不为'?'或者最后两个字符都为'?'则不做更改
				colContent = '?' == colContent.charAt(colContent.length() - 1)
						? ('?' == colContent.charAt(colContent.length() - 2) ? colContent
								: colContent.substring(0, colContent.length() - 1))
						: colContent;
			}
		} else if ("COUNT".equals(colInfo[0])) {
			colContent = "" + (content.toString().split("(?<=[^?]|[?][?])'").length);
		} else if ("Z".equals(colInfo[0])) {
			colContent = con.getMappingKey(getEssentialValue(colInfo[1]));
		}
		return colContent;
	}

	/**
	 * get essential value of the "C" row
	 * 
	 * @param name
	 *            非必填行中关键列的名称
	 * @return 关键列的值
	 */
	public String getEssentialValue(String name) {
		return resultJson.getString(name);
	}

	/**
	 * concat column function
	 * 
	 * @param rowContent
	 *            当前行已拼接好的内容
	 * @param colContent
	 *            当前列内容
	 * @param colInfo
	 *            列属性数组
	 * @param options
	 *            行属性数组
	 * @return 已拼接处理好的列值
	 */
	public String concatCol(String rowContent, String colContent, String[] colInfo, String[] options) {
		StringBuffer sb = new StringBuffer();
		// convert content to byte array
		char[] charResult = colContent.toCharArray();
		// the max number of row
		int maxRow = Integer.valueOf(options[options.length - 1]);
		// 每列的最大字节数
		int maxChar = Integer.valueOf(colInfo[2]);
		// 每列的最大循环数
		int maxCol = Integer.valueOf(colInfo[3]);
		// 当前循环行数
		int curCol = 1;
		// if i-- then temp++
		int temp = 0;
		for (int i = 0; i < charResult.length; i++) {
			sb.append(charResult[i]);
			if ((i + 1 + temp) % maxChar == 0) {//当前列的字符数是否填满
				curCol++;
				if (charResult[i] == '?' && charResult[i - 1] != '?') {//当前列的最后一位如果是问号并且倒数第二位不是问号做截断操作
					sb.deleteCharAt(sb.length() - 1);//删除拼接的最后一位字符
					i--;//字符数组下标回退一位
					temp++;//记录回退数
				}
				//当前列的已处理数是否大于该列的最大可循环列数
				if (curCol > maxCol) {
					//当前已处理的行数是否小于该行的最大可循环行数
					if (curRow < maxRow) {
						//如果是的话则拼接引号进入该行的下一次循环
						sb.append("'");
						sb.append(rowContent);//拷贝上一行的公共参数
						curCol = 1;
						curRow++;
						//继续进行余下字符的拼接
						continue;
					} else {
						// 如果当前已处理行数超过了该行的最大可循环行数则退出循环，余下的字符不做处理
						break;
					}
				}
				sb.append(":");
			}
		}
		return sb.toString();
	}

	/**
	 * concat "48" row
	 * 
	 * @param result
	 * @param options
	 */
	public void concat$48(Result result, String[] options) {
		Map<String, Integer> ct = result.getCtnCounts();
		Iterator<String> itera = ct.keySet().iterator();
		while (itera.hasNext()) {
			String equipment = itera.next();
			result.setCurrentCtn(equipment);
			result.setCurrentCtnQty(ct.get(equipment));
			resultJson = JSONObject.fromObject(result);
			concatRows("48-9-M-99".split("-"));
		}
	}

}
