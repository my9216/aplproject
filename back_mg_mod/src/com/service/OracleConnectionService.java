package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

import com.traffic.util.Logger;
import com.traffic.util.PropertiesUtil;

/**
 * oracle连接池
 * @author Administrator
 *
 */
public class OracleConnectionService {
	private DataSource mDataSource = null;//连接池
	private KeepConnectionThread mKeepConnectionThread = null;//保持连接线程
	public Connection conn = null;//创建连接
	private boolean mStop;
	private static OracleConnectionService mOracleConnectionService = null;
	public final static Properties properties = PropertiesUtil.getProperties("dbconfig.properties");//数据库连接配置
	
	/*
	 * 保持连接线程，每6小时与数据库交互一次
	 */
	class KeepConnectionThread extends Thread {
		public KeepConnectionThread() {
			setName(this.getClass().getName());
		}

		public void run() {
			Logger logger = Logger.getLogger(OracleConnectionService.class);
			while (!mStop) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					sleep(10000);
					conn = GetConnection();
					pstmt = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
					rs = pstmt.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int columns = rsmd.getColumnCount();
					if (columns != 0) {
						while (rs.next()) {
							String time = rs.getString(1);
							logger.info("In Time:" + time);
						}
					}
				} catch (Exception e) {
					logger.error(e.getClass().getName() + ":" + e.getMessage());
				} finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (Exception e) {
						logger.error(e.getClass().getName() + ":" + e.getMessage());
					}
					// 延时5小时。
					for (int i = 0; i < (60 * 60 * 5); i++) {
						try {
							sleep(1000);
							// 1秒
							if (mStop) {
								break;
							}
						} catch (InterruptedException e) {
							logger.error(e.getClass().getName() + ":" + e.getMessage());
						}
					}
				}
			}
		}
		public void Stop() {
			mStop = true;
		}
	}

	/*
	 * 创建连接并启动线程
	 */
	public static void Initialize() throws Exception
	{
		if (mOracleConnectionService == null) {
			mOracleConnectionService = new OracleConnectionService();
		}
		mOracleConnectionService.mKeepConnectionThread.start();
	}
	
	/*
	 * 关闭连接并结束线程
	 */
	public static void Finalize() throws Exception
	{
		if (mOracleConnectionService != null && mOracleConnectionService.mKeepConnectionThread != null) {
			mOracleConnectionService.mKeepConnectionThread.Stop();
		}
		mOracleConnectionService = null;
	}
	
	public static OracleConnectionService GetInstance() throws Exception {
		if (mOracleConnectionService == null){
			throw new Exception("Service not initialized yet.");
		}
		return mOracleConnectionService;
	}

	/*
	 * 建立连接，系统启动开始运行并只执行一次
	 */
	public OracleConnectionService() throws Exception {
		try {
			mDataSource = BasicDataSourceFactory.createDataSource(properties);
			mKeepConnectionThread = new KeepConnectionThread();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * 返回数据库连接
	 */
	public Connection GetConnection() throws Exception {
		return mDataSource.getConnection();
	}
}