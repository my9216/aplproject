package com.traffic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.traffic.bean.ErrorListBean;
import com.traffic.util.Logger;

import net.sf.json.JSONArray;

public class ErrorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ErrorListServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		String filename = request.getParameter("filename");
		String startdate = request.getParameter("startdate");
		String stopdate = request.getParameter("stopdate");
		String MODULES = request.getParameter("MODULES");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Vector<?> jsonV = null; //返回结果集
		JSONArray jal = null; //返回的json对象
		JSONArray Statistics = null; //返回的json对象
		try {
			jsonV = ErrorListBean.GetStatisticsinfo(MODULES,startdate,stopdate);
			Statistics=(JSONArray)jsonV.get(2);	
			jsonV = ErrorListBean.GetErrorList(filename,startdate,stopdate,MODULES,page,rows);
			PrintWriter pw=response.getWriter();
			if (jsonV != null && jsonV.size() > 0) {
				//最终处理：传回AJAX 结果集
				jal = (JSONArray)jsonV.get(2);	
				pw.print("{\"total\":" +jsonV.get(0) + ",\"rows\":" + jal.toString() + ",\"Statistics\":" + Statistics.get(0).toString() + "}");
				//response.getWriter().write("{\"total\":" +jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
			}else{
				pw.print("{\"total\":0,\"rows\":[],\"Statistics\":"+ Statistics.get(0).toString()+"}");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
