package com.bmm.bean.common;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bmm.service.OracleConnectionService;
import com.bmm.traffic.util.Logger;

/*
 * �����ݿ���в������ѷ���
 */
public class BaseBean {

	private static Logger logger = Logger.getLogger(BaseBean.class);

	/**
	 * ֱ�Ӷ����ݿ���в�ѯ����
	 * 
	 * @param pSqlString	ִ�е�sql���
	 * @param pParameter	��������
	 * @return dataset 		ArrayList�����
	 * @throws Exception
	 */
	public static ArrayList<ArrayList<String>> query(String pSqlString, String[] pParameter) throws Exception {
		// ��ʼ�������
		ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ��OracleConnectionService���ӳ��л�ȡ����
			conn = OracleConnectionService.GetInstance().GetConnection();
			pstmt = conn.prepareStatement(pSqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// �����ѯ����
			if (pParameter != null) {
				for (int i = 1; i <= pParameter.length; i++) {
					pstmt.setString(i, pParameter[i - 1]);
				}
			}
			// ִ�в�ѯ
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			if (columns != 0) {
				// ���������
				while (rs.next()) {
					ArrayList<String> row = new ArrayList<String>();
					for (int i = 0; i < columns; i++) {// ����ÿһ�е���
						row.add(rs.getString(i + 1));
					}
					dataset.add(row);// ����ÿ������
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				// �ر�����
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return dataset;
	}

	/**
	 * ���������ҳת�ܳ�JSON,����JSONArray
	 * 
	 * @param pSqlString		ִ�е�sql���
	 * @param pParameter		��������
	 * @param page
	 * @param rows
	 * @return returnV 			Vector��ҳ�����
	 * @throws SQLException
	 * @throws Exception
	 */
	protected static Vector extractJSONArray(String pSqlString, String[] pParameter, int page, int rows)
			throws SQLException, Exception {
		// ��װsql����ֹ��Ϊ�汾���⵼��ordey by����
		pSqlString = "select * from (" + pSqlString + ")";
		// ��ʼ����ҳ�����
		Vector returnV = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ��OracleConnectionService���ӳ��л�ȡ����
			conn = OracleConnectionService.GetInstance().GetConnection();
			pstmt = conn.prepareStatement(pSqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// �����ѯ����
			if (pParameter != null) {
				for (int i = 1; i <= pParameter.length; i++) {
					pstmt.setString(i, pParameter[i - 1]);// �������
				}
			}
			// ִ�в�ѯ
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ����
			int columns = rsmd.getColumnCount();
			int iRow = 0;
			rs.last();
			// ��ȡ������
			int row = rs.getRow();

			rs.beforeFirst();
			if (rows != 0 && page != 1) {
				rs.absolute((page - 1) * rows);
			}
			JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();

			// ���������
			if (columns != 0) {
				while (rs.next() && (iRow < rows || rows == 0)) {
					for (int i = 1; i <= columns; i++) {
						// ����clob���͵���
						if ("CONTENTCLOB".equals(rsmd.getColumnLabel(i))) {
							Clob clob = rs.getClob("CONTENTCLOB");
							Reader inStream = clob.getCharacterStream();
							char[] c = new char[(int) clob.length()];
							try {
								inStream.read(c);
								String clobstr = new String(c);
								mapOfColValues.put(rsmd.getColumnLabel(i), clobstr);
								inStream.close();
							} catch (IOException e) {
								logger.error(e.getMessage());
							}
						} else {
							mapOfColValues.put(rsmd.getColumnLabel(i), rs.getObject(i));
						}
					}
					++iRow;
					array.add(mapOfColValues);
				}
			}
			// ������
			returnV.add(String.valueOf(row));
			returnV.add("rows");
			// �����
			returnV.add(array);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				// �ر�����
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return returnV;
	}

	/**
	 * ���������ҳת����JSON,����JSONArray,����������
	 * 
	 * @param pSqlString	ִ�е�sql���
	 * @param pParameter	��������
	 * @param id			������ʵ��ֵ
	 * @param name			��������ʾֵ
	 * @return returnV 		�����
	 * @throws SQLException
	 * @throws Exception
	 */
	protected static Vector extractComboArray(String pSqlString, String[] pParameter, String id, String name)
			throws SQLException, Exception {
		pSqlString = "select * from (" + pSqlString + ")";
		// ��ʼ�������
		Vector returnV = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// ��OracleConnectionService���ӳ��л�ȡ����
			conn = OracleConnectionService.GetInstance().GetConnection();
			pstmt = conn.prepareStatement(pSqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// �������
			if (pParameter != null) {
				for (int i = 1; i <= pParameter.length; i++) {
					pstmt.setString(i, pParameter[i - 1]);
				}
			}
			// ִ��sql
			rs = pstmt.executeQuery();
			JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();
			while (rs.next()) {
				// ����������ʵ��ֵ
				mapOfColValues.put(id, rs.getObject(1));
				// ������������ʾֵ
				mapOfColValues.put(name, rs.getObject(2));
				array.add(mapOfColValues);
			}
			// ��������������
			returnV.add(array);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return returnV;
	}

	/**
	 * �������ݿ�
	 * 
	 * @param pSqlString	ִ�е�sql���
	 * @param pParameter	��������
	 * @return updateCount 	����ִ�гɹ�������
	 * @throws Exception
	 */
	public static int update(String pSqlString, String[] pParameter) throws Exception {
		int updateCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// ��OracleConnectionService���ӳ��л�ȡ����
			conn = OracleConnectionService.GetInstance().GetConnection();
			pstmt = conn.prepareStatement(pSqlString);
			// �������
			if (pParameter != null) {
				for (int i = 1; i <= pParameter.length; i++) {
					pstmt.setString(i, pParameter[i - 1]);
				}
			}
			// ִ��sql
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				// �ر�����
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return updateCount;
	}
	
}