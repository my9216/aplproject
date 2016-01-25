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
     //������й������Ͷ��е�����    
     private static final String qmName = "test";//MQ�Ķ��й��������� ;     
     //private static final String qName = "MIDDLE_SEND_QUEUE"; //MQԶ�̶��е�����    
     private static MQQueueManager qMgr;//���й�����  
     public  static void init(){    
         //���û���:    
         //MQEnvironment�а�������MQQueueManager�����еĻ����Ĺ��ɵľ�̬������MQEnvironment��ֵ���趨����MQQueueManager�Ĺ��캯�����ص�ʱ�������ã�    
         //��˱����ڽ���MQQueueManager����֮ǰ�趨MQEnvironment�е�ֵ.    
         MQEnvironment.hostname="127.0.0.1";          //MQ��������IP��ַ          
         MQEnvironment.channel="service";           //ͨ������:����������  
         MQEnvironment.CCSID=1381;//437                    //������MQ����ʹ�õı���1381����GBK��1208����UTF(Coded Character Set Identifier:CCSID)    
         MQEnvironment.port=1414;                       //MQ�˿�    
         try {    
             //���岢��ʼ�����й�������������     
             //MQQueueManager���Ա����̹߳������Ǵ�MQ��ȡ��Ϣ��ʱ����ͬ���ģ��κ�ʱ��ֻ��һ���߳̿��Ժ�MQͨ�š�    
            qMgr = new MQQueueManager(qmName);    
        } catch (MQException e) {    
            // TODO Auto-generated catch block    
            System.out.println("��ʹ��MQ����");    
            e.printStackTrace();    
        }     
     }    
     /**  
      * ��MQ������Ϣ  
      * @param message  
      * @return  
      */    
     public static Map<String,Object> sendMessage(Object message,String qName){      
         Map<String,Object> map=new HashMap<String,Object>();  
         try{       
             //���ý�Ҫ���ӵĶ�������    
             // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface     
             //(except for completion code constants and error code constants).    
             //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.    
             //MQOO_OUTPUT:Open the queue to put messages.    
             /*Ŀ��ΪԶ�̶��У��������ﲻ������MQOO_INPUT_AS_Q_DEF����*/    
             //int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;    
             /*����ѡ����ʺ�Զ�̶����뱾�ض���*/    
             //int openOptions = MQC.MQOO_INQUIRE | MQC.MQOO_OUTPUT; //����ʱʹ��  
             //int qOptioin = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; ����ʱʹ��  
             int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;    
             //���Ӷ���     
             //MQQueue provides inquire, set, put and get operations for WebSphere MQ queues.     
             //The inquire and set capabilities are inherited from MQManagedObject.     
             /*�ر��˾����´�*/    
            if(qMgr==null || !qMgr.isConnected()){    
                qMgr = new MQQueueManager(qmName);    
            }    
             MQQueue queue = qMgr.accessQueue(qName, openOptions);              
             //����һ���򵥵���Ϣ    
             MQMessage putMessage = new MQMessage();  
             map.put("messageId",putMessage);  
             //String uuid=java.util.UUID.randomUUID().toString();  
             //�����ݷ�����Ϣ������    
             putMessage.writeObject(message);      
             //����д����Ϣ�����ԣ�Ĭ�����ԣ�    
             MQPutMessageOptions pmo = new MQPutMessageOptions();   
              
             //����Ϣд�����     
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
      * ��������Ϣ�طŵ�MQ����  
      * @param message  
      * @return  
      */    
     public static Map<String,Object> sendReplyMessage(Object message,String qName,MQMessage mqMessage){      
         Map<String,Object> map=new HashMap<String,Object>();  
         try{       
             //���ý�Ҫ���ӵĶ�������    
             // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface     
             //(except for completion code constants and error code constants).    
             //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.    
             //MQOO_OUTPUT:Open the queue to put messages.    
             /*Ŀ��ΪԶ�̶��У��������ﲻ������MQOO_INPUT_AS_Q_DEF����*/    
             //int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;    
             /*����ѡ����ʺ�Զ�̶����뱾�ض���*/    
             //int openOptions = MQC.MQOO_INQUIRE | MQC.MQOO_OUTPUT; //����ʱʹ��  
             //int qOptioin = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; ����ʱʹ��  
             int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;    
             //���Ӷ���     
             //MQQueue provides inquire, set, put and get operations for WebSphere MQ queues.     
             //The inquire and set capabilities are inherited from MQManagedObject.     
             /*�ر��˾����´�*/    
            if(qMgr==null || !qMgr.isConnected()){    
                qMgr = new MQQueueManager(qmName);    
            }    
             MQQueue queue = qMgr.accessQueue(qName, openOptions);              
             //����һ���򵥵���Ϣ    
             MQMessage putMessage = new MQMessage();  
             putMessage.messageId=mqMessage.messageId;  
             map.put("messageId",putMessage);  
             //String uuid=java.util.UUID.randomUUID().toString();  
             //�����ݷ�����Ϣ������    
             putMessage.writeObject(message);      
             //����д����Ϣ�����ԣ�Ĭ�����ԣ�    
             MQPutMessageOptions pmo = new MQPutMessageOptions();   
              
             //����Ϣд�����     
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
      * �Ӷ�����ȥ��ȡ��Ϣ�����������û����Ϣ���ͻᷢ���쳣������û�й�ϵ����TRY...CATCH������ǵ�����������÷���������޷�����˵������Ϣ  
      * ���������Խ��÷�������һ������ѭ����while(true){...}֮�У�����Ҫ���õȴ�����Ϊ�ڸ÷����ڲ���û����Ϣ��ʱ����Զ��ȴ���  
      * @return  
      */    
     public static String getMessage(String qName,MQMessage mqMessage){    
         String message="";    
         try{                
             //���ý�Ҫ���ӵĶ�������    
             // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface     
             //(except for completion code constants and error code constants).    
             //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.    
             //MQOO_OUTPUT:Open the queue to put messages.    
             //int qOptioin = MQC.MQOO_INQUIRE | MQC.MQOO_OUTPUT; ����ʱʹ��  
             //int qOptioin = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT; ����ʱʹ��  
               
             int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;    
             MQMessage retrieve = new MQMessage();    
             //����ȡ����Ϣ�����ԣ�Ĭ�����ԣ�    
             //Set the put message options.�����÷�����Ϣѡ�     
             MQGetMessageOptions gmo = new MQGetMessageOptions();     
               
             gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT;//Get messages under sync point control����ͬ��������»�ȡ��Ϣ��     
             gmo.options = gmo.options + MQC.MQGMO_WAIT;  // Wait if no messages on the Queue������ڶ�����û����Ϣ��ȴ���     
             gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING;// Fail if Qeue Manager Quiescing��������й�����ͣ����ʧ�ܣ�     
             gmo.waitInterval = 3000 ;  // Sets the time limit for the wait.�����õȴ��ĺ���ʱ�����ƣ�     
             /*�ر��˾����´�*/    
            if(qMgr==null || !qMgr.isConnected()){    
                qMgr = new MQQueueManager(qmName);    
            }    
             MQQueue queue = qMgr.accessQueue(qName, openOptions);    
               
             MQMessage retrievedMessage = new MQMessage();  
             //�Ӷ�����ȡ����ӦmessageId����Ϣ  
             retrieve.messageId = mqMessage.messageId;   
             // �Ӷ�����ȡ����Ϣ  
             queue.get(retrieve, gmo);    
               
              
             Object obj = retrieve.readObject();  
             message=obj.toString();//���������������  
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
         outSys("������Ϣ:",mqMessage.messageId.toString());    
           
         outSys("���մ������:",getMessage("test1",mqMessage));  
         Map<String,Object> reply_map = new HashMap<String,Object>();  
         reply_map=sendReplyMessage("{name: local queue 008}","test1",mqMessage);  
         outSys("������������:",reply_map.get("message").toString());  
           
         outSys("������������:",getMessage("test1",mqMessage));  
           
           
     }  
       
       
       
     public static void outSys(String display,String val){  
         System.out.println(display+val);  
     }  
       
}   