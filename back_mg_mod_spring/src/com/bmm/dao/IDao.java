package com.bmm.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import net.sf.json.JSONObject;

public interface IDao {
	
	/**
	 * ��������
	 * @param sql
	 * @param parameter
	 */
	public void update(String sql, String[] parameter);
	
	/**
	 * ������
	 * @param sql
	 * @param parameter
	 */
	public void updateBatch(String sql, BatchPreparedStatementSetter parameter);
	
	/**
	 * ����һ����ѯ���
	 * @param sql
	 * @param parameter
	 * @return
	 */
	public Object selectOne(String sql, String[] parameter);
	
	/**
	 * ���ز�ѯ�б�
	 * @param sql
	 * @param parameter
	 * @return
	 */
	public List selectList(String sql, String[] parameter);
	
	/**
	 * ���ز�ѯ�б���ҳ
	 * @param sql
	 * @param parameter
	 * @param rows
	 * @param page
	 * @return
	 */
	public JSONObject selectListPage(String sql, String[] parameter, int rows, int page);
}
