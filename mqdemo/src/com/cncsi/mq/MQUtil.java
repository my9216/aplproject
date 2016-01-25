package com.cncsi.mq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQTopic;

/*=================================================== 
 * MQUtil.java 2008-5-6 下午05:09:54 卢渔美 
* @author <a href = "andytea@163.com">andytea</a> 
 *=================================================== 
 * 
 */
public class MQUtil extends Thread {

	// private static IDWLLogger logger =
	// DWLLoggerManager.getLogger(MQUtil.class);

	String qName = null; // 队列名 通过参数传递
	String qmgName = null; // 队列管理器名 通过参数传递
	private MQQueueManager mqmenager; // 队列管理器
	private MQQueue mqqueue; // 队列

	public MQQueueManager getMqmenager() {
		return mqmenager;
	}

	public void setMqmenager(MQQueueManager mqmenager) {
		this.mqmenager = mqmenager;
	}

	public MQQueue getMqqueue() {
		return mqqueue;
	}

	public void setMqqueue(MQQueue mqqueue) {
		this.mqqueue = mqqueue;
	}

	/**
	 * @param hostname
	 *            连接MQ地址
	 * @param key_qname
	 *            队列名称
	 * @param key_qmgname
	 *            队列管理器名称
	 * @param read
	 *            打开队列方式标记：接收/浏览消息，0/null--接收 1--浏览
	 * @param port
	 *            端口
	 */
	@SuppressWarnings("deprecation")
	public MQUtil(String hostname, String key_qname, String key_qmgname, int read, String port) throws Exception {

		/* 设置 MQEnvironment 属性以便客户机连接,通过 TCRMProperties 从配置文件获取 */

		MQEnvironment.hostname = "rintbmr4"; // 队列管理器所在主机的主机名，不区分大小写
		MQEnvironment.channel = "Q2C.SVRCONN"; // 客户机连接通道名（一般就是队列管理器下面服务器连接通道的名称）
		MQEnvironment.port = Integer.parseInt("6914");
		MQEnvironment.CCSID = Integer.parseInt("1381");// 1381表示是简体中文，
		// MQEnvironment.hostname = TCRMProperties.getProperty(hostname); //
		// 队列管理器所在主机的主机名，不区分大小写
		// MQEnvironment.channel = TCRMProperties.getProperty("channel"); //
		// 客户机连接通道名（一般就是队列管理器下面服务器连接通道的名称）
		// MQEnvironment.port =
		// Integer.parseInt(TCRMProperties.getProperty(port));
		// MQEnvironment.CCSID =
		// Integer.parseInt(TCRMProperties.getProperty("CCSID"));// 1381表示是简体中文，
		// CCSID的值在AIX上一般设为1383，如果要支持GBK则设为1386，在WIN上设为1381。
		//  MQQueueManager queueManager= new MQQueueManager("QM_xx_2");
         // MQTopic topic = queueManager.accessTopic("TOPIC_xx_2", "TOPIC_xx_2", CMQC.MQTOPIC_OPEN_AS_SUBSCRIPTION, CMQC.MQSO_CREATE);
		qName = "QLOCAL";
		qmgName = "RSTGBRKA";
		// qName = TCRMProperties.getProperty(key_qname); //队列名称
		// qmgName = TCRMProperties.getProperty(key_qmgname); //队列管理器名称

		// TODO
		System.out.println(MQEnvironment.hostname);
		System.out.println(MQEnvironment.channel);
		System.out.println(MQEnvironment.port);
		System.out.println(MQEnvironment.CCSID);
		System.out.println(qName);
		System.out.println(qmgName);
		// qName = "BK_QM";
		// qmgName = "QM_hqmpangyf7";
		// MQEnvironment.hostname = "192.168.10.103";
		// MQEnvironment.channel = "abc";
		// MQEnvironment.port = 1414;
		// MQEnvironment.CCSID=1381;

		/**
		 * 1 获得通道连接 2 打开队列 3 定义MQMessage对象，即缓冲区 4 定义MQPutMessageOptions放置，传递信息 5
		 * 往队列放入消息或者从队列取得消息
		 */
		int openOptions;
		if (read == 1) {// 浏览
			openOptions = MQC.MQOO_BROWSE | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
		} else {// 获取
			openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
		}
		// MQC.MQOO_BROWSE 阅读消息

		try {
			// MQConn()，通过队列管理器名获得连接。。。

			// 用于正常联接/MQC.MQCNO_FASTPATH_BINDING
			// 用于快速联接/MQC.MQCNO_STANDARD_BINDING
			int options = MQC.MQCNO_STANDARD_BINDING;
			mqmenager = new MQQueueManager(qmgName, options);
			
			mqqueue = mqmenager.accessQueue(qName, openOptions, null, null, null); // 通过队列名打开队列以便进行操作

			// 上面 mqqueue 作为 MQQueue
			// 的对象代表着队列，拥有有助于消息发送（即放置、获取、设置、查询）的方法和对应于队列属性的属性；
		} catch (MQException e) {
			// logger.fine("连接不上队列服务器主机 MQException:" + e.toString());
			System.out.println("连接不上队列服务器主机 MQException:" + e.toString());
		}

		/** 有两种方式打开队列： */
		// MQQueue Q2 =
		// mqmenager.accessQueue("qName",openOptions,qMgrName,dynamicQname,alternateUserId);
		// MQQueue Q3 = new MQQueue（qmgr, "qName", openOption,
		// "qMgrName","dynamicQname", "alternateUserId"）;
	}

