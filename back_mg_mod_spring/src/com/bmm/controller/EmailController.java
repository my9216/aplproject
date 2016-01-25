package com.bmm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bmm.entity.Email;
import com.bmm.service.EmailService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class EmailController extends BaseController {

	@Resource(name = "EmailService")
	private EmailService service;

	/**
	 * ��ȡ�ʼ��б�
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/Email", method = RequestMethod.GET)
	public void getEmailList(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String type = request.getParameter("type");
		String address = request.getParameter("address");
		String status = request.getParameter("status");
		String startDate = request.getParameter("startdate");
		String stopDate = request.getParameter("stopdate");
		
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));

		// ����ѯ������ʼ����entity��
		Email email = new Email();
		email.setMailType(type);
		email.setAddress(address);
		email.setStatus(status);
		email.setStartDate(startDate);
		email.setStopDate(stopDate);

		// ��ʼ�������
		JSONArray statistics = null;
		JSONObject result = null;

		try {
			// ��ȡ�ʼ���Ϣ�б�
			result = service.getEmailList(email, rows, page);
			// ��ȡ�ʼ�����ͳ��
			statistics = service.getStatistics(email);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		if (result != null && result.size() > 0) {
			pw.print("{\"total\":" + result.get("Count") + ",\"rows\":" + result.get("List") + ",\"Statistics\":"
					+ statistics.toString() + "}");
		} else {
			pw.print("{\"total\":0,\"rows\":[],\"Statistics\":" + statistics.toString() + "}");
		}
	}

	/**
	 * ���·����ʼ�������ѡ�е�����id��ԭ�����ʼ����ݸ���һ���µĳ�����״̬��Ϊwait
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/Email", method = RequestMethod.POST)
	public void resend(HttpServletRequest request) throws Exception {

		// ��ȡ��Ӧ�������
		String ids = request.getParameter("ids");
		try {
			// ����ids���Ʋ����²����µ��ʼ���״̬��Ϊwait
			service.updateEmailStatus(ids);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	/**
	 * ��ʱˢ�½����ʼ�����
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/EmailStatus", method = RequestMethod.POST)
	public void refreshStatus(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String type = request.getParameter("type");
		String address = request.getParameter("address");
		String status = request.getParameter("status");
		String startDate = request.getParameter("startdate");
		String stopDate = request.getParameter("stopdate");
		String ids = request.getParameter("ids");

		// ����ѯ������ʼ����entity��
		Email email = new Email();
		email.setMailType(type);
		email.setAddress(address);
		email.setStatus(status);
		email.setStartDate(startDate);
		email.setStopDate(stopDate);

		// ��ʼ�������
		JSONArray statistics = null;
		JSONArray result = null;

		try {
			// ��ȡ�ʼ���Ϣ�б�
			result = service.refreshStatus(ids);
			// ��ȡ�ʼ�����ͳ��
			statistics = service.getStatistics(email);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		if (result != null && result.size() > 0) {
			pw.print("{\"total\":" + result.size() + ",\"rows\":" + result.toString() + ",\"Statistics\":"
					+ statistics.toString() + "}");
		} else {
			pw.print("{\"total\":0,\"rows\":[],\"Statistics\":" + statistics.toString() + "}");
		}
	}
}