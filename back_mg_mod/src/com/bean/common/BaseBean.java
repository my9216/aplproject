package com.bean.common;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.traffic.util.Logger;
import com.service.OracleConnectionService;


public class BaseBean {
	private static String mLastInsertId = "";
	
	private static Logger logger = Logger.getLogger(BaseBean.class);
	
	/**
	 * ��ѯ���ݿ�
	 * @param pSqlString
	 * @param pParameter
	 * @param pParameterNum
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ArrayList<String>> Query(String pSqlString, String[] pParameter) throws Exception
	{
		ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();//��������
		Connection conn = null;//�������ݿ�����
		PreparedStatement pstmt = null;//ִ�в�������ѯ
		ResultSet rs = null;//��ȡ�����
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();//��OracleConnectionService���ӳ��л�ȡ����
			pstmt = conn.prepareStatement(pSqlString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//�����ѯ����
				}
			}
			rs = pstmt.executeQuery();//ִ�в�ѯ
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			if(columns != 0){
				while(rs.next()){//���������
					ArrayList<String> row = new ArrayList<String>();
					for(int i=0; i<columns;i++){//����ÿһ�е���
						row.add(rs.getString(i+1));
					}
					dataset.add(row);//���ÿ������
				}
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally {
			try{//�ر�����
				rs.close();
				pstmt.close();
				conn.close();	
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return dataset;
	}
	
	/**
	* ����õĽ�������з�ҳ������List
	* 
	* @param rs
	* @return
	* @throws SQLException
	 * @throws Exception 
	*/
	public static List<ArrayList<String>> paging(ArrayList<ArrayList<String>> list,int page,int rows){
		int fromIndex = (page-1)*rows;
		int toIndex = fromIndex + rows;
		if(toIndex > list.size()){
			toIndex = list.size();
		}
		return list.subList(fromIndex, toIndex);
	}
	/**
	* ���������ҳת�ܳ�JSON,����JSONArray
	* 
	* @param rs
	* @return �����
	* @throws SQLException
	 * @throws Exception 
	*/
	protected static Vector extractJSONArray(String pSqlString, String[] pParameter,int page,int rows) throws SQLException, Exception {
		//ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
		pSqlString = "select * from (" + pSqlString + ")";//��װsql����ֹ��Ϊ�汾���⵼��ordey by����
		Vector returnV = new Vector();//�����ҳ�����
		Connection conn = null;//�������ݿ�����
		PreparedStatement pstmt = null;//ִ�в�������ѯ
		ResultSet rs = null;//��ȡ�����
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();
			pstmt = conn.prepareStatement(pSqlString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//�������
				}
			}
			rs = pstmt.executeQuery();//ִ�в�ѯ
		
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();//��ȡ����
			int iRow=0;
			rs.last();
			int row = rs.getRow();//��ȡ������
		
			rs.beforeFirst();
			if(rows!=0  && page != 1){
				rs.absolute((page-1)*rows);
			}
			JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();

			if(columns != 0){
				while (rs.next()&&(iRow<rows || rows==0)) {//���������
					for (int i = 1; i <= columns; i++) {
						if("CONTENTCLOB".equals(rsmd.getColumnLabel(i))){//����clob���͵���
							  Clob  clob = rs.getClob("CONTENTCLOB");    
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
						}else{
							mapOfColValues.put(rsmd.getColumnLabel(i), rs.getObject(i));
						}
					}
					++iRow;
					array.add(mapOfColValues);
				}
			}
			returnV.add(String.valueOf(row));//������
			returnV.add("rows");
			returnV.add(array);//�����
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally {
			try{//�ر�����
				rs.close();
				pstmt.close();
				conn.close();	
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return returnV;
	}

	/**
	* ���������ҳת�ܳ�JSON,����JSONArray,����������
	* 
	* @param rs
	* @return
	* @throws SQLException
	 * @throws Exception 
	*/
	protected static Vector extractComboArray(String pSqlString, String[] pParameter,String id,String name) throws SQLException, Exception {
		//ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
		pSqlString = "select * from (" + pSqlString + ")";
		Vector returnV = new Vector();//��������
		Connection conn = null;//�������ݿ�����
		PreparedStatement pstmt = null;//ִ�в�������ѯ
		ResultSet rs = null;//��ȡ�����
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();//��OracleConnectionService���ӳ��л�ȡ����
			pstmt = conn.prepareStatement(pSqlString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//�������
				}
			}
			rs = pstmt.executeQuery();//ִ��sql
			JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();
			while (rs.next()) {
				mapOfColValues.put(id, rs.getObject(1));//������id
				mapOfColValues.put(name, rs.getObject(2));//������value
				array.add(mapOfColValues);
			}
			returnV.add(array);//��������������
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		finally {
			try{
				rs.close();
				pstmt.close();
				conn.close();	
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return returnV;
	}
	
	/**
	 * �������ݿ�
	 * @param pSqlString
	 * @param pParameter
	 * @param pParameterNum
	 * @return ��������
	 * @throws Exception
	 */
	public static int Update(String pSqlString, String[] pParameter) throws Exception
	{
		int updatecount = 0;
		Connection conn = null;//�������ݿ�����
		PreparedStatement pstmt = null;//ִ�в���������
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();//��OracleConnectionService���ӳ��л�ȡ����
			pstmt = conn.prepareStatement(pSqlString);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//�������
				}
			}
			updatecount = pstmt.executeUpdate();//ִ��sql
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally {
			try{//�ر�����
				pstmt.close();
				conn.close();	
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return updatecount;
	}
	
	/*!
	 * ��ȡ���һ��insert�ļ�¼��ID��š�
	 */
	public static String GetLastInsertId()
	{
		return mLastInsertId;
	}
	
	/*
	 * ������ת��string�ö��ŷָ�
	 */
	public static final String ArrayToString(String[] pArray)
	{
		if (pArray == null) {
			return "";
		}
		
		String valuelist = "";
		for (String value : pArray) {
			if (valuelist != "") {
				valuelist += ",";
			}
			valuelist += value;
		}
		return valuelist;
	}
	
	/*
	 * ��Arrayת��string�ö��ŷָ�
	 */
	public static final String ArrayToString(ArrayList<String> pArray)
	{
		if (pArray == null) {
			return "";
		}
		
		String valuelist = "";
		for (String value : pArray) {
			if (valuelist != "") {
				valuelist += ",";
			}
			valuelist += value;
		}
		return valuelist;
	}
}
