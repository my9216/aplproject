package com.apl.convert.mq;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 程序入口类
 *  
 */
public class MqService {
	
	public static void main(String[] args) {
		 new FileSystemXmlApplicationContext("applicationContext.xml"); 
	}
}
