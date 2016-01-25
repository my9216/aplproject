package com.bmm.traffic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmm.traffic.bean.ManagerBean;
import com.bmm.traffic.util.Logger;

import net.sf.json.JSONArray;

/**
 * ����bl#��Χ�༭����
 * 
 * @author Administrator
 *
 */
public class ManagerServlet extends HttpServlet {

	protected Logger logger = Logger.getLogger(this.getClass());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");

		// ��ʼ����ؽ����
		Vector<?> jsonV = null;
		JSONArray jal = null;

		// ��ȡ��������
		String action = request.getParameter("action");
		ManagerBean bean = new ManagerBean();
		// ����ǰ̨�������
		loaddata(bean, request);

		// ������״�����Ϣ
		if ("loadlist".equals(action)) {
			int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));// ��ǰҳ��
			int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));// ����
			try {
				// ��ȡ��ҳ����
				jsonV = bean.getLoadList(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(2);
					for (int i = 0; i < jal.size(); i++) {
						Vector<?> subjsonV = null;
						// ���ص�json����
						JSONArray subjal = null;
						// ��ȡ���ڵ�id
						String PARENTID = jal.getJSONObject(i).getString("ID");
						bean.setType(request.getParameter("type"));
						bean.setParentid(PARENTID);
						// ���ݸ��ڵ�id��ȡ�ӽڵ�����
						subjsonV = bean.getLoadChildList(0, 0);
						if (subjsonV != null && subjsonV.size() > 0) {
							subjal = (JSONArray) subjsonV.get(2);
							// ƴ�ӳ���״�������
							jal.getJSONObject(i).put("children", subjal);
							jal.getJSONObject(i).put("state", "closed");
						}
					}
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");// ����ǰ̨
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ���ر༭�����Ϣ
		} else if ("loaddefaultlist".equals(action)) {
			int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));
			bean.setType(request.getParameter("type"));
			try {
				jsonV = bean.getLoadChildList(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(2);
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����local����
		} else if ("loadlocation".equals(action)) {
			int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));

			try {
				jsonV = bean.getLoadlocation(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(2);
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����bp#����
		} else if ("loadbp".equals(action)) {
			int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));

			try {
				jsonV = bean.loadBusinessPartner(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(2);
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����orign����
		} else if ("loadeditorign".equals(action)) {
			int page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows") == null ? "0" : request.getParameter("rows"));

			try {
				jsonV = bean.loadOrigin(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(2);
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����location������
		} else if ("getlocation".equals(action)) {
			try {
				jsonV = bean.getLocationCombo();
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(0);
					pw.print(jal.toString());
				} else {
					pw.print("");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����bp#������
		} else if ("getbpcode".equals(action)) {
			try {
				jsonV = bean.getBpCombo();
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(0);
					pw.print(jal.toString());
				} else {
					pw.print("");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����orign������
		} else if ("getorign".equals(action)) {
			try {
				jsonV = bean.getOriginCombo();
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(0);
					pw.print(jal.toString());
				} else {
					pw.print("");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			// ����޸�location��Ϣ
		} else if ("editlocation".equals(action)) {
			String[] codes = request.getParameterValues("codes");
			// ѭ������ǰ̨�������
			for (int i = 0; i < codes.length; i++) {
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code + "editid");
				if ("".equals(editid)) {
					// ִ��insert����
					try {
						bean.setLevel("3");
						bean.addLocation("");
						bean.setLevel("4");
						// ����location��Ĭ����Ϣ
						bean.addLocation("default");
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {
					// ����location��Ϣ
					try {
						bean.setLevel("3");
						if ("default".equals(code)) {
							bean.updateDefault(request.getParameter(code + "editid"));
						} else {
							bean.updateLocation(request.getParameter(code + "editid"));
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
			// ����޸�bp#��Ϣ
		} else if ("editbp".equals(action)) {
			String[] codes = request.getParameterValues("codes");
			// ѭ������ǰ̨�������
			for (int i = 0; i < codes.length; i++) {
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code + "editid");
				// ִ��insert����
				if ("".equals(editid)) {
					try {
						// ���bussiness party��Ϣ���ȼ�Ϊ4
						bean.setLevel("4");
						bean.addbp("");
						// ���bussiness party�µ�default��Ϣ���ȼ�Ϊ5
						bean.setLevel("5");
						bean.addbp("default");
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					// �޸�
				} else {
					try {
						bean.setLevel("4");
						if ("default".equals(code)) {
							bean.updateDefault(request.getParameter(code + "editid"));
						} else {
							bean.updatebp(editid);
						}

					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
			// ����޸�orign��Ϣ
		} else if ("editorign".equals(action)) {
			String[] codes = request.getParameterValues("codes");
			bean.setLevel("5");
			// ѭ������ǰ̨�������
			for (int i = 0; i < codes.length; i++) {
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code + "editid");
				// ִ��insert����
				if ("".equals(editid)) {
					try {
						bean.addorign();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {
					try {
						bean.setLevel("5");
						if ("default".equals(code)) {
							bean.updateDefault(request.getParameter(code + "editid"));
						} else {
							bean.updateorign(editid);
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
			// ɾ����Ϣ
		} else if ("remove".equals(action)) {
			String[] IDS = request.getParameterValues("listid");
			try {
				bean.remove(IDS);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * ��ȡ��������ز��������뵽bean��
	 * 
	 * @param bean
	 *            ManagerBean����
	 * @param request
	 *            �������
	 */
	public void loaddata(ManagerBean bean, HttpServletRequest request) {
		bean.setLocationcode(request.getParameter("locationcode"));
		bean.setCode(request.getParameter("code"));
		bean.setBpcode(request.getParameter("bpcode"));
		bean.setLevel(request.getParameter("level"));
		bean.setParentid(request.getParameter("parentid"));
		bean.setParentcode(request.getParameter("parentcode"));
		bean.setWavemax(request.getParameter("wavemax"));
		bean.setWavemin(request.getParameter("wavemin"));
		bean.setNowavemax(request.getParameter("nowavemax"));
		bean.setNowavemin(request.getParameter("nowavemin"));
	}
}