	/*
	 * ==========================================================
	 * Method:finalizerCloseConnection 2008-7-5 下午05:13:10 卢渔美 TODO：
	 * 关闭连接每次调用完该类一定要记得调用该方法关闭连接
	 * =========================================================
	 */
	public void finalizerCloseConnection() {
		try {
			mqqueue.close();
			mqmenager.disconnect();
		} catch (MQException e) {
			System.out.println("A WebSphere MQ error occurred : Completion code " + e.completionCode
					+ " Reason Code is " + e.reasonCode);
			// logger.fine("A WebSphere MQ error occurred : Completion code " +
			// e.completionCode + " Reason Code is " + e.reasonCode);
		}
	}

	/*
	 * ==========================================================
	 * Method:sendMessage 2008-7-5 下午05:13:34 卢渔美 TODO： 往队列发送字符串消息
	 * =========================================================
	 * 
	 * @param message 要发送到队列里的消息 String 类型
	 */
	public void sendMessage(String message) {

		try {
			// MQMessage 的对象 mqmessage 代表着将被放到队列上的或将从队列获取的消息，相当于一个款冲区
			MQMessage mqmessage = new MQMessage();
			mqmessage.characterSet = 1381;// 数字和字符串等数据类型格式通过设置该属性来控制,用于控制消息放置到队列的方式；

			mqmessage.writeString(message);// 用write***()方法写入消息
			// mqmessage.writeObject(message);

			/** 用MQPutMessageOptions放置，传递信息 */
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			// 可以设置选项字段的值，指令队列管理器为消息生成新的消息ID 并将其设为MQMD 的MsgId 字段
			// pmo.options = pmo.options + MQC.MQPMO_NEW_MSG_ID;
			mqqueue.put(mqmessage, pmo);

		} catch (MQException ex) {
			System.out.println(ex.toString());
			// logger.error("MQSeries Error:" + ex.completionCode + " Reason
			// code " + ex.reasonCode);
		} catch (IOException ie) {
			System.out.println(ie.toString());
			// logger.error(ie.toString());
		}

	}

	/*
	 * ==========================================================
	 * Method:sendMessage 2008-7-5 下午05:14:10 卢渔美 TODO： 往队列发送byte[]消息
	 * =========================================================
	 * 
	 * @param bytearray 要发送到队列里的消息
	 */
	public void sendMessage(byte[] bytearray) {

		try {
			// MQMessage的对象mqmessage代表着将被放到队列上的或将从队列获取的消息，相当于一个款冲区
			MQMessage mqmessage = new MQMessage();
			mqmessage.characterSet = 1383;// 数字和字符串等数据类型格式通过设置该属性来控制,用于控制消息放置到队列的方式；

			mqmessage.write(bytearray); // 用write***()方法写入消息

			/** 用MQPutMessageOptions放置，传递信息 */
			MQPutMessageOptions pmo = new MQPutMessageOptions();

			// 可以设置选项字段的值，指令队列管理器为消息生成新的消息ID 并将其设为MQMD 的MsgId 字段
			// pmo.options = pmo.options + MQC.MQPMO_NEW_MSG_ID;

			mqqueue.put(mqmessage, pmo);

		} catch (MQException ex) {
			System.out.println(ex.toString());
			// logger.error("MQSeries Error:" + ex.completionCode + " Reason
			// code " + ex.reasonCode);
		} catch (IOException ie) {
			// logger.error(ie.toString());
			System.out.println(ie.toString());
		}
	}

