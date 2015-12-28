package com.notification.generate;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;


@Service("confirmationGenerate")
public class ConfirmationGenerate extends BaseGenerate implements IGenerate {
	public void scan() {
		Map<String,String> generate = new HashMap<String,String>();
		generate.put("file", "confirmationgenerate.txt");
		generate.put("sqlname", "GenerateMap.getConfirmationList");
		generate.put("subject", "subject_confirmation");
		generate.put("template", "template_confirmation");
		generate.put("mailType", "Confirmation");
		Generate(generate);
//		List<Map<String, String>> confirmationlist;
//		try {
//			String remindtime = scanService.getRemindtime("CONFIRMATION");
//			scanService.updateRemindtime("CONFIRMATION"); // 修改扫描时间为最近的系统时间
//			String confirmationgeneratesql = FileUtil.readFileByLine("sql/confirmationgenerate.txt");
//			confirmationgeneratesql = confirmationgeneratesql.replaceAll("value", remindtime);
//			confirmationlist = scanService.getGenerateList(confirmationgeneratesql);
//			MailTemplate temp = new MailTemplate();
//			for (int i = 0; i < confirmationlist.size(); i++) {
//				Email email = new Email();
//				JSONObject json = JSONObject.fromObject(confirmationlist.get(i));
//				//String address = json.getString("COMMUNICATION");
//				String address = "Morrison.wu@solidinfo.hk";
//				String content = temp.generateMail(prop,json, prop.getProperty("template_confirmation"));//得到邮件正文内容
//				String subject = prop.getProperty("subject_confirmation");
//				String state = "error";
//				String sendinfo = "Invalid address";
//				String checkmail = SimpleMailSender.validateMail(address);
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
//				email.setType("Confirmation");
//				emailService.addEmail(email);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
