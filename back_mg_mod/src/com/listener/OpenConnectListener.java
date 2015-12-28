package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.service.OracleConnectionService;

public class OpenConnectListener implements ServletContextListener  {

	/*
	 * �˳�����ʱ�Ͽ�����
	 */
	public void contextDestroyed(ServletContextEvent event) {
		try {
			OracleConnectionService.Finalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * ��ʼ�����ݿ�����
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			OracleConnectionService.Initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
