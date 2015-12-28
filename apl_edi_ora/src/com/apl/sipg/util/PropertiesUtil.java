package com.apl.sipg.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * 读取配置文件
	 * @return prop 
	 */
	public static Properties getProperties(String name) {
		Properties prop = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(name);
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
