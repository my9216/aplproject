package com.apl.upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.apl.network.TransferInterface;
import com.apl.util.Generatexml;
import com.apl.util.Logger;
import com.bean.message.MessageBean;

/*
 * 处理上传文件
 */
public class UploadHandleServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(UploadHandleServlet.class);
    public ArrayList<String> headlist = new ArrayList<String>();
    public ArrayList<String> contentlist = new ArrayList<String>();
    public ArrayList<String> footlist = new ArrayList<String>();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
		String file = this.Savefile(request, response);
		readFileByLines(file);
        Map<String,Map<String,String>> head = resolveheadorfoot(headlist);//拆分后的头文件
        ArrayList<JSONObject> content = resolvecontent(contentlist);//拆分后的文件内容
        Map<String,Map<String,String>> foot =  resolveheadorfoot(footlist);//拆分后的尾文件
        
        Generatexml gen = new Generatexml();
        ArrayList<String> serialnum = new ArrayList<String>();//流水号
        ArrayList<String> ctn = new ArrayList<String>();//箱号
        ArrayList<String> bl = new ArrayList<String>();//提单号
        ArrayList<String> vessel = new ArrayList<String>();//船名
        ArrayList<String> voyage = new ArrayList<String>();//航次
        ArrayList<String> status = new ArrayList<String>();//状态
		for(int i=0;i<content.size();i++){
			ArrayList<String> str = new ArrayList<String>();
			try {
				str = gen.createxml(head,content.get(i),foot);//生成放箱的xml
				if (str != null) {
					String xml = str.get(0);
					serialnum.add(str.get(1));
					bl.add(str.get(2));
					ctn.add(str.get(3));
					vessel.add(str.get(4));
					voyage.add(str.get(5));
					status.add(TransferInterface.postXMLtoSipg(xml, "CTNREL"));//进行放箱操作并获取状态
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				response.getWriter().write("fail");
				logger.error(e.getMessage());
				return;
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				response.getWriter().write("fail");
				logger.error(e.getMessage());
				return;
			}
		}
		MessageBean bean = new MessageBean();
		try {
			bean.insert(serialnum,bl,ctn,head,content,foot,status,vessel,voyage);//插入放箱数据
			response.getWriter().write("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	 }         
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
     	throws ServletException, IOException {
		 doGet(request, response);
	 }
	 
	 /**
	     * 以行为单位读取文件，常用于读面向行的格式化文件
	     */
	 public void readFileByLines(String fileName) {
		System.out.println("fileName============="+fileName);
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            headlist = new ArrayList<String>();
            contentlist = new ArrayList<String>();
            footlist = new ArrayList<String>();
            String tempString = null;
            for(int i=0;i<3;i++){
            	tempString = reader.readLine();
            	headlist.add(tempString);
            } 
            while ((tempString = reader.readLine()) != null) {
            	  String line = tempString.substring(0,2);
            	  if(line.equals("99")){
            		  footlist.add(tempString);
            		  break;
            	  }
            	  contentlist.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	 }
	 
	 /**
	  * 拆分尾文件
	  * @param headlistorfoot
	  * @return
	  */
	 public Map<String,Map<String,String>> resolveheadorfoot(ArrayList<String> headlistorfoot){
		 //ArrayList<Map<String,Map<String,String>>> resolveheadlists = new ArrayList<Map<String,Map<String,String>>>();
		 Map<String,Map<String,String>> resolveheadmap = new LinkedHashMap<String,Map<String,String>>();
		 for(int i=0;i<headlistorfoot.size();i++){
			 Map<String,String> mapinfo = new LinkedHashMap<String,String>();
			 String value = headlistorfoot.get(i);
			 String line = value.substring(0,2);
			 String[] values = value.split("(?<=[^?]|[?][?]):");
			 for(int j=0;j<values.length;j++){
				 DecimalFormat df = new DecimalFormat("00");
				 mapinfo.put(df.format(j), values[j]);
			 }
			 //resolveheadlist.add(map);
			 
			 resolveheadmap.put(line, mapinfo);
			 //resolveheadlists.add(map2);
		 }
		 //JSONObject jsonobject = JSONObject.fromObject(resolveheadmap);
		 //System.out.println(jsonobject);
		 return resolveheadmap;
	 }
	    
	 /**
	  * 拆分文件操作
	  * @param contentlist
	  * @return
	  */
	 public  ArrayList<JSONObject> resolvecontent(ArrayList<String> contentlist){
		 ArrayList<JSONObject> resolvecontentmaplists = new ArrayList<JSONObject>();
		 Map<String,Map<String,String>> resolvecontentmap = new LinkedHashMap<String,Map<String,String>>();
		 
		 for(int i=0;i<contentlist.size();i++){
			 Map<String,String> mapinfo = new LinkedHashMap<String,String>();
			 String value = contentlist.get(i);
			 String line = value.substring(0,2);
			 String[] values = value.split("(?<=[^?]|[?][?]):");
			 if("12".equals(line)){
				 resolvecontentmap = new LinkedHashMap<String,Map<String,String>>();
			 }
			 for(int j=0;j<values.length;j++){
				 DecimalFormat df = new DecimalFormat("00");
				 mapinfo.put(df.format(j), values[j]);
			 }
			 resolvecontentmap.put(line, mapinfo);
			 if("51".equals(line)){
				 JSONObject jsonobject = JSONObject.fromObject(resolvecontentmap);
				 resolvecontentmaplists.add(jsonobject);
			 }
			 //resolveheadlists.add(map2);
		 }
		 //JSONArray jsonArray = JSONArray.fromObject(resolvecontentmaplists);
		 //System.out.println(jsonArray);
		 return resolvecontentmaplists;
	 }
	 
	 /**
	  * 保存上传的文件
	  * @param request
	  * @param response
	  * @return
	  */
	 public String Savefile(HttpServletRequest request, HttpServletResponse response){
         //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
         String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
         File file = new File(savePath);
         //判断上传文件的保存目录是否存在
         if (!file.exists() && !file.isDirectory()) {
             //创建目录
             file.mkdir();
         }
         //消息提示
         String message = "";
         String filename = "";
         try{
             //使用Apache文件上传组件处理文件上传步骤：
             //1、创建一个DiskFileItemFactory工厂
             DiskFileItemFactory factory = new DiskFileItemFactory();
             //2、创建一个文件上传解析器
             ServletFileUpload upload = new ServletFileUpload(factory);
              //解决上传文件名的中文乱码
             upload.setHeaderEncoding("UTF-8"); 
             //3、判断提交上来的数据是否是上传表单的数据
             //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
             List<FileItem> list = upload.parseRequest(request);
             for(FileItem item : list){
                 //如果fileitem中封装的是普通输入项的数据
                 if(item.isFormField()){
               	  filename = item.getFieldName();
                     //解决普通输入项的数据的中文乱码问题
                     String value = item.getString("UTF-8");
                     //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                     System.out.println(filename + "=" + value);
                 }else{//如果fileitem中封装的是上传文件
                     //得到上传的文件名称，
                     filename = item.getName();
                     System.out.println(filename);
                     if(filename==null || filename.trim().equals("")){
                         continue;
                     }
                     //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                     //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                     filename = filename.substring(filename.lastIndexOf("\\")+1);
                     //获取item中的上传文件的输入流
                     InputStream in = item.getInputStream();
                     //创建一个文件输出流
                     FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                     //创建一个缓冲区
                     byte buffer[] = new byte[1024];
                     //判断输入流中的数据是否已经读完的标识
                     int len = 0;
                     //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                     while((len=in.read(buffer))>0){
                         //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                         out.write(buffer, 0, len);
                    }
                     //关闭输入流
                     in.close();
                     //关闭输出流
                     out.close();
                     //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                 }
            }
           
           // 
        }catch (Exception e) {
             message= "文件上传失败！";
             logger.error(e.getMessage());
        }
        return savePath + "\\" + filename;
	 }
}
