package com.notification.generate;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;


@Service("confirmationGenerate")
public class ConfirmationGenerate extends BaseGenerate implements IGenerate {
	public void scan() {
		
		Map<String,String> generate = new HashMap<String,String>();
		//读取sql文件名
		generate.put("file", "confirmationgenerate.txt");
		//获取sql语句配置key
		generate.put("sqlname", "GenerateMap.getConfirmationList");
		// 获取主题名
		generate.put("subject", "subject_confirmation");
		//获取模版名
		generate.put("template", "template_confirmation");
		//定义邮件类型
		generate.put("mailType", "Confirmation");
		//调用父类的公共方法生成邮件
		Generate(generate);
	}

}
