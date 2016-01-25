package com.cncsi.mq;
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
  
import com.ibm.mq.MQC;  
import com.ibm.mq.MQEnvironment;  
import com.ibm.mq.MQException;  
import com.ibm.mq.MQGetMessageOptions;  
import com.ibm.mq.MQMessage;  
import com.ibm.mq.MQPutMessageOptions;  
import com.ibm.mq.MQQueue;  
import com.ibm.mq.MQQueueManager;  
    
public class CLIENT_MQ{    
     //定义队列管理器和队列的名称    
     private static final String qmName = "test";//MQ的队列管理器名称 ;     
     //private static final String qName = "MIDDLE_SEND_QUEUE"; //MQ远程队列的名称    
     private static MQQueueManager qMgr;//队列管理器  
     public  static void init(){    
         //设置环境:    
         //MQEnvironment中包含控制MQQueueManager对象中的环境的构成的静态变量，MQEnvironment的值的设定会在MQQueueManager的构造函数加载的时候起作用，    
         //因此必须在建立MQQueueManager对象之前设定MQEnvironment中的值.    
         MQEnvironment.hostname="127.0.0.1";          //MQ服务器的IP地址          
         MQEnvironment.channel="service";           //通道类型:服务器连接  
         MQEnvironment.CCSID=1381;//437                    //服务器MQ服务使用的编码1381代表GBK、1208代表UTF(Coded Character Set Identifier:CCSID)    
         MQEnvironment.port=1414;                       //MQ端口    
         try {    
             //定义并初始化队列管理器对象并连接     
             //MQQueueManager可以被多线程共享，但是从MQ获取信息的时候是同步的，任何时候只有一个线程可以和MQ通信。    
            qMgr = new MQQueueManager(qmName);    
        } catch (MQException e) {    
            // TODO Auto-generated catch block    
            System.out.println("初使化MQ出错");    
            e.printStackTrace();    
        }     
     }    
     /**  
      * 往MQ发送消息  
      * @param message  
      * @return  
      */    
     public static Map<String,Object> sendMessage(Object message,String qName){      
         Map<String,Object> map=new HashMap<String,Object>();  
         try{       
             //设置将要连接的队列属性    
             // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface     
             //(except for completion code constants and error code constants).    
             //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.    
             //MQOO_OUTPUT:Open the queue to put messages.    
             /*目标为远程队列，所有这里不可以用MQOO_INPUT_AS_Q_DEF属性*/    
             //int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;    
             /*以下选项可适合远程队列与本地队列*/    
             //int openOptions = MQC.MQOO_INQUIRE | MQC.MQOO_OUTPUT; //发送时使用  
             //int qOptioin = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 接收时使用  
             int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;    
             //连接队列     
             //MQQueue provides inquire, set, put and get operations for WebSphere MQ queues.     
             //The inquire and set capabilities are inherited from MQManagedObject.     
             /*关闭了就重新打开*/    
            if(qMgr==null || !qMgr.isConnected()){    
                qMgr = new MQQueueManager(qmName);    
            }    
             MQQueue queue = qMgr.accessQueue(qName, openOptions);              
             //定义一个简单的消息    
             MQMessage putMessage = new MQMessage();  
             map.put("messageId",putMessage);  
             //String uuid=java.util.UUID.randomUUID().toString();  
             //将数据放入消息缓冲区    
             putMessage.writeObject(message);      
             //设置写入消息的属性（默认属性）    
             MQPutMessageOptions pmo = new MQPutMessageOptions();   
              
             //将消息写入队列     
             queue.put(putMessage,pmo);   
             map.put("message",message.toString());  
             queue.close();    
         }catch (MQException ex) {     
             System.out.println("A WebSphere MQ error occurred : Completion code "   + ex.completionCode + " Reason code " + ex.reasonCode);     
             ex.printStackTrace();    
         }catch (IOException ex) {     
             System.out.println("An error occurred whilst writing to the message buffer: " + ex);     
         }catch(Exception ex){    
             ex.printStackTrace();    
         }finally{    
             try {    
                qMgr.disconnect();    
            } catch (MQException e) {    
                e.printStackTrace();    
            }    
          }    
         return map;    
     }    
       
       
        
  
       
