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
 * 处理bl#范围编辑操作
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
		String action = request.getParameter("action");// 获取操作类型
		ManagerBean bean = new ManagerBean();
		loaddata(bean, request);//导出前台传入参数
		Vector<?> jsonV = null; // 返回结果集
		JSONArray jal = null; // 返回的json对象
		if ("loadlist".equals(action)) {//加载树状表格信息
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));//当前页数
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));//行数
			try {
				jsonV = bean.GetLoadlist(page, rows);//获取分页数据
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
					jal = (JSONArray) jsonV.get(2);
					for(int i=0;i<jal.size();i++){
						Vector<?> subjsonV = null;
						JSONArray subjal = null; // 返回的json对象
						String PARENTID = jal.getJSONObject(i).getString("ID");//获取父节点id
						bean.setType(request.getParameter("type"));
						bean.setParentid(PARENTID);
						subjsonV = bean.GetLoadPartentlist();//根据父节点id获取子节点数据
						if (subjsonV != null && subjsonV.size() > 0) {
							subjal =  (JSONArray) subjsonV.get(2);
							jal.getJSONObject(i).put("children",subjal);//拼接出树状表格数据
							jal.getJSONObject(i).put("state","closed");
						}
					}
					pw.print("{\"total\":" + jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");//返回前台
				} else {
					pw.print("{\"total\":0,\"rows\":[]}");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		if ("loaddefaultlist".equals(action)) {//加载编辑表格信息
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));
			bean.setType(request.getParameter("type"));
			try {
				jsonV = bean.GetLoaddefaultlist(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
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
		
		if ("loadlocation".equals(action)) {//加载local详情
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));

			try {
				jsonV = bean.GetLoadlocation(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
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
		if ("loadbp".equals(action)) {//加载bp#详情
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));

			try {
				jsonV = bean.GetLoadbp(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
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
		if("loadeditorign".equals(action)){//加载orign详情
			int page = Integer.parseInt(request.getParameter("page")==null?"0":request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")==null?"0":request.getParameter("rows"));

			try {
				jsonV = bean.Geteditorign(page, rows);
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
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
		if ("getlocation".equals(action)) {//加载location下拉框
			try {
				jsonV = bean.GetLocationList();
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
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
		if ("getbpcode".equals(action)) {//加载bp#下拉框
			try {
				jsonV = bean.GetBpcodeList();
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
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
		if ("getorign".equals(action)) {//加载orign下拉框
			try {
				jsonV = bean.GetOrignList();
				PrintWriter pw = response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					// 最终处理：传回AJAX 结果集
					jal = (JSONArray) jsonV.get(0);
					pw.print(jal.toString());
				} else {
					pw.print("");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		if ("editlocation".equals(action)) {//添加修改location信息
			String[] codes = request.getParameterValues("codes");
			for (int i = 0; i < codes.length; i++) {//循环遍历前台传入参数
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code+"editid");
				if ("".equals(editid)) {// 执行insert操作
					try {
						bean.setLevel("3");
						bean.addlocation("");
						bean.setLevel("4");
						bean.addlocation("default");// 保存location的默认信息
					} catch (Exception e) {	
						logger.error(e.getMessage());
					} // 保存location信息
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
		if ("editbp".equals(action)) {//添加修改bp#信息
			String[] codes = request.getParameterValues("codes");
			for (int i = 0; i < codes.length; i++) {//循环遍历前台传入参数
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code+"editid");
				if ("".equals(editid)) {// 执行insert操作
					try {
						bean.setLevel("4");//添加bussiness party信息，等级为4
						bean.addbp("");
						bean.setLevel("5");//添加bussiness party下的default信息，等级为5
						bean.addbp("default");
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {//修改
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
		if ("editorign".equals(action)) {//添加修改orign信息
			String[] codes = request.getParameterValues("codes");
			bean.setLevel("5");
			for (int i = 0; i < codes.length; i++) {//循环遍历前台传入参数
				String code = codes[i];
				bean.setCode(code);
				bean.setWavemax(request.getParameter(code + "wavemax"));
				bean.setWavemin(request.getParameter(code + "wavemin"));
				bean.setNowavemax(request.getParameter(code + "nowavemax"));
				bean.setNowavemin(request.getParameter(code + "nowavemin"));
				bean.setWavelast(request.getParameter(code + "wavelast"));
				bean.setNowavelast(request.getParameter(code + "nowavelast"));
				String editid = request.getParameter(code + "editid");
				if ("".equals(editid)) {// 执行insert操作
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
		if ("remove".equals(action)) {//删除信息
			String[] IDS = request.getParameterValues("listid");
			try {
				bean.remove(IDS);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	/*
	 * 初始化bean复制
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
