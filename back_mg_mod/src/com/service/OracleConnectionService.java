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
 * oracle���ӳ�
 * @author Administrator
 *
 */
public class OracleConnectionService {
	private DataSource mDataSource = null;//���ӳ�
	private KeepConnectionThread mKeepConnectionThread = null;//���������߳�
	public Connection conn = null;//��������
	private boolean mStop;
	private static OracleConnectionService mOracleConnectionService = null;
	public final static Properties properties = PropertiesUtil.getProperties("dbconfig.properties");//���ݿ���������
	
	/*
	 * ���������̣߳�ÿ6Сʱ�����ݿ⽻��һ��
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
					// ��ʱ5Сʱ��
					for (int i = 0; i < (60 * 60 * 5); i++) {
						try {
							sleep(1000);
							// 1��
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
	 * �������Ӳ������߳�
	 */
	public static void Initialize() throws Exception
	{
		if (mOracleConnectionService == null) {
			mOracleConnectionService = new OracleConnectionService();
		}
		mOracleConnectionService.mKeepConnectionThread.start();
	}
	
	/*
	 * �ر����Ӳ������߳�
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
	 * �������ӣ�ϵͳ������ʼ���в�ִֻ��һ��
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
	 * �������ݿ�����
	 */
	public Connection GetConnection() throws Exception {
		return mDataSource.getConnection();
	}
	
	
}