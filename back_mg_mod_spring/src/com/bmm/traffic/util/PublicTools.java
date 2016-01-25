package com.bmm.traffic.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmm.bean.common.BaseBean;

public class PublicTools extends BaseBean {

	/**
	 * ��֤ҳ���Ƿ����
	 * 
	 * @param request	�������
	 * @param response	��Ӧ����
	 * @throws IOException
	 */
	public static void validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = (String) (request.getSession().getAttribute("userid") == null ? ""
				: request.getSession().getAttribute("userid"));
		if ("".equals(userid)) {
			String loginurl = request.getSession().getServletContext().getInitParameter("loginurl");
			response.sendRedirect(loginurl);
		}
	}

	/**
	 * ������ת��string�ö��ŷָ�
	 * 
	 * @param pArray	��Ҫת��������
	 * @return
	 */
	public static final String ArrayToString(String[] pArray) {
		if (pArray == null) {
			return "";
		}
		String valuelist = "";
		for (String value : pArray) {
			if (valuelist != "") {
				valuelist += ",";
			}
			valuelist += value;
		}
		return valuelist;
	}

	/**
	 * ��ArrayListתΪString��","�ָ�
	 * 
	 * @param pArray
	 *            ��Ҫת����ArrayList
	 * @return
	 */
	public static final String ArrayToString(ArrayList<String> pArray) {
		if (pArray == null) {
			return "";
		}
		String valuelist = "";
		for (String value : pArray) {
			if (valuelist != "") {
				valuelist += ",";
			}
			valuelist += value;
		}
		return valuelist;
	}
}
