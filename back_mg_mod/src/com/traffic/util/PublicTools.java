package com.traffic.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.common.BaseBean;

public class PublicTools extends BaseBean{
	
	/**
	 * 验证页面是否登入
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void validate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userid = (String) (request.getSession().getAttribute("userid")==null?"":request.getSession().getAttribute("userid"));
		if("".equals(userid)){
			String loginurl = request.getSession().getServletContext().getInitParameter("loginurl");
			response.sendRedirect(loginurl);
		}
	}
}
