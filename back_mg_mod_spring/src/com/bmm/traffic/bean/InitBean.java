package com.bmm.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;

import com.bmm.bean.common.BaseBean;

public class InitBean extends BaseBean {

	/* ��ѯ�û����Ȩ����Ϣ��sql */
	private static final String S_SQL_SEARCH = "SELECT OFFICE, GROUP_ID, USERID FROM WEBEDIVIEW where USERID=?";

	
	/**
	 * ��ȡ�û����Ȩ����Ϣ�ķ���
	 * 
	 * @param userid	WBP������û�id
	 * @return vec 		��ѯ���Ľ����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector<?> getUserInfo(String userid) throws SQLException, Exception {
		if ("".equals(userid)) {
			return null;
		}
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_SEARCH;
		String[] parameter = new String[1];
		parameter[0] = userid;
		vec = extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}
}
