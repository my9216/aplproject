package com.traffic.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.traffic.bean.initBean;

import net.sf.json.JSONArray;

public class initServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().write("loading....");
		String userid = request.getParameter("userid") == null ? "13" : request.getParameter("userid");
		Vector<?> jsonV = null; // 返回结果集
		JSONArray jal = null; // 返回的json对象
		try {
			jsonV = initBean.getUserInfo(userid);
			if (jsonV != null && ((JSONArray) jsonV.get(2)).size() > 0) {
				jal = (JSONArray) jsonV.get(2);
				request.getSession().setAttribute("userid", userid);
				request.getSession().setAttribute("groupid", jal.getJSONObject(0).getString("GROUP_ID"));
				request.getSession().setAttribute("local", jal.getJSONObject(0).getString("OFFICE"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");// 登入成功跳转后台页面
				dispatcher.forward(request, response);
			} else {
				request.getSession().setAttribute("userid", userid);
				request.getSession().setAttribute("groupid", "");
				request.getSession().setAttribute("local", "");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");// 登入成功跳转后台页面
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
