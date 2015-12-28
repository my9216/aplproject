package com.apl.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import net.sf.json.JSONObject;


public class Generatexml {
	public final String RECORD_ID = "00";
	public final String MESSAGE_CTNREL = "CTNREL";
	public final String MESSAGE_CANCELREL = "CANCELREL";
	public final String SENDER_CODE = "BWC000019";
	public final String RECEIVER_CODE = "SIPG";
	//public final String[] FILE_CREATE_TIME = {"00","06"};//当前时间
	public final String RECORD_ID2 = "CTNREL";//
	public final String TERMINAL = "";
	public final String[] VESSEL = {"10","02"};
	public final String[] VOYAGE = {"10","04"};
	//public final String SERIALNUM = {"12","02"};//SENDER_CODE+箱号+当前时间到毫秒
	public final String[] BL_NO = {"12","01"};
	public final String[] CTN_NO = {"51","02"};
	public final String[] CTN_STATUS = {"51","05"};
	
	/**
	 * 拼接新的放箱xml
	 * @param head
	 * @param jsonObject
	 * @param foot
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public ArrayList<String> createxml(Map<String, Map<String, String>> head, JSONObject jsonObject, Map<String, Map<String, String>> foot) throws ParserConfigurationException, TransformerException {
		ArrayList<String> str = new ArrayList<String>();
		String ctnStatus = jsonObject.getJSONObject(CTN_STATUS[0]).get(CTN_STATUS[1]).toString();
		if("E".equals(ctnStatus)){
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		String FILE_CREATE_TIME = df.format(new Date());// new Date()为获取当前系统时间
		String num = jsonObject.getJSONObject(CTN_NO[0]).get(CTN_NO[1]).toString();
		String SERIALNUM = SENDER_CODE + num + FILE_CREATE_TIME ;//SENDER_CODE+箱号+当前时间到毫秒
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("root");//根元素
		doc.appendChild(root); // 将根元素添加到文档上
		Element label = null;
		Element sublabel = null;
		Element minlabel = null;
		label = doc.createElement("i");
		label.setAttribute("f", "RECORD_ID");
		label.setAttribute("v", RECORD_ID);
		root.appendChild(label);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "MESSAGE_TYPE");
		sublabel.setAttribute("v", MESSAGE_CTNREL);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "SENDER_CODE");
		sublabel.setAttribute("v", SENDER_CODE);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "RECEIVER_CODE");
		sublabel.setAttribute("v", RECEIVER_CODE);
		label.appendChild(sublabel);

		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "FILE_CREATE_TIME");
		sublabel.setAttribute("v", FILE_CREATE_TIME);
		label.appendChild(sublabel);
		

		label = doc.createElement("i");
		label.setAttribute("f", "RECORD_ID");
		label.setAttribute("v", RECORD_ID2);
		root.appendChild(label);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "TERMINAL");
		sublabel.setAttribute("v", TERMINAL);
		label.appendChild(sublabel);
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "VESSEL");
		sublabel.setAttribute("v", head.get(VESSEL[0]).get(VESSEL[1]));
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "VOYAGE");
		sublabel.setAttribute("v", head.get(VOYAGE[0]).get(VOYAGE[1]));
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "SERIALNUM");
		sublabel.setAttribute("v", SERIALNUM);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "BL_NO");
		sublabel.setAttribute("v", jsonObject.getJSONObject(BL_NO[0]).get(BL_NO[1]).toString());
		
		minlabel = doc.createElement("i");
		minlabel.setAttribute("f", "CTN_NO");
		minlabel.setAttribute("v", num);
		
		sublabel.appendChild(minlabel);
		label.appendChild(sublabel);
		
		
		TransformerFactory tFact = TransformerFactory.newInstance();  
		Transformer trans = tFact.newTransformer();  
		trans.setOutputProperty(OutputKeys.INDENT, "yes");//元素是否换行
		StringWriter writer = new StringWriter();  
		StreamResult result = new StreamResult(writer);  
		DOMSource source = new DOMSource(doc);  
		trans.transform(source, result);
		str.add(writer.toString());
		str.add(SERIALNUM);
		str.add(jsonObject.getJSONObject(BL_NO[0]).get(BL_NO[1]).toString());//BL_NO
		str.add(num);//ctn_NO
		str.add(head.get(VESSEL[0]).get(VESSEL[1]));
		str.add(head.get(VOYAGE[0]).get(VOYAGE[1]));
		return str;
	}

	/**
	 * 生成放箱xml
	 * @param head
	 * @param jsonObject
	 * @param foot
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public String createxml(String serialnum, String ctn,String bl,String vessel,String voyage) throws ParserConfigurationException, TransformerException {
		String str = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		String FILE_CREATE_TIME = df.format(new Date());// new Date()为获取当前系统时间
		String SERIALNUM = SENDER_CODE + ctn + FILE_CREATE_TIME ;//SENDER_CODE+箱号+当前时间到毫秒
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("root");//根元素
		doc.appendChild(root); // 将根元素添加到文档上
		Element label = null;
		Element sublabel = null;
		Element minlabel = null;
		label = doc.createElement("i");
		label.setAttribute("f", "RECORD_ID");
		label.setAttribute("v", RECORD_ID);
		root.appendChild(label);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "MESSAGE_TYPE");
		sublabel.setAttribute("v", MESSAGE_CTNREL);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "SENDER_CODE");
		sublabel.setAttribute("v", SENDER_CODE);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "RECEIVER_CODE");
		sublabel.setAttribute("v", RECEIVER_CODE);
		label.appendChild(sublabel);

		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "FILE_CREATE_TIME");
		sublabel.setAttribute("v", FILE_CREATE_TIME);
		label.appendChild(sublabel);
		

		label = doc.createElement("i");
		label.setAttribute("f", "RECORD_ID");
		label.setAttribute("v", RECORD_ID2);
		root.appendChild(label);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "TERMINAL");
		sublabel.setAttribute("v", TERMINAL);
		label.appendChild(sublabel);
		
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "VESSEL");
		sublabel.setAttribute("v", vessel);
		label.appendChild(sublabel);
		
		
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "VOYAGE");
		sublabel.setAttribute("v", voyage);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "SERIALNUM");
		sublabel.setAttribute("v", SERIALNUM);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "BL_NO");
		sublabel.setAttribute("v", bl);
		
		minlabel = doc.createElement("i");
		minlabel.setAttribute("f", "CTN_NO");
		minlabel.setAttribute("v", ctn);
		
		sublabel.appendChild(minlabel);
		label.appendChild(sublabel);
		
		
		TransformerFactory tFact = TransformerFactory.newInstance();  
		Transformer trans = tFact.newTransformer();  
		trans.setOutputProperty(OutputKeys.INDENT, "yes");//元素是否换行
		StringWriter writer = new StringWriter();  
		StreamResult result = new StreamResult(writer);  
		DOMSource source = new DOMSource(doc);  
		trans.transform(source, result);
		str= writer.toString();
		return str;
	}
	
	/**
	 * 生成取消放箱xml
	 * @param head
	 * @param jsonObject
	 * @param foot
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public String cancelxml(String serialnum) throws ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		ArrayList<String> str = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
		String FILE_CREATE_TIME = df.format(new Date());// new Date()为获取当前系统时间
		//String num = JSONObject.fromObject(jsonObject.get(CTN_NO[0])).get(CTN_NO[1]).toString();
		//String SERIALNUM = SENDER_CODE + num + FILE_CREATE_TIME ;//SENDER_CODE+箱号+当前时间到毫秒
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("root");//根元素
		doc.appendChild(root); // 将根元素添加到文档上
		Element label = null;
		Element sublabel = null;
		Element minlabel = null;
		label = doc.createElement("i");
		label.setAttribute("f", "RECORD_ID");
		label.setAttribute("v", RECORD_ID);
		root.appendChild(label);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "MESSAGE_TYPE");
		sublabel.setAttribute("v", MESSAGE_CANCELREL);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "SENDER_CODE");
		sublabel.setAttribute("v", SENDER_CODE);
		label.appendChild(sublabel);
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "RECEIVER_CODE");
		sublabel.setAttribute("v", RECEIVER_CODE);
		label.appendChild(sublabel);

		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "FILE_CREATE_TIME");
		sublabel.setAttribute("v", FILE_CREATE_TIME);
		label.appendChild(sublabel);
		

		label = doc.createElement("i");
		label.setAttribute("f", "RECORD_ID");
		label.setAttribute("v", MESSAGE_CANCELREL);
		root.appendChild(label);
		
		
		sublabel = doc.createElement("i");
		sublabel.setAttribute("f", "SERIALNUM");
		sublabel.setAttribute("v", serialnum);
		label.appendChild(sublabel);
		
		
		TransformerFactory tFact = TransformerFactory.newInstance();  
		Transformer trans = tFact.newTransformer();  
		trans.setOutputProperty(OutputKeys.INDENT, "yes");//元素是否换行
		StringWriter writer = new StringWriter();  
		StreamResult result = new StreamResult(writer);  
		DOMSource source = new DOMSource(doc);  
		trans.transform(source, result);
		return writer.toString();
	}
}
