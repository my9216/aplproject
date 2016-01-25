package test.jms.jmsdemo;

import javax.jms.BytesMessage;
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.MessageListener;  
import javax.jms.TextMessage;  

public class ConsumerMessageListener implements MessageListener {  
   
    public void onMessage(Message message) {  
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换  
    	System.out.println("in.........");
    	String responseXml = null;
    	if(message instanceof BytesMessage){
            BytesMessage bm = (BytesMessage) message;  
            byte[] bys = null;  
            try {  
                bys = new byte[(int) bm.getBodyLength()];  
                bm.readBytes(bys);  
                responseXml = new String(bys);  
            } catch (JMSException e) {  
                e.printStackTrace();  
            }
        	System.out.println(responseXml);
        }else{  
            TextMessage bm = (TextMessage) message;
            try {  
                responseXml = bm.getText(); 
                System.out.println(responseXml);
            } catch (JMSException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
    
    }  
   
}  
