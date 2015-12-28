package com.traffic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.traffic.bean.ManagerBean;
import com.traffic.util.Logger;
import net.sf.json.JSONArray;

/**
 * ����bl#��Χ�༭����
 * @author Administrator
 *
 */
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		String action = request.getParameter("action");// ��ȡ��������
		ManagerBean bean = new ManagerBean();
		loaddata(bean, request);//����ǰ̨�������
		Vector<?> jsonV = null; // ���ؽ����
		JSONArray jal = null; // ���ص�json����
		if ("loadlist".equals(action)) {//������״�����Ϣ
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));//��ǰҳ��
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));//����
			try {
				jsonV = bean.GetLoadlist(page, rows);//��ȡ��ҳ����
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// ���մ�������AJAX �����
					jal = (JSONArray) jsonV.get(2);
					for(int i=0;i<jal.size();i++){
						Vector<?> subjsonV = null;
						JSONArray subjal = null; // ���ص�json����
						String PARENTID = jal.getJSONObject(i).getString("ID");//��ȡ���ڵ�id
						bean.setType(request.getParameter("type"));
						bean.setParentid(PARENTID);
						subjsonV = bean.GetLoadPartentlist();//���ݸ��ڵ�id��ȡ�ӽڵ�����
						if (subjsonV != null && subjsonV.size() > 0) {
							subjal =  (JSONArray) subjsonV.get(2);
							jal.getJSONObject(i).put("children",subjal);//ƴ�ӳ���״�������
							jal.getJSONObject(i).put("state","closed");
						}
					}
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");//����ǰ̨
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		if ("loaddefaultlist".equals(action)) {//���ر༭�����Ϣ
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));
			bean.setType(request.getParameter("type"));
			try {
				jsonV = bean.GetLoaddefaultlist(page, rows);
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
				// System.out.println(e.getMessage());
			}
		}
		
		if ("loadlocation".equals(action)) {//����local����
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));

			try {
				jsonV = bean.GetLoadlocation(page, rows);
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
				// System.out.println(e.getMessage());
			}
		}
		if ("loadbp".equals(action)) {//����bp#����
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));

			try {
				jsonV = bean.GetLoadbp(page, rows);
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
				// System.out.println(e.getMessage());
			}
		}
		if("loadeditorign".equals(action)){//����orign����
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));

			try {
				jsonV = bean.Geteditorign(page, rows);
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
				// System.out.println(e.getMessage());
			}
		}
		if ("getlocation".equals(action)) {//����location������
			try {
				jsonV = bean.GetLocationList();
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
				// System.out.println(e.getMessage());
			}
		}
		if ("getbpcode".equals(action)) {//����bp#������
			try {
				jsonV = bean.GetBpcodeList();
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
				// System.out.println(e.getMessage());
			}
		}
		if ("getorign".equals(action)) {//����orign������
			try {
				jsonV = bean.GetOrignList();
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
		}
		if ("editlocation".equals(action)) {//����޸�location��Ϣ
			String[] codes = request.getParameterValues("codes");
			for (int i = 0; i < codes.length; i++) {//ѭ������ǰ̨�������
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code+"editid");
				if ("".equals(editid)) {// ִ��insert����
					try {
						bean.setLevel("3");
						bean.addlocation("");
						bean.setLevel("4");
						bean.addlocation("default");// ����location��Ĭ����Ϣ
					} catch (Exception e) {	
						logger.error(e.getMessage());
					} // ����location��Ϣ
				} else {
					try {
						bean.setLevel("3");
						if("default".equals(code)){
							bean.updatedefault(request.getParameter(code+"editid"));
						}else{
							bean.updatelocation(request.getParameter(code+"editid"));
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		if ("editbp".equals(action)) {//����޸�bp#��Ϣ
			String[] codes = request.getParameterValues("codes");
			for (int i = 0; i < codes.length; i++) {//ѭ������ǰ̨�������
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code+"editid");
				if ("".equals(editid)) {// ִ��insert����
					try {
						bean.setLevel("4");//���bussiness party��Ϣ���ȼ�Ϊ4
						bean.addbp("");
						bean.setLevel("5");//���bussiness party�µ�default��Ϣ���ȼ�Ϊ5
						bean.addbp("default");
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {//�޸�
					try {
						bean.setLevel("4");
						if("default".equals(code)){
							bean.updatedefault(request.getParameter(code+"editid"));
						}else{
							bean.updatebp(editid);
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
			
		}
		if ("editorign".equals(action)) {//����޸�orign��Ϣ
			String[] codes = request.getParameterValues("codes");
			bean.setLevel("5");
			for (int i = 0; i < codes.length; i++) {//ѭ������ǰ̨�������
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code + "editid");
				if ("".equals(editid)) {// ִ��insert����
					try {
						bean.addorign();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {
					try {
						bean.setLevel("5");
						if("default".equals(code)){
							bean.updatedefault(request.getParameter(code+"editid"));
						}else{
							bean.updateorign(editid);
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		if ("remove".equals(action)) {//ɾ����Ϣ
			String[] IDS = request.getParameterValues("listid");
			try {
				bean.remove(IDS);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	/*
	 * ��ʼ��bean����
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
