package com.apl.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.apl.sipg.util.PropertiesUtil;

public class TransferInterface {
	
	public static String postXMLtoSipg(String content, String kind){
		Properties p = PropertiesUtil.getProperties("aplProperties.properties");
		String smsUrl = p.getProperty(kind + "URL");
		//定义一个HttpClient对象
		HttpClient httpclient = new DefaultHttpClient();
		//建立HttpPost对象，参数为要调用的 Web API地址
		HttpPost httppost = new HttpPost(smsUrl);
		String conResult = null;
		try{
			//定义一个简单名称值对节点类型的List，将要发送的参数放在该List中
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//XML文本内容编码为BASE64
			content = encodeString(content);
			//将获取到的XML文件内容放入到List中，该Web API只解析name为xmlmsg的请求内容，名称一定要定义为xmlmsg
			nameValuePairs.add(new BasicNameValuePair("xmlmsg", content));
			//添加请求表头
			httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
			//添加请求内容，编码为utf-8
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
			//通过HttpClient发送POST请求，接受相应
			HttpResponse response = httpclient.execute(httppost);
			if(response.getStatusLine().getStatusCode()==200){
				conResult = EntityUtils.toString(response.getEntity());
			}else{
				conResult = "request failed";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			conResult = "timeout";
		} finally {
			return conResult;
		}
	}

	private static String encodeString(String content) {
		Base64 b64 =new Base64();
		byte[] byteArray = content.getBytes();
	    return new String(b64.encode(byteArray));
	}
}