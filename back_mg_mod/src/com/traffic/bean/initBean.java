package com.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;


import com.bean.common.BaseBean;

public class initBean extends BaseBean{
	private static final String S_SQL_SEARCH = "SELECT OFFICE, GROUP_ID, USERID FROM WEBEDIVIEW where USERID=?";
	
	/*
	 * ��ȡ�û�������Ϣ
	 */
	public static Vector<?> getUserInfo(String userid) throws SQLException, Exception{
		if("".equals(userid)){
			return null;
		}
		Vector<?> vec = null; // �����
		String sql = S_SQL_SEARCH;
		String[] parameter = new String[1];
		parameter[0] = userid;
		vec = BaseBean.extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}
}
