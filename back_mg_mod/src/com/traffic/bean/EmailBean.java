package com.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;


import com.bean.common.BaseBean;

public class EmailBean extends BaseBean{
	private static final String S_SQL_SEARCH = "SELECT ID, ADDRESS, TITLE, STATUS, MAIL_TYPE,CONTENT as CONTENTCLOB, to_char(CREATEDATE,'yyyy-MM-dd  hh24:mi:ss') as CREATEDATE,SENDINFO from T_NOTIFICATION_EMAIL WHERE 1=1 ";
	private static final String S_SQL_REFRESHSTATE = "SELECT ID, ADDRESS, TITLE, STATUS,CONTENT as CONTENTCLOB, MAIL_TYPE,to_char(CREATEDATE,'yyyy-MM-dd  hh24:mi:ss') as CREATEDATE from T_NOTIFICATION_EMAIL";
	private static final String S_SQL_UPDATE_ADDRESS = "INSERT INTO T_NOTIFICATION_EMAIL(ID,  STATUS, TITLE,ADDRESS,CONTENT, MAIL_TYPE,CREATEDATE) "+
													   "SELECT SEQ_T_NOTIFICATION_EMAIL.nextval as ID,? as state,TITLE,? as address,CONTENT,MAIL_TYPE,SYSDATE as CREATEDATE from T_NOTIFICATION_EMAIL where ID=?";
	private static final String S_SQL_UPDATE_STATE = "INSERT INTO T_NOTIFICATION_EMAIL(ID,  STATUS, TITLE,ADDRESS,CONTENT, MAIL_TYPE,CREATEDATE) "+
			   "SELECT SEQ_T_NOTIFICATION_EMAIL.nextval as ID,? as state,TITLE,ADDRESS,CONTENT,MAIL_TYPE,SYSDATE as CREATEDATE from T_NOTIFICATION_EMAIL WHERE ID IN (%s)";
	
	private static final String S_SQL_STATISTICS = "SELECT STATUS,count(ID) as count FROM T_NOTIFICATION_EMAIL WHERE 1=1 ";
	
	/*
	 * ��ʾ�ʼ��б�
	 */
	public static Vector<?> GetEmailList(String type, String address,String state, String startdate, String stopdate, int page, int rows) throws SQLException, Exception{
		Vector<?> vec = null; // �����
		String sql = S_SQL_SEARCH;
		String[] parameter = null;		
		/*�ж��Ƿ���ɸѡ*/
		if(!"all".equals(type)){
			sql += " AND MAIL_TYPE = '" + type + "'";
		}
		if(!"".equals(address)){
			sql += " AND lower(address) like '%" + address.toLowerCase() + "%'";
		}
		if(!"all".equals(state)){
			sql += " AND STATUS = '" + state + "'";
		}
		if(!"".equals(startdate)){
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') >= '" + startdate + "'";
		}
		if(!"".equals(stopdate)){
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') <= '" + stopdate + "'";
		}
		sql += " ORDER BY createdate DESC ";
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}
	
	/*
	 * ˢ�·���״̬
	 */
	public static Vector<?> GetRefreshstate(String[] ID) throws SQLException, Exception{
		Vector<?> vec = null; // �����
		String sql = S_SQL_REFRESHSTATE;
		sql += String.format(" WHERE ID IN (%s) ORDER BY createdate DESC", ArrayToString(ID));
		String[] parameter = null;
		vec = BaseBean.extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}
	
	/*
	 * �޸ķ��͵�ַ�����·����ʼ�
	 */
	public static void UpdateAddress(String state,String address,String ID) throws SQLException, Exception{
		String sql = S_SQL_UPDATE_ADDRESS;
		String[] parameter = new String[3];
		parameter[0] = state;
		parameter[1] = address;
		parameter[2] = ID;
		Update(sql, parameter);
	}
	
	/*
	 * ���·����ʼ�
	 */
	public static void UpdateState(String state,String[] ID) throws SQLException, Exception{
		String sql = S_SQL_UPDATE_STATE;
		sql = String.format(sql, ArrayToString(ID));
		String[] parameter = new String[1];
		parameter[0] = state;
		Update(sql, parameter);
	}
	
	/*
	 * ͳ�Ʒ������
	 */
	public static Vector<?> GetStatisticsinfo(String type, String address,String state, String startdate, String stopdate) throws SQLException, Exception{
		Vector<?> vec = null; // �����
		String sql = S_SQL_STATISTICS;
		String[] parameter = null;
		if(!"all".equals(type)){
			sql += " AND MAIL_TYPE = '" + type + "'";
		}
		if(!"".equals(address)){
			sql += " AND address like '%" + address + "%'";
		}
		if(!"all".equals(state)){
			sql += " AND STATUS = '" + state + "'";
		}
		if(!"".equals(startdate)){
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') >= '" + startdate + "'";
		}
		if(!"".equals(stopdate)){
			sql += " AND to_char(CREATEDATE,'yyyy-MM-dd') <= '" + stopdate + "'";
		}
		sql += " group by STATUS ";
		vec = BaseBean.extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}

}
