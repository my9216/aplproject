package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.service.OracleConnectionService;

/**
 * 启动oracle连接池
 * @author Administrator
 *
 */
public class OracleConnectListener implements ServletContextListener  {
	/*
	 * 退出程序时断开连接
	 */
	public void contextDestroyed(ServletContextEvent event) {
		try {
			OracleConnectionService.Finalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 初始化数据库连接
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			OracleConnectionService.Initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
