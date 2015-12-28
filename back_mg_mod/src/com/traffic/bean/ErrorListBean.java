package com.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;

import com.bean.common.BaseBean;

public class ErrorListBean extends BaseBean {
	private static final String S_SQL_SEARCH = "SELECT ID,ERROR_DESC,ERROR_FILE_NAME,to_char(CREATE_DATE,'yyyy-MM-dd  hh24:mi:ss') as CREATE_DATE FROM T_TRAFFIC_CONVERT_ERROR_LOG WHERE MODULES = ? and lower(ERROR_FILE_NAME) LIKE ? ";
	private static final String S_SQL_STATISTICS = "SELECT SUM(FILE_QUANTITY) as fileQty , SUM(ERR_FILE_QUANTITY) as errQty , SUM(WBP_FILE_QUANTITY) as wbpQty ,SUM(Q2C_FILE_QUANTITY) as q2cQty FROM T_TRAFFIC_CONVERT_DAILY_LOG WHERE MODULES = ?";

	/*
	 * 获得错误信息列表
	 */
	public static Vector<?> GetErrorList(String filename, String startdate, String stopdate, String MODULES, int page,
			int rows) throws SQLException, Exception {
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_SEARCH;
		String[] parameter = null;
		parameter = new String[2];
		parameter[0] = MODULES;
		parameter[1] = "%" + filename.toLowerCase() + "%";

		if (!"".equals(startdate)) {
			sql += " AND to_char(CREATE_DATE,'yyyy-MM-dd') >= '" + startdate + "'";
		}
		if (!"".equals(stopdate)) {
			sql += " AND to_char(CREATE_DATE,'yyyy-MM-dd') <= '" + stopdate + "'";
		}
		sql += " order by CREATE_DATE desc ";
		vec = BaseBean.extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/*
	 * 信息统计：文件总数，错误数，成功数
	 */
	public static Vector<?> GetStatisticsinfo(String MODULES, String startdate, String stopdate)
			throws SQLException, Exception {
		Vector<?> vec = null; // 结果集
		String sql = S_SQL_STATISTICS;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = MODULES;
		if (!"".equals(startdate)) {
			sql += " AND to_char(LOG_DATE,'yyyy-MM-dd') >= '" + startdate + "'";
		}
		if (!"".equals(stopdate)) {
			sql += " AND to_char(LOG_DATE,'yyyy-MM-dd') <= '" + stopdate + "'";
		}
		vec = BaseBean.extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}

}
