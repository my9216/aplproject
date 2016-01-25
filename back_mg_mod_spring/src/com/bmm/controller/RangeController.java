package com.bmm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bmm.entity.Range;
import com.bmm.service.RangeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class RangeController extends BaseController {

	private Range range;
	@Resource(name = "RangeService")
	private RangeService service;

	/**
	 * ��ѯ��Χ��ҳ������
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/Range", method = RequestMethod.POST)
	public void getRangeList(HttpServletRequest request, HttpServletResponse response) {

		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		String level = request.getParameter("level");
		String editable = request.getParameter("editable");
		String local = (String) request.getSession().getAttribute("local");
		
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));

		// ����ѯ������ʼ����entity��
		range = new Range();
		range.setIsDelete(type);
		range.setCode(code);
		range.setLevel(level);
		range.setEditable(editable);
		range.setLocal(local);
		// ��ʼ�������
		JSONObject result = null;
		try {
			// ��ȡ��Χ����
			result = service.getRangeList(range, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		// ����ǰ̨
		pw.print("{\"total\":" + result.getString("Count") + ",\"rows\":" + result.getString("Data") + "}");
	}

	/**
	 * ��ѯ��Χ��ϸҳ������
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/RangeDetail", method = RequestMethod.GET)
	public void addRangeList(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String parentId = request.getParameter("parentId");
		String type = request.getParameter("type");
		int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));

		// ����ѯ������ʼ����entity��
		range = new Range();
		range.setParentId(parentId);
		range.setIsDelete(type);

		// ��ʼ�������
		JSONObject result = null;
		try {
			// ��ȡ������Χ����
			result = service.getSubRangeList(range, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		// ����ǰ̨
		pw.print("{\"total\":" + result.getString("Count") + ",\"rows\":" + result.getString("List") + "}");
	}

	/**
	 * ��ѯ����Location�µ�����BusinessPartner���س���������Ҫ�ĸ�ʽ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/BpCombo", method = RequestMethod.GET)
	public void getBpCombo(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String code = request.getParameter("code");

		// ��ʼ�������
		JSONArray result = null;
		// ��ѯ
		result = service.getBpCombo(code);
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		// ����ǰ̨
		pw.print(result.toString());
	}

	/**
	 * ��ѯ������Origin���س���������Ҫ�ĸ�ʽ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/OriginCombo", method = RequestMethod.GET)
	public void getOriginCombo(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ʼ�������
		JSONArray result = null;
		// ��ѯ
		result = service.getOriginCombo();
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		// ����ǰ̨
		pw.print(result.toString());
	}

	/**
	 * ��ѯ��BusinessPartner����ϸ��Χ����������ҳ����"+"��"edit"ʱ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/BusinessPartner", method = RequestMethod.GET)
	public void loadBusinessPartner(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String code = request.getParameter("code");

		// ����ѯ������ʼ����entity��
		range = new Range();
		range.setCode(code);

		// ��ʼ�������
		JSONArray result = null;
		// ��ѯ
		result = service.getBusinessPartner(range);
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.toString());
		}
		// ����ǰ̨
		pw.print("{\"rows\":" + result.toString() + "}");
	}

	/**
	 * ��ѯ��Origin����ϸ��Χ����������ҳ����"+"��"edit"ʱ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/Origin", method = RequestMethod.GET)
	public void loadOrigin(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String code = request.getParameter("code");
		String parentId = request.getParameter("parentId");

		// ����ѯ������ʼ����entity��
		range = new Range();
		range.setCode(code);
		range.setParentId(parentId);

		// ��ʼ�������
		JSONArray result = null;
		// ��ѯ
		result = service.getOrigin(code, parentId);
		// �����ҳ��
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����ǰ̨
		pw.print("{\"rows\":" + result.toString() + "}");
	}

	/**
	 * ������༭��Χ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/RangeDetail", method = RequestMethod.POST)
	public void updateRange(HttpServletRequest request, HttpServletResponse response) {
		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��Ӧ�������
		String level = request.getParameter("level");
		// ҳ���ϴ������� level + 1 ���ڱ������ӵķ�Χlevel
		level = Integer.valueOf(level) + 1 + "";
		String[] codes = request.getParameterValues("codes");
		List<Range> list = new ArrayList<Range>();

		// ѭ�����������codeֵ������ÿ��CODEֵ��ȡ��Ӧ��Χ����
		for (String code : codes) {
			Range range = new Range();
			range.setId(request.getParameter(code + "editid"));
			range.setCode(code);
			range.setLevel(level);
			range.setParentId(request.getParameter("parentid"));
			range.setNonWave1Min(request.getParameter(code + "nowavemin"));
			range.setNonWave1Max(request.getParameter(code + "nowavemax"));
			range.setNonWave1Last(request.getParameter(code + "nowavelast"));
			range.setWave1Min(request.getParameter(code + "wavemin"));
			range.setWave1Max(request.getParameter(code + "wavemax"));
			range.setWave1Last(request.getParameter(code + "wavelast"));
			// ��Range���ӵ�list��
			list.add(range);
		}
		// ִ��
		service.executeRange(list);
	}

	/**
	 * ɾ����Χ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/RangeDetail", method = RequestMethod.DELETE)
	public void deleteRange(HttpServletRequest request, HttpServletResponse response) {

		// ͳһrequest��response����
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}

		// ��ȡ��Ӧ�������
		String ids = request.getParameter("ids");
		// ɾ��
		service.deleteRange(ids);
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}