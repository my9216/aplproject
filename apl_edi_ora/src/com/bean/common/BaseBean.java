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

import com.apl.util.Logger;
import com.service.OracleConnectionService;


public class BaseBean {
	private static String mLastInsertId = "";
	
	private static Logger logger = Logger.getLogger(BaseBean.class);
	
	/**
	 * 查询数据库
	 * @param pSqlString
	 * @param pParameter
	 * @param pParameterNum
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<ArrayList<String>> Query(String pSqlString, String[] pParameter) throws Exception
	{
		ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();//保存结果集
		Connection conn = null;//建立数据库连接
		PreparedStatement pstmt = null;//执行参数话查询
		ResultSet rs = null;//获取结果集
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();//从OracleConnectionService连接池中获取连接
			pstmt = conn.prepareStatement(pSqlString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//导入查询参数
				}
			}
			rs = pstmt.executeQuery();//执行查询
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			if(columns != 0){
				while(rs.next()){//遍历结果集
					ArrayList<String> row = new ArrayList<String>();
					for(int i=0; i<columns;i++){//遍历每一行的列
						row.add(rs.getString(i+1));
					}
					dataset.add(row);//添加每行数据
				}
			}
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally {
			try{//关闭连接
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
	* 将获得的结果集进行分页，返回List
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
	* 将结果集分页转很成JSON,返回JSONArray
	* 
	* @param rs
	* @return 结果集
	* @throws SQLException
	 * @throws Exception 
	*/
	protected static Vector extractJSONArray(String pSqlString, String[] pParameter,int page,int rows) throws SQLException, Exception {
		//ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
		pSqlString = "select * from (" + pSqlString + ")";//封装sql语句防止因为版本问题导致ordey by出错
		Vector returnV = new Vector();//保存分页结果集
		Connection conn = null;//建立数据库连接
		PreparedStatement pstmt = null;//执行参数化查询
		ResultSet rs = null;//获取结果集
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();
			pstmt = conn.prepareStatement(pSqlString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//放入参数
				}
			}
			rs = pstmt.executeQuery();//执行查询
		
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();//获取列数
			int iRow=0;
			rs.last();
			int row = rs.getRow();//获取总行数
		
			rs.beforeFirst();
			if(rows!=0  && page != 1){
				rs.absolute((page-1)*rows);
			}
			JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();

			if(columns != 0){
				while (rs.next()&&(iRow<rows || rows==0)) {//遍历结果集
					for (int i = 1; i <= columns; i++) {
						if("CONTENTCLOB".equals(rsmd.getColumnLabel(i))){//处理clob类型的列
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
			returnV.add(String.valueOf(row));//总行数
			returnV.add("rows");
			returnV.add(array);//结果集
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally {
			try{//关闭连接
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
	* 将结果集分页转很成JSON,返回JSONArray,用于下拉框
	* 
	* @param rs
	* @return
	* @throws SQLException
	 * @throws Exception 
	*/
	protected static Vector extractComboArray(String pSqlString, String[] pParameter,String id,String name) throws SQLException, Exception {
		//ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
		pSqlString = "select * from (" + pSqlString + ")";
		Vector returnV = new Vector();//保存结果集
		Connection conn = null;//建立数据库连接
		PreparedStatement pstmt = null;//执行参数化查询
		ResultSet rs = null;//获取结果集
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();//从OracleConnectionService连接池中获取连接
			pstmt = conn.prepareStatement(pSqlString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//导入参数
				}
			}
			rs = pstmt.executeQuery();//执行sql
			JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();
			while (rs.next()) {
				mapOfColValues.put(id, rs.getObject(1));//下拉框id
				mapOfColValues.put(name, rs.getObject(2));//下拉框value
				array.add(mapOfColValues);
			}
			returnV.add(array);//保存下拉框数据
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
	 * 更新数据库
	 * @param pSqlString
	 * @param pParameter
	 * @param pParameterNum
	 * @return 更新行数
	 * @throws Exception
	 */
	public static int Update(String pSqlString, String[] pParameter) throws Exception
	{
		int updatecount = 0;
		Connection conn = null;//建立数据库连接
		PreparedStatement pstmt = null;//执行参数化操作
		try {
			conn = OracleConnectionService.GetInstance().GetConnection();//从OracleConnectionService连接池中获取连接
			pstmt = conn.prepareStatement(pSqlString);
			if (pParameter != null) {
				for (int i=1; i<=pParameter.length; i++) {
					pstmt.setString(i, pParameter[i-1]);//导入参数
				}
			}
			updatecount = pstmt.executeUpdate();//执行sql
		}
		catch (SQLException e) {
			logger.error(e.getMessage());
		}
		finally {
			try{//关闭连接
				pstmt.close();
				conn.close();	
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return updatecount;
	}
	
	/*!
	 * 获取最近一次insert的记录的ID编号。
	 */
	public static String GetLastInsertId()
	{
		return mLastInsertId;
	}
	
	/*
	 * 将数组转成string用逗号分割
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
	 * 将Array转成string用逗号分割
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
