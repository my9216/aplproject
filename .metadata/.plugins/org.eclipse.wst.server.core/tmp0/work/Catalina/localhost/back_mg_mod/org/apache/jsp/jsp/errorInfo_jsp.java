package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class errorInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"../css/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/themes/icon.css\">\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/jquery.min.js?aa=bb\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/accordion-header.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("#sub, #back {\r\n");
      out.write("\twidth: 120px;\r\n");
      out.write("\tbackground: #307EB7;\r\n");
      out.write("\tcolor: #ffffff;\r\n");
      out.write("\tborder-radius: 10px;\r\n");
      out.write("\tmargin-right: 3px;\r\n");
      out.write("\tmargin-left: 3px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<title>ShowErrorinfo</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("\t<div region=\"center\" style=\"padding: 10px\">\r\n");
      out.write("\t\t<div id=\"acc\" class=\"easyui-accordion\"\r\n");
      out.write("\t\t\tdata-options=\"multiple:true,border:false\" style=\"width: 99%\">\r\n");
      out.write("\t\t\t<div id=\"search\"\r\n");
      out.write("\t\t\t\tdata-options=\"tools:'#seachbutton',collapsible:false\"\r\n");
      out.write("\t\t\t\ttitle=\"<span class='accstyle'><span></span>Search</span>\">\r\n");
      out.write("\t\t\t\t<table id=\"searchtable\" style=\"width: 100%\" cellpadding=\"10\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>FILENAME:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input class=\"inputtext\" name=\"filename\" id=\"filename\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td>DATE:</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input class=\"easyui-datebox\" name=\"startdate\"\r\n");
      out.write("\t\t\t\t\t\t\tid=\"startdate\" style=\"height: 32px; width: 180px\" /> --- <input\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"easyui-datebox\" name=\"stoptime\" id=\"stopdate\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"height: 32px; width: 180px\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<br />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"list\" data-options=\"collapsible:false\"\r\n");
      out.write("\t\t\t\ttitle=\"<span class='accstyle'><span></span>Search Results</span>\">\r\n");
      out.write("\t\t\t\t<p><span id='Statistics' style=\"color:red;font-size:15px\"></span></p>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<table id=\"messageList\" name=\"messageList\">\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"seachbutton\">\r\n");
      out.write("\t\t<a href=\"#\" class=\"easyui-linkbutton button\" onclick=\"searchs()\"\r\n");
      out.write("\t\t\tstyle=\"width: 85px; height: 30px; line-height: 30px; color: #000000;\">Search</a>\r\n");
      out.write("\t\t<a href=\"#\" class=\"easyui-linkbutton button\" onclick=\"clearsearchs()\"\r\n");
      out.write("\t\t\tstyle=\"width: 125px; height: 30px; line-height: 30px; color: #000000;\">Clear\r\n");
      out.write("\t\t\tSearch</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("\t\tvar modules = \"");
      out.print(request.getParameter("modules"));
      out.write("\";//记录显示log的类型traffic或Convert\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(document).ready(function(){\r\n");
      out.write("\t  \t\taccordion_header();\r\n");
      out.write("\t  \t\tvar date = formatDate(new Date());//默认的时间区间为今天\r\n");
      out.write("\t  \t\t$(\"#startdate\").datebox('setValue' , date);\r\n");
      out.write("\t  \t\t$(\"#stopdate\").datebox('setValue' , date);\r\n");
      out.write("\t  \t\tloadGrid(\"\",date,date);\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t});\r\n");
      out.write("\t  \t\r\n");
      out.write("\t\t/* 筛选操作 */\r\n");
      out.write("\t  \tfunction searchs(){\r\n");
      out.write("\t  \t\tvar filename = $(\"#filename\").val();\r\n");
      out.write("\t  \t\tvar startdate=$(\"#startdate\").datebox(\"getValue\");\r\n");
      out.write("\t  \t\tvar stopdate=$(\"#stopdate\").datebox(\"getValue\");\r\n");
      out.write("\t  \t\tloadGrid(filename,startdate,stopdate);\r\n");
      out.write("\t  \t}\r\n");
      out.write("\t  \t\r\n");
      out.write("\t\t/* 清空筛选操作 */\r\n");
      out.write("\t  \tfunction clearsearchs(){\r\n");
      out.write("\t  \t\t$(\"#filename\").val(\"\");\r\n");
      out.write("\t  \t\t$(\"#startdate\").datebox(\"setValue\",\"\");\r\n");
      out.write("\t  \t\t$(\"#stopdate\").datebox(\"setValue\",\"\");\r\n");
      out.write("\t  \t\tloadGrid(\"\",\"\",\"\");\r\n");
      out.write("\t  \t}\r\n");
      out.write("\t  \t\r\n");
      out.write("\t\t/* 加载日志数据 */\r\n");
      out.write("\t\tfunction loadGrid(filename,startdate,stopdate){\r\n");
      out.write("\t\t\t$(\"#messageList\").datagrid({\r\n");
      out.write("\t\t\t\turl: '");
      out.print(request.getContextPath() );
      out.write("/Error.do',\r\n");
      out.write("\t\t\t\tmethod:'GET',\r\n");
      out.write("\t\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\t\t\"filename\" : filename,\r\n");
      out.write("\t\t\t\t\t\"modules\":modules,\r\n");
      out.write("\t\t\t\t\t\"startdate\" : startdate,\r\n");
      out.write("\t\t\t\t\t\"stopdate\" : stopdate\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\twidth : '100%',\r\n");
      out.write("\t\t\t\tnowrap : false,\r\n");
      out.write("\t\t\t\tshowFooter : true,\r\n");
      out.write("\t\t\t\tstriped : true, //隔行变色\r\n");
      out.write("\t\t\t\tpagination : true,\r\n");
      out.write("\t\t\t\tpageSize : 10,\r\n");
      out.write("\t\t\t\tpageNumber : 1,\r\n");
      out.write("\t\t\t\tidField : 'ID',\r\n");
      out.write("\t\t\t\trownumbers : false,\r\n");
      out.write("\t\t\t\tfitColumns : true,\r\n");
      out.write("\t\t\t\tscrollbarSize : 0,\r\n");
      out.write("\t\t\t\tcolumns : [ [ {\r\n");
      out.write("\t\t\t\t\tfield : 'ERROR_FILE_NAME',\r\n");
      out.write("\t\t\t\t\ttitle : '&nbsp;&nbsp;ERROR_FILE_NAME',\r\n");
      out.write("\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\tformatter: function(val,row){\r\n");
      out.write("\t\t\t\t\t\treturn \"&nbsp;&nbsp;\" + val;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\tfield : 'ERROR_DESC',\r\n");
      out.write("\t\t\t\t\ttitle : 'ERROR_DESC',\r\n");
      out.write("\t\t\t\t\twidth : 160\r\n");
      out.write("\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\tfield : 'CREATE_DATE',\r\n");
      out.write("\t\t\t\t\ttitle : 'CREATEDATE',\r\n");
      out.write("\t\t\t\t\twidth : 40\r\n");
      out.write("\t\t\t\t} ] ],\r\n");
      out.write("\t\t\t\tonClickRow : function(rowIndex, rowData) {\r\n");
      out.write("\t\t\t\t\t$(\"#messageList\").datagrid(\"unselectRow\",rowIndex);\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tonLoadSuccess : function() {\r\n");
      out.write("\t\t\t\t\t/* 加载完毕后统计数据 */\r\n");
      out.write("\t\t\t\t\tvar Statistics = $(this).datagrid(\"getData\").Statistics;\r\n");
      out.write("\t\t\t\t\tif(modules==\"CONVERT\"){\r\n");
      out.write("\t\t\t\t\t\tif(Statistics.FILEQTY != undefined && Statistics.FILEQTY != null){\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#Statistics\").html(\"FileQty&nbsp;:&nbsp;&nbsp;\"+Statistics.FILEQTY+\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SuccQty&nbsp;:&nbsp;&nbsp;\"+(Statistics.FILEQTY-Statistics.ERRQTY)+\"&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;ErrQty&nbsp;:&nbsp;&nbsp;\"+Statistics.ERRQTY);\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#Statistics\").html(\"FileQty&nbsp;:&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SuccQty&nbsp;:&nbsp;&nbsp;0&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;ErrQty&nbsp;:&nbsp;&nbsp;0\");\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tif(Statistics.FILEQTY != undefined && Statistics.FILEQTY != null){\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#Statistics\").html(\"FileQty&nbsp;:&nbsp;&nbsp;\"+Statistics.FILEQTY+\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;WbpQty&nbsp;:&nbsp;&nbsp;\"+Statistics.WBPQTY+\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q2cQty&nbsp;:&nbsp;&nbsp;\"+Statistics.Q2CQTY+\"&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;ErrQty&nbsp;:&nbsp;&nbsp;\"+Statistics.ERRQTY);\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#Statistics\").html(\"FileQty&nbsp;:&nbsp;&nbsp;0&nbsp&nbsp;&nbsp;;&nbsp;&nbsp;&nbsp;WbpQty&nbsp;:&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q2cQty&nbsp;:&nbsp;&nbsp;0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ErrQty&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;0\");\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(\"#acc\").accordion(\"select\", 1);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/* 格式化日期 */\r\n");
      out.write("\tfunction formatDate(date){\r\n");
      out.write("\t\tvar day = date.getDate() > 9 ? date.getDate() : \"0\" + date.getDate();\r\n");
      out.write("\t\tvar month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : \"0\" + (date.getMonth() + 1);\r\n");
      out.write("\t\treturn date.getFullYear() + '-' + month + '-' + day;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
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
