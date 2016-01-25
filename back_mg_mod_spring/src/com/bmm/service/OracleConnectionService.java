package com.bmm.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

import com.bmm.traffic.util.Logger;
import com.bmm.traffic.util.PropertiesUtil;

/**
 * oracle���ӳ�
 * 
 * @author Administrator
 *
 */
public class OracleConnectionService {

	Logger logger = Logger.getLogger(OracleConnectionService.class);

	/* ���ӳ� */
	private DataSource mDataSource = null;

	/* ���������߳� */
	private KeepConnectionThread mKeepConnectionThread = null;

	/* ���ݿ����� */
	public Connection conn = null;
	
	/* ���ݿ����ӱ�ʶ�� */
	private boolean mStop;
	private static OracleConnectionService mOracleConnectionService = null;

	/* ���ݿ����������ļ� */
	public final static Properties properties = PropertiesUtil.getProperties("dbconfig.properties");

	/**
	 * ���������̣߳�ÿ6Сʱ�����ݿ⽻��һ��
	 */
	class KeepConnectionThread extends Thread {

		public KeepConnectionThread() {
			setName(this.getClass().getName());
		}

		public void run() {
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
							logger.info("KeepConnectionThread ran ! In Time:" + time);
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
					// ��ʱ5Сʱ
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

		public void terminate() {
			mStop = true;
		}
	}

	/*
	 * �������Ӳ������߳�
	 */
	public static void Initialize() throws Exception {
		if (mOracleConnectionService == null) {
			mOracleConnectionService = new OracleConnectionService();
		}
		mOracleConnectionService.mKeepConnectionThread.start();
	}

	/*
	 * �ر����Ӳ������߳�
	 */
	public static void Finalize() throws Exception {
		if (mOracleConnectionService != null && mOracleConnectionService.mKeepConnectionThread != null) {
			mOracleConnectionService.mKeepConnectionThread.terminate();
		}
		mOracleConnectionService = null;
	}

	/**
	 * ��ȡOracleConnectionServiceʵ��
	 * 
	 * @return mOracleConnectionService ����ʵ��
	 * @throws Exception
	 */
	public static OracleConnectionService GetInstance() throws Exception {
		if (mOracleConnectionService == null) {
			throw new Exception("Service not initialized yet.");
		}
		return mOracleConnectionService;
	}

	/**
	 * �������ӣ�ϵͳ������ʼ���в�ִֻ��һ��
	 * 
	 * @throws Exception
	 */
	public OracleConnectionService() throws Exception {
		try {
			// ͨ��Apache.Dbcp��ʼ��һ���µ�DataSource,�������ݿ����������ļ�
			// �����ļ�properties�е�key���ܸ���!
			mDataSource = BasicDataSourceFactory.createDataSource(properties);
			// ��ʼ�����������߳�
			mKeepConnectionThread = new KeepConnectionThread();
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	/**
	 * �������ݿ�����
	 * 
	 * @return	conn ���ݿ�����
	 * @throws Exception
	 */
	public Connection GetConnection() throws Exception {
		return mDataSource.getConnection();
	}
}