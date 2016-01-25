package com.notification.generate;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("pendingGenerate")
public class PendingGenerate extends BaseGenerate implements IGenerate {

	public void scan() {
		Map<String,String> generate = new HashMap<String,String>();
		//读取sql文件名
		generate.put("file", "pendinggenerate.txt");
		//获取sql语句配置key
		generate.put("sqlname", "GenerateMap.getPendingList");
		// 获取主题名
		generate.put("subject", "subject_pending");
		//获取模版名
		generate.put("template", "template_pending");
		//定义邮件类型
		generate.put("mailType", "Pending");
		//调用父类的公共方法生成邮件
		Generate(generate);
	}
}
