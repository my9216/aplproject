package com.apl.ctnrel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.apl.cancel.CancelServlet;
import com.apl.network.TransferInterface;
import com.apl.util.Generatexml;
import com.apl.util.Logger;
import com.bean.message.MessageBean;

/**
 * 处理放箱操作
 */
public class CtnrelServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CtnrelServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
		}
		response.setContentType("text/html;charset=UTF-8");
        String[] cancelIdList = request.getParameterValues("cancelIdList");//获取放箱id
        String[] serialnumIdList = request.getParameterValues("serialnumIdList");//获取放箱流水号
        String[] ctnIdList = request.getParameterValues("ctnIdList");//获取放箱箱号
        String[] blIdList = request.getParameterValues("blIdList");//获取提单号
        String[] vesselIdList = request.getParameterValues("vesselIdList");//获取放箱船号
        String[] voyageIdList = request.getParameterValues("voyageIdList");//获取放箱航线号
        
        ArrayList<String> status = new ArrayList<String>();
        Generatexml gen = new Generatexml();
		for(int i=0;i<cancelIdList.length;i++){
			try {
				String xml = gen.createxml(serialnumIdList[i],ctnIdList[i],blIdList[i],vesselIdList[i],voyageIdList[i]);
				status.add(TransferInterface.postXMLtoSipg(xml, "CTNREL"));//执行放箱并获取状态
			} catch (ParserConfigurationException e) {
				logger.error(e.getMessage());
			} catch (TransformerException e) {
				logger.error(e.getMessage());
			}
		}
		MessageBean bean = new MessageBean();
		try {
			bean.UpdateMessage(cancelIdList,status,"CTNREL");//修改数据库为放箱状态
			//response.sendRedirect("list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}

}
