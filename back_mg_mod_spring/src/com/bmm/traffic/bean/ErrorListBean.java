package com.bmm.traffic.bean;

import java.sql.SQLException;
import java.util.Vector;

import com.bmm.bean.common.BaseBean;

public class ErrorListBean extends BaseBean {

	/* ��ѯ������־sql */
	private static final String S_SQL_SEARCH = "SELECT ID,ERROR_DESC,ERROR_FILE_NAME,to_char(CREATE_DATE,'yyyy-MM-dd  hh24:mi:ss') as CREATE_DATE FROM T_TRAFFIC_CONVERT_ERROR_LOG WHERE MODULES = ? and lower(ERROR_FILE_NAME) LIKE ? ";

	/* ͳ�ƴ�����־����sql */
	private static final String S_SQL_STATISTICS = "SELECT SUM(FILE_QUANTITY) as fileQty , SUM(ERR_FILE_QUANTITY) as errQty , SUM(WBP_FILE_QUANTITY) as wbpQty ,SUM(Q2C_FILE_QUANTITY) as q2cQty FROM T_TRAFFIC_CONVERT_DAILY_LOG WHERE MODULES = ?";

	/**
	 * ������־�б��ѯ����
	 * 
	 * @param filename		������־��Ӧ�ĵ��ļ���
	 * @param startDate		������־����ʱ���ѯ��ʼ����
	 * @param stopDate		������־����ʱ���ѯ��ֹ����
	 * @param modules		��Ӧ��ģ������
	 * @param page
	 * @param rows
	 * @return vec			��ѯ���Ľ����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector<?> getErrorList(String filename, String startDate, String stopDate, String modules, int page,
			int rows) throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_SEARCH;
		String[] parameter = null;
		parameter = new String[2];
		parameter[0] = modules;
		//filename���Դ�Сд,ͳһ��Сд��ѯ
		parameter[1] = "%" + filename.toLowerCase() + "%";
		
		// �ж��Ƿ���ɸѡ,����ж�Ӧ�Ĳ�ѯ����,�򽫽���ѯ����ƴ��SQL����WHERE������
		if (!"".equals(startDate)) {
			sql += " AND to_char(CREATE_DATE,'yyyy-MM-dd') >= '" + startDate + "'";
		}
		if (!"".equals(stopDate)) {
			sql += " AND to_char(CREATE_DATE,'yyyy-MM-dd') <= '" + stopDate + "'";
		}
		sql += " order by CREATE_DATE desc ";
		vec = extractJSONArray(sql, parameter, page, rows);
		return vec;
	}

	/**
	 * ����ʱ������ͳ�ƴ�����־����
	 * 
	 * @param modules		��Ӧ��ģ������
	 * @param startDate		������־����ʱ���ѯ��ʼ����
	 * @param stopDate		������־����ʱ���ѯ��ֹ����
	 * @return vec			��ѯ���Ľ����
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Vector<?> getStatistics(String modules, String startDate, String stopDate)
			throws SQLException, Exception {
		// �����
		Vector<?> vec = null;
		String sql = S_SQL_STATISTICS;
		String[] parameter = null;
		parameter = new String[1];
		parameter[0] = modules;
		if (!"".equals(startDate)) {
			sql += " AND to_char(LOG_DATE,'yyyy-MM-dd') >= '" + startDate + "'";
		}
		if (!"".equals(stopDate)) {
			sql += " AND to_char(LOG_DATE,'yyyy-MM-dd') <= '" + stopDate + "'";
		}
		vec = extractJSONArray(sql, parameter, 0, 0);
		return vec;
	}

}
