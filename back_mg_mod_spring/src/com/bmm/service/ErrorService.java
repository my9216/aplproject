package com.bmm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bmm.dao.Dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("ErrorService")
public class ErrorService extends SqlService {

	@Resource(name = "dao")
	private Dao dao;

	/**
	 * ͳ��Error���������
	 * 
	 * @param error
	 *            ��ѯʵ����
	 * @return result JSONObject���Ͳ�ѯ���
	 */
	public JSONObject getStatistics(com.bmm.entity.Error error) {

		// ��ʼ��SQL���
		String sql = S_SQL_ERROR_STATISTICS;
		// ��ʼ����ز�ѯ����
		String modules = error.getModules();
		String startDate = error.getStartDate();
		String stopDate = error.getStopDate();

		// ������ز�����SQL
		String[] param = null;
		param = new String[1];
		param[0] = modules;
		// �ж��Ƿ���ɸѡ,����ж�Ӧ�Ĳ�ѯ����,�򽫽���ѯ����ƴ��SQL����WHERE������
		if (!"".equals(startDate)) {
			sql += " AND to_char(LOG_DATE,'yyyy-MM-dd') >= '" + startDate + "'";
		}
		if (!"".equals(stopDate)) {
			sql += " AND to_char(LOG_DATE,'yyyy-MM-dd') <= '" + stopDate + "'";
		}

		// ��ѯ
		List list = dao.selectList(sql, param);
		// ����ѯ���Ľ����ת��Ϊ JSONObject
		JSONObject result = JSONObject.fromObject(list.get(0));
		return result;
	}

	/**
	 * ��ѯ������Ϣ
	 * 
	 * @param error
	 *            ��ѯʵ����
	 * @return JSONArray���Ͳ�ѯ�����
	 * @throws Exception
	 */
	public JSONObject getErrorList(com.bmm.entity.Error error, int rows, int page) throws Exception {

		// ��ʼ��SQL���
		String sql = S_SQL_ERROR_SEARCH;
		// ��ʼ����ز�ѯ����
		String fileName = error.getFilename();
		String modules = error.getModules();
		String startDate = error.getStartDate();
		String stopDate = error.getStopDate();

		// ������ز�����SQL
		String[] param = null;
		param = new String[2];
		param[0] = modules;
		// filename���Դ�Сд,ͳһ��Сд��ѯ
		param[1] = "%" + fileName.toLowerCase() + "%";
		// �ж��Ƿ���ɸѡ,����ж�Ӧ�Ĳ�ѯ����,�򽫽���ѯ����ƴ��SQL����WHERE������
		if (!"".equals(startDate)) {
			sql += " AND to_char(CREATE_DATE,'yyyy-MM-dd') >= '" + startDate + "'";
		}
		if (!"".equals(stopDate)) {
			sql += " AND to_char(CREATE_DATE,'yyyy-MM-dd') <= '" + stopDate + "'";
		}
		// ��ѯ��䰴������ʱ�䵹������
		sql += " order by CREATE_DATE desc ";

		// ��ѯ
		JSONObject queryResult = dao.selectListPage(sql, param, rows, page);
		//List list = (List) queryResult.get("List");
		// ����ѯ���Ľ����ת��Ϊ JSONArray
		//JSONArray result = JSONArray.fromObject(list);
		return queryResult;
	}
}
