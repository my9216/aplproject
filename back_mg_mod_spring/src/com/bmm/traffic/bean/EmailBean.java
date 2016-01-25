package com.bmm.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;

import com.bmm.bean.common.BaseBean;
import com.bmm.traffic.util.PublicTools;

public class EmailBean extends BaseBean {

	/* ��ѯ�ʼ��б�sql */
	private static final String S_SQL_SEARCH = "SELECT ID, ADDRESS, TITLE, STATUS, MAIL_TYPE,CONTENT as CONTENTCLOB, to_char(CREATEDATE,'yyyy-MM-dd  hh24:mi:ss') as CREATEDATE,SENDINFO from T_NOTIFICATION_EMAIL WHERE 1=1 ";

	/* ��ʱ�첽��ѯ���µ��ʼ���Ϣsql */
	private static final String S_SQL_REFRESHSTATUS = "SELECT ID, ADDRESS, TITLE, STATUS,CONTENT as CONTENTCLOB, MAIL_TYPE,to_char(CREATEDATE,'yyyy-MM-dd  hh24:mi:ss') as CREATEDATE from T_NOTIFICATION_EMAIL";

	/* �����ռ��˵�ַ��״̬sql */
	private static final String S_SQL_UPDATE_ADDRESS = "INSERT INTO T_NOTIFICATION_EMAIL(ID,  STATUS, TITLE,ADDRESS,CONTENT, MAIL_TYPE,CREATEDATE) "
			+ "SELECT SEQ_T_NOTIFICATION_EMAIL.nextval as ID,? as status,TITLE,? as address,CONTENT,MAIL_TYPE,SYSDATE as CREATEDATE from T_NOTIFICATION_EMAIL where ID=?";

	/* �����ʼ�״̬sql */
	private static final String S_SQL_UPDATE_STATUS = "INSERT INTO T_NOTIFICATION_EMAIL(ID,  STATUS, TITLE,ADDRESS,CONTENT, MAIL_TYPE,CREATEDATE) "
			+ "SELECT SEQ_T_NOTIFICATION_EMAIL.nextval as ID,? as status,TITLE,ADDRESS,CONTENT,MAIL_TYPE,SYSDATE as CREATEDATE from T_NOTIFICATION_EMAIL WHERE ID IN (%s)";

	/* ͳ���ʼ�������sql */
	private static final String S_SQL_STATISTICS = "SELECT STATUS,count(ID) as count FROM T_NOTIFICATION_EMAIL WHERE 1=1 ";

	/**
	 * �ʼ��б��ѯ����
	 * 
	 * @param type		�ʼ�����
	 * @param address	�ռ��˵�ַ
	 * @param status	�ʼ�״̬
	 * @param startDate	�ʼ�����ʱ���ѯ��ʼʱ��
	 * @param stopdDate	�ʼ�����ʱ���ѯ��ֹʱ��
	 * @param page
	 * @param rows
	 * @return vec 		�����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector<?> getEmailList(String type, String address, String status, String startDate, String stopDate,
			int page, int rows) throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_SEARCH;
		String[] parameter = null;

		// �ж��Ƿ���ɸѡ,����ж�Ӧ�Ĳ�ѯ����,�򽫽���ѯ����ƴ��SQL����WHERE������
		if (!("all".equals(type))) {
			sql += " AND MAIL_TYPE = '" + type + "'";
		}
		if (!"".equals(address)) {
			sql += " AND lower(address) like '%" + address.toLowerCase() + "%'";
		}
		if (!"all".equals(status)) {
			sql += " AND STATUS = '" + status + "'";
		}
		if (!"".equals(startDate)) {
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') >= '" + startDate + "'";
		}
		if (!"".equals(stopDate)) {
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') <= '" + stopDate + "'";
		}
		// ��ѯ��䰴������ʱ�䵹������
		sql += " ORDER BY createdate DESC ";
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ��ʱ�첽��ѯÿ���ʼ�������״̬�����µ�ҳ��
	 * 
	 * @param ID	�ʼ���ID����
	 * @return 		�ʼ�������״̬�����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector<?> getRefreshStatus(String[] ID) throws SQLException, Exception {
		Vector<?> vec = null; // �����
		String sql = S_SQL_REFRESHSTATUS;
		sql += String.format(" WHERE ID IN (%s) ORDER BY createdate DESC", PublicTools.ArrayToString(ID));
		String[] parameter = null;
		vec = BaseBean.extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}

	/**
	 * �޸��ռ��˵�ַ�������ʼ�״̬��Ϊδ����
	 * 
	 * @param status	�ʼ�״̬
	 * @param address	�ʼ��ռ��˵�ַ
	 * @param id		�ʼ���id����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static void updateAddress(String status, String address, String id) throws SQLException, Exception {
		String sql = S_SQL_UPDATE_ADDRESS;
		String[] parameter = new String[3];
		parameter[0] = status;
		parameter[1] = address;
		parameter[2] = id;
		update(sql, parameter);
	}

	/**
	 * ���ʼ�״̬����Ϊδ����
	 * 
	 * @param status	�ʼ�״̬
	 * @param id		�ʼ���id����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static void updateStatus(String status, String[] id) throws SQLException, Exception {
		String sql = S_SQL_UPDATE_STATUS;
		sql = String.format(sql, PublicTools.ArrayToString(id));
		String[] parameter = new String[1];
		parameter[0] = status;
		update(sql, parameter);
	}

	/**
	 * ͳ�Ʒ������
	 * 
	 * @param type		�ʼ�����
	 * @param address	�ʼ��ռ��˵�ַ
	 * @param status	�ʼ�״̬
	 * @param startDate	�ʼ�����ʱ���ѯ��ʼ����
	 * @param stopDate	�ʼ�����ʱ���ѯ��ֹ����
	 * @return vec 		ͳ������Ľ����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector<?> getStatistics(String type, String address, String status, String startDate,
			String stopDate) throws SQLException, Exception {
		Vector<?> vec = null; // �����
		String sql = S_SQL_STATISTICS;
		String[] parameter = null;
		if (!"all".equals(type)) {
			sql += " AND MAIL_TYPE = '" + type + "'";
		}
		if (!"".equals(address)) {
			sql += " AND address like '%" + address + "%'";
		}
		if (!"all".equals(status)) {
			sql += " AND STATUS = '" + status + "'";
		}
		if (!"".equals(startDate)) {
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') >= '" + startDate + "'";
		}
		if (!"".equals(stopDate)) {
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') <= '" + stopDate + "'";
		}
		sql += " group by STATUS ";
		vec = extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}
}
