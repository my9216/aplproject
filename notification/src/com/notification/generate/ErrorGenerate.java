package com.notification.generate;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("errorGenerate")
public class ErrorGenerate extends BaseGenerate implements IGenerate {
	public void scan() {
		Map<String,String> generate = new HashMap<String,String>();
		generate.put("file", "errorgenerate.txt");
		generate.put("sqlname", "GenerateMap.getErrorList");
		generate.put("subject", "subject_error");
		generate.put("template", "template_error");
		generate.put("mailType", "error");
		Generate(generate);
		
//		List<Map<String, String>> list;
//		try {
//			String remindtime = scanService.getRemindtime("ERROR");
//			scanService.updateRemindtime("ERROR");// 修改扫描时间为最近的系统时间
//			String errorgeneratesql = FileUtil.readFileByLine("sql/errorgenerate.txt");
//			errorgeneratesql = errorgeneratesql.replaceAll("value", remindtime);
//			list = scanService.getGenerateList(errorgeneratesql);
//			MailTemplate temp = new MailTemplate();
//			for (int i = 0; i < list.size(); i++) {
//				Email email = new Email();
//				JSONObject json = JSONObject.fromObject(list.get(i));
//				JSONObject obj2 = new JSONObject();
//				String[] ERROR_DESC = json.getString("ERROR_DESC").split("#@");
//				obj2.put("BPCODE", ERROR_DESC[0]);
//				obj2.put("REFERENCENO", ERROR_DESC[1]);
//				obj2.put("VESSEL", ERROR_DESC[2]);
//				obj2.put("VOYAGE", ERROR_DESC[3]);
//				obj2.put("BLNO", ERROR_DESC[4]);
//				obj2.put("ERRORMESSAGE", ERROR_DESC[5]);
//				// String address = json.getString("COMMUNICATION");
//				String address = "Morrison.wu@solidinfo.hk";
//				String content = temp.generateMail(prop, obj2, prop.getProperty("template_error"));// 得到邮件正文内容
//				String subject = prop.getProperty("subject_error");
//				String state = "error";
//				String checkmail = SimpleMailSender.validateMail(address);
//				String sendinfo = "Invalid address";
//				if (!"".equals(checkmail)) {// 判断地址是否合法
//					state = "wait";
//					sendinfo = "";
//					address = checkmail;
//				}
//				email.setSendinfo(sendinfo);
//				email.setAddress(address);
//				email.setContent(content);
//				email.setStatus(state);
//				email.setTitle(subject);
//				email.setType("error");
//				emailService.addEmail(email);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
