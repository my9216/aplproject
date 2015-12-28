package com.apl.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.apl.util.Logger;
import com.bean.message.MessageBean;

import net.sf.json.JSONArray;

/**
 * 获取放箱列表
 */
public class MessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(MessageServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {

		}
		response.setContentType("text/html;charset=UTF-8");
		String slno = request.getParameter("slno");//集装箱号
		String ctnno = request.getParameter("ctnno");//箱号
		String serialnum = request.getParameter("serialnum");//流水号
		String type = request.getParameter("type");//类型（放箱/取消）
		String status = request.getParameter("status");//状态（成功/失败）
		String startdate = request.getParameter("startdate");//开始时间
		String stopdate = request.getParameter("stopdate");//结束时间
		
		int page = Integer.parseInt(request.getParameter("page"));//当前页数
		int rows = Integer.parseInt(request.getParameter("rows"));//每页行数
		
		Vector<?> jsonV = null; //返回结果集
		JSONArray jal = null; //返回的json对象
		try {
			jsonV = MessageBean.GetMessageList(slno,ctnno,serialnum,type,status,startdate,stopdate,page,rows);
			PrintWriter pw=response.getWriter();
			if (jsonV != null && jsonV.size() > 0) {
				//最终处理：传回AJAX 结果集
				jal = (JSONArray)jsonV.get(2);	
				//System.out.println(jal.toString());
				pw.print("{\"total\":" +jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
				//response.getWriter().write("{\"total\":" +jsonV.get(0) + ",\"rows\":" + jal.toString() + "}");
			}else{
				pw.print("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
