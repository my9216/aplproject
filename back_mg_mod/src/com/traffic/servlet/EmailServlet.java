package com.traffic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.traffic.bean.EmailBean;
import com.traffic.util.Logger;

import net.sf.json.JSONArray;

public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EmailServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");// 获取操作类型
		
		if("getlist".equals(action)){
			String type = request.getParameter("type");
			String address = request.getParameter("address");
			String state = request.getParameter("state");
			String startdate = request.getParameter("startdate");
			String stopdate = request.getParameter("stopdate");
			
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			
			Vector<?> jsonV = null; //返回结果集
			JSONArray jal = null; //返回的json对象
			JSONArray Statistics = null; //返回的json对象
			try {
				jsonV = EmailBean.GetStatisticsinfo(type, address, state, startdate,stopdate);
				Statistics=(JSONArray)jsonV.get(2);
				jsonV = EmailBean.GetEmailList(type, address, state, startdate, stopdate, page, rows);
				PrintWriter pw=response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					//最终处理：传回AJAX 结果集
					jal = (JSONArray)jsonV.get(2);	
					pw.print("{\"total\":" +jsonV.get(0) + ",\"rows\":" + jal.toString() + ",\"Statistics\":" + Statistics.toString() + "}");
					//response.getWriter().write("{\"total\":" +jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
				}else{
					pw.print("{\"total\":0,\"rows\":[],\"Statistics\":"+ Statistics.toString()+"}");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//Logger logger = Logger.GetLogger();
				logger.error(e.getMessage());
			}
		}
		
		if("refreshstate".equals(action)){
			String[] IDS = request.getParameterValues("IDS");
			String type = request.getParameter("type");
			String address = request.getParameter("address");
			String state = request.getParameter("state");
			String startdate = request.getParameter("startdate");
			String stopdate = request.getParameter("stopdate");
			Vector<?> jsonV = null; //返回结果集
			JSONArray jal = null; //返回的json对象
			JSONArray Statistics = null; //返回的json对象
			try {
				jsonV = EmailBean.GetStatisticsinfo(type, address, state, startdate,stopdate);
				Statistics=(JSONArray)jsonV.get(2);
				jsonV = EmailBean.GetRefreshstate(IDS);
				PrintWriter pw=response.getWriter();
				if (jsonV != null && jsonV.size() > 0) {
					//最终处理：传回AJAX 结果集
					jal = (JSONArray)jsonV.get(2);
					pw.print("{\"rows\":" + jal.toString() + ",\"Statistics\":" + Statistics.toString() + "}");
				}else{
					pw.print("");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//Logger logger = Logger.GetLogger();
				logger.error(e.getMessage());
			}
		}
		
		if("resend".equals(action)){
			String[] IDS = request.getParameterValues("IDS");
			try {
			  EmailBean.UpdateState("wait", IDS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//Logger logger = Logger.GetLogger();
				logger.error(e.getMessage());
			}
		}
		
		if("edit".equals(action)){
			String ID = request.getParameter("emailid");
			String address = request.getParameter("editaddress");
			try {
				 EmailBean.UpdateAddress("wait", address, ID);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

}
