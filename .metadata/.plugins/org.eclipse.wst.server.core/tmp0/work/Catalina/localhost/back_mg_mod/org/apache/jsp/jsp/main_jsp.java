package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("\t\t<title>APL</title>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/default/easyui.css?version=1\">\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/icon.css\">\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\t\t\tbody\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tmargin: 0;\r\n");
      out.write("\t\t\t\tborder: 0;\r\n");
      out.write("\t\t\t\theight: 100%;\r\n");
      out.write("\t\t\t\tmax-height: 100%;\r\n");
      out.write("\t\t\t\tbackground:#E9E9E9;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t.tabs li a.tabs-inner:before {\r\n");
      out.write("\t\t\t  position:absolute;\r\n");
      out.write("\t\t\t  content:\"\";\r\n");
      out.write("\t\t\t  display: inline-block;\r\n");
      out.write("\t\t\t  top:0px;\r\n");
      out.write("\t\t\t  left:1px;\r\n");
      out.write("\t\t\t  z-index:4;\r\n");
      out.write("\t\t\t  content:url(css/themes/default/images/splitline.png);\r\n");
      out.write("\t\t\t  background-image: -moz-linear-gradient(top, #A2B3C4, #778899);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t.tabs li a.tabs-inner:after {\r\n");
      out.write("\t\t\t  position:absolute;\r\n");
      out.write("\t\t\t  content:\"\";\r\n");
      out.write("\t\t\t  display: inline-block;\r\n");
      out.write("\t\t\t  top:0px;\r\n");
      out.write("\t\t\t  right:-3px;\r\n");
      out.write("\t\t\t  z-index:4;\r\n");
      out.write("\t\t\t  content:url(css/themes/default/images/splitline.png);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t/*切tabs圆角css,注意z-index先后*/\r\n");
      out.write("\t\t\t.tabs li.tabs-selected:after {/*切出右下角圆角*/\r\n");
      out.write("\t\t\t  position:absolute;\r\n");
      out.write("\t\t\t  bottom:0px;\r\n");
      out.write("\t\t\t  right:-3px;\r\n");
      out.write("\t\t\t  background-color: #E9E9E9;\r\n");
      out.write("\t\t\t  content:\"\";\r\n");
      out.write("\t\t\t  width:10px;\r\n");
      out.write("\t\t\t  height:47px;\r\n");
      out.write("\t\t\t  z-index:5;\r\n");
      out.write("\t\t\t  display: inline-block;\r\n");
      out.write("\t\t\t  border-radius: 0 0 0 7px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.tabs li.tabs-selected:before {/*切出左下角圆角*/\r\n");
      out.write("\t\t\t  position:absolute;\r\n");
      out.write("\t\t\t  bottom:0px;\r\n");
      out.write("\t\t\t  left:-3px;\r\n");
      out.write("\t\t\t  background-color: #E9E9E9;\r\n");
      out.write("\t\t\t  content:\"\";\r\n");
      out.write("\t\t\t  width:10px;\r\n");
      out.write("\t\t\t  height:47px;\r\n");
      out.write("\t\t\t  z-index:5;\r\n");
      out.write("\t\t\t  display: inline-block;\r\n");
      out.write("\t\t\t  border-radius: 0 0 7px 0;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t/*由于切出圆角后tabs形状将怪异，所以顶部要覆盖重新定义*/\r\n");
      out.write("\t\t\t.tabs li.tabs-selected a.tabs-inner:after {/*覆盖顶部*/\r\n");
      out.write("\t\t\t  position:absolute;\r\n");
      out.write("\t\t\t  top:0px;\r\n");
      out.write("\t\t\t  right:7px;\r\n");
      out.write("\t\t\t  background-color: #E9E9E9;\r\n");
      out.write("\t\t\t  content:\"\";\r\n");
      out.write("\t\t\t  width:106px;\r\n");
      out.write("\t\t\t  height:10px;\r\n");
      out.write("\t\t\t  z-index:6;\r\n");
      out.write("\t\t\t  display: inline-block;\r\n");
      out.write("\t\t\t  border-radius: 0px 0px 0 0;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t.tabs li.tabs-selected a.tabs-inner:before {/*重定义顶部形状*/\r\n");
      out.write("\t\t\t  position:absolute;\r\n");
      out.write("\t\t\t  top:0px;\r\n");
      out.write("\t\t\t  left:7px;\r\n");
      out.write("\t\t\t  background-color: #307EB7;\r\n");
      out.write("\t\t\t  content:\"\";\r\n");
      out.write("\t\t\t  width:106px;\r\n");
      out.write("\t\t\t  height:10px;\r\n");
      out.write("\t\t\t  z-index:7;\r\n");
      out.write("\t\t\t  display: inline-block;\r\n");
      out.write("\t\t\t  border-radius: 7px 7px 0 0;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t.panel {\r\n");
      out.write("\t\t\t  border-radius: 7px 7px 0 0;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</style>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body style=\"padding:0 25px 0 25px;\">\r\n");
      out.write("\t\t<div style=\"top:5px;left:40px;position:absolute;z-index:10\">\r\n");
      out.write("\t\t\t<img src='img/logo.png' style=\"width:110px;height:38px\"/>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"maintabs\" class=\"easyui-tabs\" style=\"top:0px;left:25px;position:absolute;height:650px\">\r\n");
      out.write("\t\t\t<div id='tab1' title=\"<span style='font-size:14px;font-weight:bold;font-family: Arial,sans-serif;'>Bkg# Range</span>\" height=\"100%\">\r\n");
      out.write("\t\t\t\t<div class=\"easyui-layout\" data-options=\"minWidth:890\"  style=\"height:100%\">\r\n");
      out.write("\t\t\t\t\t<div data-options=\"region:'north'\" style=\"height:47px;padding:0px;border:0px;margin:0px;overflow:hidden\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"width:100%;height:45px;background:#307EB7;color:#ffffff;line-height:45px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<span id=\"Maintaintitles\" style=\"font-size:14px;font-weight:bold;padding-left:15px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"tab1fram\" data-options=\"region:'center'\" style=\"border:0px;overflow:hidden\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id='tab2' title=\"<span style='font-size:14px;font-weight:bold;z-index:9;font-family: Arial,sans-serif;'>Traffic Log</span>\" style=\"overflow-y:visible;\">\r\n");
      out.write("\t\t\t\t<div class=\"easyui-layout\" data-options=\"minWidth:890,border:false\" style=\"height:100%;\">\r\n");
      out.write("\t\t\t\t\t<div data-options=\"region:'north'\" style=\"height:47px;padding:0px;border:0px;margin:0px;\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"signtitle\" style=\"width:100%;height:45px;background:#307EB7;color:#ffffff;line-height:45px;border-radius: 7px 7px 0 0;\">\r\n");
      out.write("\t\t\t\t\t\t\t<span id=\"Traffictitles\" style=\"font-size:14px;font-weight:bold;padding-left:15px\">\r\n");
      out.write("\t\t\t\t\t\t\t\tTraffic Log\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"tab2fram\" data-options=\"region:'center'\" style=\"border:0px;overflow:hidden\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id='tab3' title=\"<span style='font-size:14px;font-weight:bold;z-index:9;font-family: Arial,sans-serif;'>Convert Log</span>\" style=\"overflow-y:visible;\">\r\n");
      out.write("\t\t\t\t<div class=\"easyui-layout\" data-options=\"minWidth:890,border:false\" style=\"height:100%;\">\r\n");
      out.write("\t\t\t\t\t<div data-options=\"region:'north'\" style=\"height:47px;padding:0px;border:0px;margin:0px;\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"signtitle\" style=\"width:100%;height:45px;background:#307EB7;color:#ffffff;line-height:45px;border-radius: 7px 7px 0 0;\">\r\n");
      out.write("\t\t\t\t\t\t\t<span id=\"Converttitles\" style=\"font-size:14px;font-weight:bold;padding-left:15px\">\r\n");
      out.write("\t\t\t\t\t\t\t\tConvert Log\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"tab3fram\" data-options=\"region:'center'\" style=\"border:0px;overflow:hidden\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id='tab4' title=\"<span style='font-size:14px;font-weight:bold;z-index:9;font-family: Arial,sans-serif;'>Email</span>\" style=\"overflow-y:visible;\">\r\n");
      out.write("\t\t\t\t<div class=\"easyui-layout\" data-options=\"minWidth:890,border:false\" style=\"height:100%;\">\r\n");
      out.write("\t\t\t\t\t<div data-options=\"region:'north'\" style=\"height:47px;padding:0px;border:0px;margin:0px;\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"signtitle\" style=\"width:100%;height:45px;background:#307EB7;color:#ffffff;line-height:45px;border-radius: 7px 7px 0 0;\">\r\n");
      out.write("\t\t\t\t\t\t\t<span id=\"Converttitles\" style=\"font-size:14px;font-weight:bold;padding-left:15px\">\r\n");
      out.write("\t\t\t\t\t\t\t\tEmail\r\n");
      out.write("\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"tab4fram\" data-options=\"region:'center'\" style=\"border:0px;overflow:hidden\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar lastindex = 0;\r\n");
      out.write("\t\tvar isfirstload = true;\r\n");
      out.write("\t\tvar isexit = false;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t/* 初始化tab */\r\n");
      out.write("\t\t$('#maintabs').tabs({\r\n");
      out.write("\t\t\ttabWidth:120,\r\n");
      out.write("\t\t\ttabHeight:38,\r\n");
      out.write("\t\t\tfit:true,\r\n");
      out.write("\t\t\tonBeforeSelect:function(title,index){\r\n");
      out.write("\t\t\t\tif(isexit){\r\n");
      out.write("\t\t\t\t\t$.messager.confirm('Confirm', 'Are you sure to exit the editor?', function(r){\r\n");
      out.write("\t\t\t\t\t\tif (r){\r\n");
      out.write("\t\t\t\t\t\t\tisexit = false;\r\n");
      out.write("\t\t\t\t\t\t\t$('#maintabs').tabs('select',index);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tonSelect:function(title,index){\r\n");
      out.write("\t\t\t\tif(index==0){//范围配置页面\r\n");
      out.write("\t\t\t\t\t//$(\"#playlisttitles\").html(\"<bean:message key=\"content.SearchAll\"/>\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab2fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab3fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab4fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab1fram\").html(\"<iframe name='content' src='jsp/rangeInfo.jsp' style='border:none;margin:0px' height='100%' width='100%'></iframe>\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(index==1){//TRAFFIC日志页面\r\n");
      out.write("\t\t\t\t\t//$(\"#terminaltitles\").html(\"<bean:message key=\"terminal.SearchAllTerminal\"/>\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab1fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab3fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab4fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab2fram\").html(\"<iframe name='content' src='jsp/errorInfo.jsp?modules=TRAFFIC' style='border:none;margin:0px' height='100%' width='100%'></iframe>\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(index==2){//CONVERT日志页面\r\n");
      out.write("\t\t\t\t\t//$(\"#playlisttitles\").html(\"<bean:message key=\"content.SearchAll\"/>\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab1fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab2fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab4fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab3fram\").html(\"<iframe name='content' src='jsp/errorInfo.jsp?modules=CONVERT' style='border:none;margin:0px' height='100%' width='100%'></iframe>\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(index==3){//邮件页面\r\n");
      out.write("\t\t\t\t\t//$(\"#playlisttitles\").html(\"<bean:message key=\"content.SearchAll\"/>\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab1fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab2fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab3fram\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$(\"#tab4fram\").html(\"<iframe name='content' src='jsp/emailInfo.jsp' style='border:none;margin:0px' height='100%' width='100%'></iframe>\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(document).ready(function(){\r\n");
      out.write("\t\t\t//alert(document.body.clientWidth);\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t/* 修改蓝色标题方法 */\r\n");
      out.write("\t\tfunction updatetitle(id,title){\r\n");
      out.write("\t\t\t$(\"#\"+id).html(title);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction isexitfn(isexit){\r\n");
      out.write("\t\t\tthis.isexit = isexit;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
