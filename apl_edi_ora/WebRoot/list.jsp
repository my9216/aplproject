<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/accordion-header.js"></script>
<style>
#sub, #back {
	width: 120px;
	background: #307EB7;
	color: #ffffff;
	border-radius: 10px;
	margin-right: 3px;
	margin-left: 3px;
}
</style>
<title>MessageList</title>
</head>
<body class="easyui-layout">
	<div region="center" style="padding: 10px">
		<div id="acc" class="easyui-accordion"
			data-options="multiple:true,border:false" style="width: 99%">
			<div id="search"
				data-options="tools:'#seachbutton',collapsible:false"
				title="<span class='accstyle'><span></span>Search</span>">
				<table id="searchtable" style="width: 100%" cellpadding="10">
					<tr>
						<td>SERIALNUM:</td>
						<td><input class="inputtext" name="serialnum" id="serialnum" />
						</td>
						<td>BL_NO:</td>
						<td><input class="inputtext" name="slno" id="slno" /></td>
						<td>CTN_NO:</td>
						<td><input class="inputtext" name="ctnno" id="ctnno" /></td>
					</tr>
					<tr>
						<td>TYPE:</td> 
						<td><select class="easyui-combobox"
							data-options="height:32,width:$('#type').parent().width()"
							id="type" name="type">
								<option value="all">ALL</option>
								<option value="CTNREL">CTNREL</option>
								<option value="CANCELREL">CANCELREL</option>
						</select></td>
						<td>STATUS:</td>
						<td><select class="easyui-combobox"
							data-options="height:32,width:$('#status').parent().width()"
							id="status" name="status">
								<option value="all">ALL</option>
								<option value="ok">OK</option>
								<option value="other">OTHER</option>
						</select></td>
						<td>DATE:</td>
						<td><input class="easyui-datebox" name="startdate"
							id="startdate" style="height: 32px; width: 180px" /> --- <input
							class="easyui-datebox" name="stoptime" id="stopdate"
							style="height: 32px; width: 180px" /></td>
					</tr>
				</table>
				<br />
			</div>
			<div id="list" data-options="tools:'#action',collapsible:false"
				title="<span class='accstyle'><span></span>Search Results</span>">
				<table id="messageList" name="messageList">
				</table>
			</div>
		</div>
	</div>

	<div id="mMessage" name="mMessage" title="Import Message"
		style="background: #efefef;">
		<div class="easyui-layout" data-options="border:false"
			style="height: 140px">
			<form
				action="<%=request.getContextPath() %>/UploadHandleServlet"
				method="post" id="uploadMessageForm" name="uploadMessageForm" enctype="multipart/form-data">
				<div data-options="region:'center',border:false"
					style="padding-top: 20px; padding-left: 10px;">
					Upload <input id="importMessage" name="importMessage"
						class="easyui-filebox" style="width: 250px">
				</div>
				<div data-options="region:'south',border:false"
					style="text-align: center; padding: 12px 0px 0 0; height: 68px; border-top: solid 1px #E9E9E9">
					<a href="javascript:void(0)" id="sub" class="easyui-linkbutton"
						data-options="size:'large'" onclick="commit()"><span
						style="font-size: 18px">Commit</span></a> <a href="javascript:void(0)"
						id="back" class="easyui-linkbutton" data-options="size:'large'"
						onclick="returns()"><span style="font-size: 18px">Return</span></a>
				</div>
			</form>
		</div>
	</div>
	
	<div id="mProgress" name="mProgress" style="background: #efefef;text-align:center">
		<img src="img/loading.gif" />
	</div>
	
	<div id="mm1" style="width: 230px;">
		<div id="mm1-item" onclick="imports()">Import</div>
		<div id="mm1-item" onclick="cancel()">Cancel</div>
		<div id="mm1-item" onclick="Ctnrel()">Ctnrel</div>
	</div>
	<div id="selectedIdList"></div>


	<div id="action">
		<a href="#" class="easyui-menubutton"
			data-options="menu:'#mm1',plain:false,menuAlign:'right'"
			style="padding: 10px; width: 105px; height: 30px; line-height: 30px; color: #307EB7; background: -webkit-linear-gradient(white, silver);"><span
			class='actionstyle'>Action</span></a>
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
		$(document).ready(function(){
	  		loadGrid("","","","all","all","","");
	  		accordion_header();
	  	});
	  	
	  	$("#mMessage").dialog({
			width: 320,
			closed: true,
			modal: true,
			border: false,
			closable:false
		});

	  	$("#mProgress").dialog({
			width: 128,
			closed: true,
			modal: true,
			border: false,
			closable:false,
			title:null
		});
	  	
	  	$("#uploadMessageForm").form({
	  		url:"<%=request.getContextPath()%>/UploadHandleServlet",
	  	    onSubmit: function(){

	  	    },
	  	    success:function(data){
			  	if(data=="success"){
					$("#mProgress").dialog("close");
					$("#mMessage").dialog("close");
					clearsearchs();
				}else{
					$.messager.alert("Message","Upload Fail");
				}
	  	    }
	  	});
	  	
		function commit(){
			 $("#mProgress").dialog("open");
			 $("#uploadMessageForm").submit();
			 /*
			 if (window.applicationCache) {//判断是否支持html5
				uploadFile();
			 }else{
				
			 }
			 */
		}
		
		function Ctnrel(){
  			var listhtml= "aa=aa";
			var lists = $("#messageList").datagrid("getSelections");
			
			if(lists.length>0){
		  	 	for(var i=0;i<lists.length;i++){
					listhtml+= "&cancelIdList=" + lists[i].ID;
					listhtml+= "&serialnumIdList=" + lists[i].SERIALNUM;
					listhtml+= "&ctnIdList=" + lists[i].CTN;
					listhtml+= "&blIdList=" + lists[i].BL;
					listhtml+= "&vesselIdList=" + lists[i].VESSEL;
					listhtml+= "&voyageIdList=" + lists[i].VOYAGE;
				}
		  	 	$("#mProgress").dialog("open");
				$.ajax({
		           type: "POST",
		           url: "<%=request.getContextPath() %>/CtnrelServlet",
		           data: listhtml,
		           success: function(msg){
		        	    $("#mProgress").dialog("close");
		           		clearsearchs();
				   }
		        });
			}else{
				$.messager.alert("Message",'Please Check One Data');
				return;
			}
		}
		
		function returns(){
			$("#mMessage").dialog("close");
		}
		
	  	function searchs(){
	  		var slno = $("#slno").val();
	  		var ctnno = $("#ctnno").val();
	  		var serialnum=$("#serialnum").val();
	  		var type = $("#type").combobox("getValue");
	  		var status = $("#status").combobox("getValue");
	  		var startdate=$("#startdate").datebox("getValue");
	  		var stopdate=$("#stopdate").datebox("getValue");
	  		loadGrid(slno,ctnno,serialnum,type,status,startdate,stopdate);
	  	}
	  	
	  	function clearsearchs(){
	  		$("#slno").val("");
	  		$("#ctnno").val("");
	  		$("#serialnum").val("");
	  		$("#type").combobox("setValue","all");
	  		$("#status").combobox("setValue","all");
	  		$("#startdate").datebox("setValue","");
	  		$("#stopdate").datebox("setValue","");
	  		loadGrid("","","","all","all","","");
	  	}
	  	
	  	function imports(){
	  		$("#mMessage").dialog("open");
	  	}
	  	
	  	function cancel(){
  			var listhtml= "aa=aa";
			var lists = $("#messageList").datagrid("getSelections");
			if(lists.length>0){
		  	 	for(var i=0;i<lists.length;i++){
					listhtml+= "&cancelIdList=" + lists[i].ID;
					listhtml+= "&serialnumIdList=" + lists[i].SERIALNUM;
				}
		  	 	$("#mProgress").dialog("open");
				  $.ajax({
			           type: "POST",
			           url: "<%=request.getContextPath() %>/CancelServlet",
			           data: listhtml,
			           success: function(msg){
			        	   $("#mProgress").dialog("close");
			           		clearsearchs();
					   }
		          });
			}else{
				$.messager.alert("Message",'Please Check One Data');
				return;
			}
	  	}
	  	
		function loadGrid(slno,ctnno,serialnum,type,status,startdate,stopdate){
			$("#messageList").datagrid({
				url: '<%= request.getContextPath()%>/MessageServlet',
			queryParams : {
				"slno" : slno,
				"ctnno" : ctnno,
				"serialnum" : serialnum,
				"type" : type,
				"status" : status,
				"startdate" : startdate,
				"stopdate" : stopdate
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
			columns : [ [ {
				field : 'ID',
				checkbox : true
			}, {
				field : 'SERIALNUM',
				title : 'SERIALNUM',
				width : 130,
				sortable:true
			}, {
				field : 'BL',
				title : 'BL_NO',
				width : 60,
				sortable:true
			}, {
				field : 'CTN',
				title : 'CTN_NO',
				width : 60,
				sortable:true
			}, {
				field : 'TYPE',
				title : 'TYPE',
				width : 40
			}, {
				field : 'STATUS',
				title : 'STATUS',
				width : 40
			}, {
				field : 'DATETIME',
				title : 'DATETIME',
				width : 80,
				align : 'right'
			} ] ],
			onClickRow : function(rowIndex, rowData) {
			},
			onLoadSuccess : function() {
				$("#acc").accordion("select", 1);
			}
		});
	}
</script>
</html>