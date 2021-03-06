<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<style>
#sub, #back {
	width: 120px;
	background: #307EB7;
	color: #ffffff;
	border-radius: 10px;
	margin-right: 3px;
	margin-left: 3px;
}
.bold{
	font-weight:bold;
}
#edittable{
	border-left: 2px solid #666; 
	border-bottom: 2px solid #666;
}
#edittable th{
	font-weight:bold;
	background-color:#D3D3D3;
}
#edittable td,#edittable th{
   border-right:2px solid #666;
   border-top: 2px solid #666;
}
</style>
<script type="text/javascript" src="js/jquery.min.js?aa=bb"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/accordion-header.js"></script>
<title>Range Manage</title>
</head>
<body class="easyui-layout">
	<div region="center" style="padding: 10px;">
		<div id="acc" class="easyui-accordion"
			data-options="multiple:true,border:false" style="width: 99%">
			<div id="search"
				data-options="tools:'#seachbutton',collapsible:false"
				title="<span class='accstyle'><span></span>Search</span>">
				<table id="searchtable"  cellpadding="10">
					<tr>
						<td>Name:</td>
						<td><input class="inputtext" name="searchcode" id="searchcode" style="width:180px"/>
						</td>
						<td>Booking# Log:</td>
						<td>
							<select class="easyui-combobox"
								data-options="height:32,width:180"
								id="type" name="type">
									<option value="all">ALL</option>
									<option value="valid">Active</option>
							</select>
						</td>
					</tr>
				</table>
				<br />
			</div>
			<div id="list" data-options="collapsible:false,tools:'#menu'"
				title="<span class='accstyle'><span></span>List</span>">
						<table id="ManageList" name="ManageList">
						</table>
			</div>
		</div>
	</div>
	
	<div id="menu">
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm1',plain:false,menuAlign:'right'" style="padding:10px;width:105px;height:30px;line-height:30px;color:#307EB7;background:-webkit-linear-gradient(white,silver);filter:none;"><span class='actionstyle'>Query</span></a>
	</div>
	
	<div id="mm1" style="width:230px;">
	 	<!-- <div onclick='loadgrid(2,"","valid")'>Country</div>  -->
        <div onclick='loadgrid(3,"","valid")'>Location</div>
        <div onclick='loadgrid(4,"","valid")'>Booking Party</div>
    </div>
    
	<div id="seachbutton">
		<a href="#" class="easyui-linkbutton button" onclick="searchs()"
			style="width: 85px; height: 30px; line-height: 30px; color: #000000;">Search</a>
		<a href="#" class="easyui-linkbutton button" onclick="clearsearchs()"
			style="width: 125px; height: 30px; line-height: 30px; color: #000000;">Clear
			Search</a>
	</div>


