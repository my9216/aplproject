package com.apl.cancel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import com.apl.network.TransferInterface;
import com.apl.util.Generatexml;
import com.apl.util.Logger;
import com.bean.message.MessageBean;

/**
 * 处理取消放箱操作
 */
public class CancelServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CancelServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage());
		}
		response.setContentType("text/html;charset=UTF-8");

        String[] cancelIdList = request.getParameterValues("cancelIdList");//获取需要取消放箱的id
        String[] serialnumIdList = request.getParameterValues("serialnumIdList");//获取需要取消放箱的流水号
        
        ArrayList<String> status = new ArrayList<String>();
        Generatexml gen = new Generatexml();
		for(int i=0;i<cancelIdList.length;i++){//循环取消放箱
			try {
				String xml = gen.cancelxml(serialnumIdList[i]);//根据取消放箱的流水号拼接xml
				status.add(TransferInterface.postXMLtoSipg(xml, "CANCELREL"));//执行取消放箱并获取执行状态
			} catch (ParserConfigurationException e) {
				logger.error(e.getMessage());
			} catch (TransformerException e) {
				logger.error(e.getMessage());
			}
		}
		MessageBean bean = new MessageBean();
		try {
			bean.UpdateMessage(cancelIdList,status, "CANCELREL");//修改数据库中放箱的状态
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
