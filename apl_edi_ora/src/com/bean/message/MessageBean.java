package com.bean.message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONObject;

import com.bean.common.BaseBean;

public class MessageBean extends BaseBean {
	private static final String S_SQL_CREATE = "INSERT INTO message(serialnum,bl,ctn,head,content,foot,type,status,vessel,voyage,datetime) values (?,?,?,?,?,?,?,?,?,?,sysdate)";
	private static final String S_SQL_SEARCH = "SELECT id,serialnum,ctn,bl,head,content,foot,type,status,to_char(datetime,'yyyy-MM-dd  hh24:mi:ss') as datetime,vessel,voyage FROM view1 WHERE lower(bl) LIKE ? AND lower(ctn) LIKE ? AND lower(serialnum) LIKE ? ";
	private static final String S_SQL_UPDATE = "UPDATE message SET type=?,status=?,datetime=sysdate WHERE id=?";

	public void insert(ArrayList<String> serialnum, ArrayList<String> bl, ArrayList<String> ctn,
			Map<String, Map<String, String>> head, ArrayList<JSONObject> content, Map<String, Map<String, String>> foot,
			ArrayList<String> status, ArrayList<String> vessel, ArrayList<String> voyage) throws Exception {
		String sql = S_SQL_CREATE;
		String[] parameter = null;
		parameter = new String[10];

		/*
		 * parameter = new String [serialnum.size()*8]; for(int
		 * i=0;i<serialnum.size();i++){ parameter[i*8] = serialnum.get(i);
		 * parameter[i*8+1] = bl.get(i); parameter[i*8+2] = ctn.get(i);
		 * parameter[i*8+3] = JSONObject.fromObject(head).toString();
		 * parameter[i*8+4] = JSONObject.fromObject(content.get(i)).toString();
		 * parameter[i*8+5] = JSONObject.fromObject(foot).toString();
		 * parameter[i*8+6] = "CTNREL"; parameter[i*8+7] = status.get(i); }
		 */
		for (int i = 0; i < serialnum.size(); i++) {
			parameter[0] = serialnum.get(i);
			parameter[1] = bl.get(i);
			parameter[2] = ctn.get(i);
			parameter[3] = JSONObject.fromObject(head).toString();
			parameter[4] = JSONObject.fromObject(content.get(i)).toString();
			parameter[5] = JSONObject.fromObject(foot).toString();
			parameter[6] = "CTNREL";
			parameter[7] = status.get(i);
			parameter[8] = vessel.get(i);
			parameter[9] = voyage.get(i);
			BaseBean.Update(sql, parameter);
		}

	}

	public static Vector<?> GetMessageList(String slno, String ctnno, String serialnum, String type, String status,
			String startdate, String stopdate, int page, int rows) throws SQLException, Exception {
		Vector<?> vec = null; // 保存结果集
		String sql = S_SQL_SEARCH;
		String[] parameter = null;
		parameter = new String[3];
		parameter[0] = "%" + slno.toLowerCase() + "%";
		parameter[1] = "%" + ctnno.toLowerCase() + "%";
		parameter[2] = "%" + serialnum.toLowerCase() + "%";
		if (!"all".equals(type)) {
			sql += " AND type='" + type + "'";
		}
		if ("ok".equals(status)) {
			sql += " AND status like '%OK%'";
		} else if ("other".equals(status)) {
			sql += " AND status not like '%OK%'";
		}
		if (!"".equals(startdate)) {
			sql += " AND to_char(datetime,'yyyy-MM-dd') >= '" + startdate + "'";
		}
		if (!"".equals(stopdate)) {
			sql += " AND to_char(datetime,'yyyy-MM-dd') <= '" + stopdate + "'";
		}

		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	public void UpdateMessage(String[] cancelIdList, ArrayList<String> status, String type) throws Exception {
		String[] parameter = null;
		parameter = new String[3];
		for (int i = 0; i < cancelIdList.length; i++) {
			String sql = S_SQL_UPDATE;
			parameter[0] = type;
			parameter[1] = status.get(i);
			parameter[2] = cancelIdList[i];
			BaseBean.Update(sql, parameter);
		}

	}
}
