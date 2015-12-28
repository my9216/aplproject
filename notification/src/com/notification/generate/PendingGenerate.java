package com.notification.generate;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("pendingGenerate")
public class PendingGenerate extends BaseGenerate implements IGenerate {

	public void scan() {
		Map<String,String> generate = new HashMap<String,String>();
		generate.put("file", "pendinggenerate.txt");
		generate.put("sqlname", "GenerateMap.getPendingList");
		generate.put("subject", "subject_pending");
		generate.put("template", "template_pending");
		generate.put("mailType", "Pending");
		Generate(generate);
//		List<Map<String, String>> list;
//		try {
//			String remindtime = scanService.getRemindtime("PENDING");
//			scanService.updateRemindtime("PENDING");// 修改扫描时间为最近的系统时间
//			System.out.println(remindtime);
//			String pendinggeneratesql = FileUtil.readFileByLine("sql/pendinggenerate.txt");
//			pendinggeneratesql = pendinggeneratesql.replaceAll("value", remindtime);
//			list = scanService.getGenerateList(pendinggeneratesql);
//			System.out.println(list.size());
//			MailTemplate temp = new MailTemplate();
//			for (int i = 0; i < list.size(); i++) {
//				Email email = new Email();
//				JSONObject json = JSONObject.fromObject(list.get(i));
//				//String address = json.getString("COMMUNICATION");
//				String address = "Morrison.wu@solidinfo.hk";
//				String content = temp.generateMail(prop, json, prop.getProperty("template_pending"));//得到邮件正文内容
//				String subject = prop.getProperty("subject_pending");
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
//				email.setType("Pending");
//				emailService.addEmail(email);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