	public void GetMsg() {
        try {
            MQMessage retrievedMessage = getMQMessage();

            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options += MQC.MQPMO_SYNCPOINT;

            mqqueue.get(retrievedMessage, gmo);

            int length = retrievedMessage.getDataLength();

            byte[] msg = new byte[length];

            retrievedMessage.readFully(msg);

            String sMsg = new String(msg);
            System.out.println(sMsg);

        }catch (RuntimeException e){ 
            e.printStackTrace();
        }catch (MQException e) {
            if (e.reasonCode != 2033) //Ã»ÓÐÏûÏ¢
            {
                e.printStackTrace();
                System.out
                        .println("A WebSphere MQ error occurred : Completion code "
                                + e.completionCode
                                + " Reason Code is "
                                + e.reasonCode);
            }
        } catch (java.io.IOException e) {
            System.out
                    .println("An error occurred whilst to the message buffer "
                            + e);
        }
    }
	/*
	 * ==========================================================
	 * Method:receiveMessage 2008-7-5 下午05:14:47 卢渔美
	 * TODO：从队列里接收消息（接收一条会从队列里删除该条消息）
	 * =========================================================
	 * 
	 * @return 取得的消息
	 */
	public String receiveMessage() {
		String msgText = null;
		try {
			
			MQMessage mqmessage = new MQMessage();
			
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			
			// gmo.options=MQC.MQGMO_BROWSE_FIRST;
			gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT;//Get messages under sync point control（在同步点控制下获取消息）     
	         gmo.options = gmo.options + MQC.MQGMO_WAIT;  // Wait if no messages on the Queue（如果在队列上没有消息则等待）     
	        gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING;// Fail if Qeue Manager Quiescing（如果队列管理器停顿则失败）     
	        gmo.waitInterval = 3000 ;
	             // MQC.MQGMO_BROWSE_MSG_UNDER_CURSOR;
	       
			mqqueue.get(mqmessage,gmo);
			
			byte[] iii = new byte[mqmessage.getMessageLength()];
			mqmessage.readFully(iii); // 取出队列的消息
			msgText = new String(iii);
		} catch (java.io.IOException ie) {
			// logger.error(ie.toString());
			System.out.println(ie.toString());
		} catch (MQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (msgText == null || "".equals(msgText)) {
			return null;
		} else {
			return msgText;
		}
	}

	/*
	 * ==========================================================
	 * Method:getMQMessage 2008-7-5 下午05:15:14 卢渔美 TODO： 取得队列消息以供操作：接收，读取，删除
	 * =========================================================
	 * 
	 * @return MQMessage
	 */
	public MQMessage getMQMessage() {
		MQMessage retrievedMessage = new MQMessage();
		try {
			// MQMessage的对象retrievedMessage代表着将从队列获取的消息，相当于一个款冲区
			retrievedMessage.characterSet = 1383;// 数字和字符串等数据类型格式通过设置该属性来控制,用于控制消息放置到队列的方式；

			MQGetMessageOptions gmo = new MQGetMessageOptions();
			// gmo.options=MQC.MQGMO_BROWSE_FIRST;
			// gmo.options=MQC.MQGMO_CONVERT;
			gmo.options = MQC.MQGMO_BROWSE_NEXT;
			mqqueue.get(retrievedMessage, gmo);

			// mqqueue.get(retrievedMessage);
		} catch (MQException ex) {
			// logger.error(ex.completionCode + " Reason code " +
			// ex.reasonCode);
			System.out.println(ex.toString());
		}

		if (retrievedMessage == null || "".equals(retrievedMessage)) {
			return null;
		} else {
			return retrievedMessage;
		}
	}

	/*
	 * ==========================================================
	 * Method:getMessageCountByQ 2008-7-5 下午05:16:20 卢渔美 TODO： 获取队列上的消息条数
	 * =========================================================
	 * 
	 * @return int 返回消息条数
	 */
	public int getMessageCountByQ() {
		int messagecount = 0;
		try {
			messagecount = this.getMqqueue().getCurrentDepth();
		} catch (MQException e) {
			// logger.error(e.completionCode + " Reason code " + e.reasonCode);
			System.out.println(e.toString());
		}

		return messagecount;
	}

	/*
	 * ==========================================================
	 * Method:removeMessageFromQ 2008-7-5 下午05:16:46 卢渔美 TODO： 删除该条消息
	 * =========================================================
	 */
	public void removeMessageFromQ() throws Exception {
		MQUtil mqutil = new MQUtil("", "", "", 1, "");
		String text = mqutil.receiveMessage();
		System.out.println(text);
		// this.receiveMessage();
		//
		// MQMessage mqmessage = this.getMQMessage();
		// try {
		// mqmessage.clearMessage();
		//
		// } catch (IOException e) {
		// TODO Auto-generated catch block
		// this.getApplicationLogs().appLog("IOException Error:"+e.toString());
		// }

	}

	/*
	 * ==========================================================
	 * Method:getAllmessageList 2008-7-5 下午05:17:04 卢渔美 TODO： 取出队列里所有的消息
	 * =========================================================
	 * 
	 * @return List 消息集合
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public List getAllmessageList() {
		List messagelist = new ArrayList();
		Object object = null;
		
		int count = this.getMessageCountByQ();
		System.out.println("------------------------");
		for (int i = count; i > 0; i--) {
			String text = this.receiveMessage();
			messagelist.add(text);
		}

		return messagelist;
	}

	/*
	 * ==========================================================
	 * Method:sendWorkItemToMq 2008-7-5 下午05:18:20 卢渔美
	 * TODO：发送工作项到MQ供MB读取发送到DOMINO OA系统有发送工作项是调用
	 * =========================================================
	 * 
	 * @param workitemids 包含工作项ID的集合
	 */
	// public void sendWorkItemToMq(List workitemids) {
	//
	// OtherConfig oconfig = new OtherConfig();
	// ApplicationLogs log = new ApplicationLogs();
	// String ifsend = oconfig.getkeyvalue("ifsend_workitem");//
	// 配置文件里读取是否发送工作项到DOMINO的开关
	//
	// if (ifsend != null && !"".equals(ifsend) && "true".equals(ifsend)) {
	// SqlControl sqlControl = new SqlControl();
	// try {
	// if (workitemids.size() != 0) {
	// for (int i = 0; i < workitemids.size(); i++) {
	// String workid = (String) workitemids.get(i);
	// List list = sqlControl
	// .doResultList("select
	// ID,TITLE,NEW_TYPE_TASK_NAME,WORKITEM_PERSON_ID,OPEN_URL,FORM_URL,DOCUMENT_ID,PRO_INST_ID,DOC_TYPE,OPER_STATE,SOURCE_PERSON_NAME
	// from DOCM_WORKITEM_VIEW where ID="
	// + workid);
	// MqInputUtil inpututil = new MqInputUtil();
	// String xmlstr = inpututil.CreateXMLByList(list);
	//
	// log.appLog(xmlstr);
	// // this.sendMessage(xmlstr);
	// MQMessage mqmessage = new MQMessage();
	// mqmessage.characterSet = 1383;
	// mqmessage.writeString(xmlstr);
	// MQPutMessageOptions pmo = new MQPutMessageOptions();
	// mqqueue.put(mqmessage, pmo);
	// }
	// }
	// } catch (MQException ex) {
	// System.out.println(ex.toString());
	// logger.error("MQSeries Error:" + ex.completionCode + " Reason code " +
	// ex.reasonCode);
	// } catch (IOException ie) {
	// logger.error(ie.toString());
	// }
	// }
	// }

	public static void main(String[] args) throws Exception {

		//MQUtil mqutil = new MQUtil("", "", "", 1, "");
		//mqutil.GetMsg();
		
		//mqutil.sendMessage("test1111");
		
		//mqutil.finalizerCloseConnection();
		
		
		MQUtil mqutil2 = new MQUtil("hostname_gw", "qName_OAToDomino", "qManager_gw", 2, "port_gw");
		//System.out.println(mqutil2.receiveMessage());
		//
		//
		// /** 取得队列所有消息 */
		
		List list = mqutil2.getAllmessageList();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("list's size is:" + list.size());
		for (int i = list.size() - 1; i >= 0; i--) {
			String text = (String) list.get(i);
			System.out.println(text);
		}
		mqutil2.finalizerCloseConnection();
		
		

		
	}

}