     /**  
      * 处理完消息回放到MQ队列  
      * @param message  
      * @return  
      */    
     public static Map<String,Object> sendReplyMessage(Object message,String qName,MQMessage mqMessage){      
         Map<String,Object> map=new HashMap<String,Object>();  
         try{       
             //设置将要连接的队列属性    
             // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface     
             //(except for completion code constants and error code constants).    
             //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.    
             //MQOO_OUTPUT:Open the queue to put messages.    
             /*目标为远程队列，所有这里不可以用MQOO_INPUT_AS_Q_DEF属性*/    
             //int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;    
             /*以下选项可适合远程队列与本地队列*/    
             //int openOptions = MQC.MQOO_INQUIRE | MQC.MQOO_OUTPUT; //发送时使用  
             //int qOptioin = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 接收时使用  
             int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;    
             //连接队列     
             //MQQueue provides inquire, set, put and get operations for WebSphere MQ queues.     
             //The inquire and set capabilities are inherited from MQManagedObject.     
             /*关闭了就重新打开*/    
            if(qMgr==null || !qMgr.isConnected()){    
                qMgr = new MQQueueManager(qmName);    
            }    
             MQQueue queue = qMgr.accessQueue(qName, openOptions);              
             //定义一个简单的消息    
             MQMessage putMessage = new MQMessage();  
             putMessage.messageId=mqMessage.messageId;  
             map.put("messageId",putMessage);  
             //String uuid=java.util.UUID.randomUUID().toString();  
             //将数据放入消息缓冲区    
             putMessage.writeObject(message);      
             //设置写入消息的属性（默认属性）    
             MQPutMessageOptions pmo = new MQPutMessageOptions();   
              
             //将消息写入队列     
             queue.put(putMessage,pmo);   
             map.put("message",message.toString());  
             queue.close();    
         }catch (MQException ex) {     
             System.out.println("A WebSphere MQ error occurred : Completion code "   + ex.completionCode + " Reason code " + ex.reasonCode);     
             ex.printStackTrace();    
         }catch (IOException ex) {     
             System.out.println("An error occurred whilst writing to the message buffer: " + ex);     
         }catch(Exception ex){    
             ex.printStackTrace();    
         }finally{    
             try {    
                qMgr.disconnect();    
            } catch (MQException e) {    
                e.printStackTrace();    
            }    
          }    
         return map;    
     }    
       
       
       
       
     /**  
      * 从队列中去获取消息，如果队列中没有消息，就会发生异常，不过没有关系，有TRY...CATCH，如果是第三方程序调用方法，如果无返回则说明无消息  
      * 第三方可以将该方法放于一个无限循环的while(true){...}之中，不需要设置等待，因为在该方法内部在没有消息的时候会自动等待。  
      * @return  
      */    
     public static String getMessage(String qName,MQMessage mqMessage){    
         String message="";    
         try{                
             //设置将要连接的队列属性    
             // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface     
             //(except for completion code constants and error code constants).    
             //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.    
             //MQOO_OUTPUT:Open the queue to put messages.    
             //int qOptioin = MQC.MQOO_INQUIRE | MQC.MQOO_OUTPUT; 发送时使用  
             //int qOptioin = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; 接收时使用  
               
             int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;    
             MQMessage retrieve = new MQMessage();    
             //设置取出消息的属性（默认属性）    
             //Set the put message options.（设置放置消息选项）     
             MQGetMessageOptions gmo = new MQGetMessageOptions();     
               
             gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT;//Get messages under sync point control（在同步点控制下获取消息）     
             gmo.options = gmo.options + MQC.MQGMO_WAIT;  // Wait if no messages on the Queue（如果在队列上没有消息则等待）     
             gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING;// Fail if Qeue Manager Quiescing（如果队列管理器停顿则失败）     
             gmo.waitInterval = 3000 ;  // Sets the time limit for the wait.（设置等待的毫秒时间限制）     
             /*关闭了就重新打开*/    
            if(qMgr==null || !qMgr.isConnected()){    
                qMgr = new MQQueueManager(qmName);    
            }    
             MQQueue queue = qMgr.accessQueue(qName, openOptions);    
               
             MQMessage retrievedMessage = new MQMessage();  
             //从队列中取出对应messageId的消息  
             retrieve.messageId = mqMessage.messageId;   
             // 从队列中取出消息  
             queue.get(retrieve, gmo);    
               
              
             Object obj = retrieve.readObject();  
             message=obj.toString();//解决中文乱码问题  
             /* 
      
             //int size = rcvMessage.getMessageLength(); 
             //byte[] p = new byte[size]; 
             //rcvMessage.readFully(p); 
              
             int len=retrieve.getDataLength(); 
             byte[] str = new byte[len]; 
              retrieve.readFully(str,0,len); 
              message = new String(str);//readUTF();     
             */  
             
             queue.close();    
         }catch (MQException ex) {  
             int reason=ex.reasonCode;  
             if(reason==2033)//no messages  
             {  
                message="nomessage";  
             }else{  
                System.out.println("A WebSphere MQ error occurred : Completion code " + ex.completionCode + " Reason code " + ex.reasonCode);     
             }  
         }catch (IOException ex) {     
             System.out.println("An error occurred whilst writing to the message buffer: " + ex);     
         }catch(Exception ex){    
             ex.printStackTrace();    
         }finally{    
            try {    
                qMgr.disconnect();    
            } catch (MQException e) {    
                e.printStackTrace();    
            }    
         }    
         return message;    
     }    
  
  
       
     public static void main(String args[]) {    
         init();  
         Map<String,Object> map = new HashMap<String,Object>();  
         map=sendMessage("fffff","test1");  
         MQMessage mqMessage = (MQMessage)map.get("messageId");  
         outSys("传输消息:",mqMessage.messageId.toString());    
           
         outSys("接收传输队列:",getMessage("test1",mqMessage));  
         Map<String,Object> reply_map = new HashMap<String,Object>();  
         reply_map=sendReplyMessage("{name: local queue 008}","test1",mqMessage);  
         outSys("放入正常队列:",reply_map.get("message").toString());  
           
         outSys("接收正常队列:",getMessage("test1",mqMessage));  
           
           
     }  
       
       
       
     public static void outSys(String display,String val){  
         System.out.println(display+val);  
     }  
       
}   