</body>
<script>
	var curlevel="4";
	var local = "<%= request.getSession().getAttribute("local") %>";
	var userid = "<%= request.getSession().getAttribute("userid") %>";
	var groupid = "<%= request.getSession().getAttribute("groupid") %>";
	$(document).ready(function(){
		window.top.updatetitle("Maintaintitles","Bkg# Range");
		loadgrid(curlevel,"","valid");
		$("#type").combobox("setValue","valid");
		accordion_header();
	});
	
  	function searchs(){
  		var searchcode = $("#searchcode").val();
  		var searchtype = $("#type").combobox("getValue");
  		loadgrid(curlevel,searchcode,searchtype);
  	}
  	
  	function clearsearchs(){
  		$("#searchcode").val("");
  		$("#type").combobox("setValue","valid");
  		loadgrid(curlevel,"","valid");
  	}
  	
	function loadgrid(leval,code,type){
		curlevel=leval;
		$("#ManageList").treegrid({
			url:'<%=request.getContextPath()%>/ManagerServlet',
			queryParams : {
				"action" : "loadlist",
				"level":leval,
				"code":code,
				"type":type
			},
			width : '100%',
			nowrap : false,
			showFooter : true,
			striped : true, //隔行变色
			pagination : true,
			pageSize : 10,
			pageNumber : 1,
			idField : 'ID',
			rownumbers : false,
			fitColumns : true,
			scrollbarSize : 0,
			treeField:"CODE",
			columns : [ [ {
				field : 'CODE',
				title : 'Name',
				width : 190,formatter:function(val,row){
					if(row.DESC_CN!=null){
						return row.DESC_CN + "(" + val + ")";
					}
					return val;
				},styler:function(value,row,index){
					if(row.ISDELETE==1){
						return "color:#FF0000;font-size:10px";
					}
					if(row.BK_LEVEL!=curlevel){
						return "font-size:10px";
					}
				}
			}, {
				field : 'NO_WAVE1_MIN',
				title : 'WBP',
				width : 100,
				formatter:function(val,row){
					if(row.BK_LEVEL==curlevel){
						return val+"--"+row.NO_WAVE1_MAX;
					}
					return '&nbsp;&nbsp;&nbsp;&nbsp;'+val+"--"+row.NO_WAVE1_MAX;
				},styler:function(value,row,index){
					if(row.ISDELETE==1){
						return "color:#FF0000;font-size:10px";
					}
					if(row.BK_LEVEL!=curlevel){
						return "font-size:10px";
					}
				}
			}, {
				field : 'NO_WAVE1_LAST_USED_ID',
				title : 'WBP_LAST',
				width : 80,
				formatter:function(val,row){
					if(row.BK_LEVEL==curlevel){
						return val;
					}
					return '&nbsp;&nbsp;&nbsp;&nbsp;'+val;
				},styler:function(value,row,index){
					if(row.ISDELETE==1){
						return "color:#FF0000;font-size:10px";
					}
					if(row.BK_LEVEL!=curlevel){
						return "font-size:10px";
					}
				}
			},{
				field : 'WAVE1_MIN',
				title : 'CSSS',
				width : 100,
				formatter:function(val,row){
					if(row.BK_LEVEL==curlevel){
						return val+"--"+row.WAVE1_MAX;
					}
					return '&nbsp;&nbsp;&nbsp;&nbsp;'+val+"--"+row.WAVE1_MAX;
				},styler:function(value,row,index){
					if(row.ISDELETE==1){
						return "color:#FF0000;font-size:10px";
					}
					if(row.BK_LEVEL!=curlevel){
						return "font-size:10px";
					}
				}
			}, {
				field : 'WAVE1_LAST_USED_ID',
				title : 'CSSS_LAST',
				width : 80,
				formatter:function(val,row){
					if(row.BK_LEVEL==curlevel){
						return val;
					}
					return '&nbsp;&nbsp;&nbsp;&nbsp;'+val;
				},styler:function(value,row,index){
					if(row.ISDELETE==1){
						return "color:#FF0000;font-size:10px";
					}
					if(row.BK_LEVEL!=curlevel){
						return "font-size:10px";
					}
				}
			}, {
				field : 'OPTION',
				title : 'OPTION',
				width : 50,
				formatter:function(val,row){
					if(row.APL_OFFICE == local || row.CODE == local){
						if(row.BK_LEVEL==curlevel){
							return '<a href="#" class="easyui-linkbutton" data-options="size:large" onclick=defaultlist("'+row.ID+'","'+row.CODE+'","'+row.BK_LEVEL+'","'+row.WAVE1_MIN+'","'+row.WAVE1_MAX+'","'+row.NO_WAVE1_MIN+'","'+row.NO_WAVE1_MAX+'","'+row.DESC_CN+'","'+row.CODE+'")>Edit</a>';
						}
					}

					//return '';
				},styler:function(value,row,index){
					if(row.ISDELETE==1){
						return "color:#FF0000;font-size:10px";
					}
					if(row.BK_LEVEL!=curlevel){
						return "font-size:10px";
					}
				}
			} ] ],
			onLoadSuccess : function() {
				$("#acc").accordion("select", 1);
			}
		});
	}
	
	function defaultlist(id,code,level,WAVE1_MIN,WAVE1_MAX,NO_WAVE1_MIN,NO_WAVE1_MAX,DESC_CN,CODE){
		var name = "";
		if(DESC_CN != null && DESC_CN != 'undefined' ){
			name=DESC_CN;
		}else{
			name=CODE;
		}
		window.top.updatetitle("Maintaintitles",name +" | "+"WBP Range:&nbsp;&nbsp;&nbsp;&nbsp;"+NO_WAVE1_MIN+"--"+NO_WAVE1_MAX+"&nbsp;&nbsp;&nbsp;&nbsp;CSSS Range:&nbsp;&nbsp;&nbsp;&nbsp;"+WAVE1_MIN+"--"+WAVE1_MAX);
		location.href="RangeMaintainEdit.jsp?parentid="+id+"&code="+code+"&level="+level+"&wavemin="+WAVE1_MIN+"&wavemax="+WAVE1_MAX+"&nowavemin="+NO_WAVE1_MIN+"&nowavemax="+NO_WAVE1_MAX;
	}
</script>
</html>