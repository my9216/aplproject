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
	border-left: 1px solid #E9E9E9;
	border-bottom: 1px solid #E9E9E9;
}
#edittable th{
	font-weight:bold;
	background: #F6F6F6;
}
#edittable td,#edittable th{
   border-right:1px solid #E9E9E9;
   border-top: 1px solid #E9E9E9;
}
#acc2 .panel-tool {
  position: absolute;
  top: 50%;
  margin-top: -15px;
  height: 32px;
  overflow: hidden;
}
#acc2 .panel-icon {
  left: 5px;
  width: 16px;
}
#acc2 .panel-tool {
  right: 3px;
  width: auto;
}
#acc2 .panel-tool a {
  height: 30px;
}

</style>
<script type="text/javascript" src="js/jquery.min.js?aa=bb"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/accordion-header.js"></script>
<title>RangeMaintain</title>
</head>
<body class="easyui-layout">
	<div region="center" data-options="border:false" style="padding: 10px;">
			<div id="acc" class="easyui-accordion" data-options="multiple:true,border:false" style="width:98%;">
				<div id="edit" data-options="tools:'#edittool',collapsible:false" title="<span class='accstyle'><span></span>Edit</span>">
					<form id="form1" name="form1">
						<span id='range' style="color:#FF0000"></span>
						<table  id="edittable" name="edittable" width="100%" border="0" cellspacing="0" cellpadding="5" style="text-align:center" >
							<thead>　
								<tr>
									<th rowspan="2" style="width:80px">Name</th>
									<th colspan="3">WBP</th>
									<th colspan="3">CSSS</th>
									<th rowspan="2" style="width:20px"></th>
								</tr>
								<tr>
									<th>MIN</th>
									<th>MAX</th>
									<th>LASTID</th>
									<th>MIN</th>
									<th>MAX</th>
									<th>LASTID</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
						<input type="hidden" name="action" id="action" />
						<input type="hidden" name="parentid" id="parentid" value="<%=request.getParameter("parentid")%>"/>
					</form>
				</div>
				<div id="list2" data-options="collapsible:false,tools:'#showall'" title="<span class='accstyle'><span></span>List</span>"  style="padding:0px;">
					<div class="easyui-layout" style="height:300px">
						<div data-options="region:'center'" style="padding:0px;">
							<table id="defaultlist" name="defaultlist">
							</table>
						</div>
					</div>
				</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
					style="text-align: right; padding: 12px 0px 0 0; height: 68px;">
		<a href="javascript:void(0)" id="sub" class="easyui-linkbutton"
						data-options="size:'large'" onclick="sub()"><span
						style="font-size: 18px">Commit</span></a> <a href="javascript:void(0)" onclick="back()"
						id="back" class="easyui-linkbutton" data-options="size:'large'"><span style="font-size: 18px">Return</span></a>
	</div>

	<div id="edittool">
		<table>
			<tr id="editlocation" style="display:none">
				<td class="bold">
					Please Select a Location:&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="location" name="location" style="height: 25px; width: 180px;"></select>
					<button onclick="addtr('location')">+</button></td>
			</tr>
			<tr id="editbp" style="display:none">
				<td class="bold" align=right>
					Please Select a Booking Party:&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="bp" name="bp" style="height: 25px; width: 180px"></select>
					<button onclick="addtr('bp')">+</button>
				</td>
			</tr>
			<tr id="editorign" style="display:none">
				<td class="bold" align=right>
					Please Select a orign:&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="orign" name="orign" style="height: 25px; width: 180px"></select>
					<button onclick="addtr('orign')">+</button></td>
			</tr>
		</table>
	</div>
	
	<div id="showall">
		<table>
			<tr>
				<td class="bold">
					<input type="checkbox" id="check" name="check" onclick="changecheck()"/>Show Locked
				</td>
			</tr>
		</table>
	</div>
</body>
<script>
	var parentid="<%=request.getParameter("parentid")%>";
	var level="<%=request.getParameter("level")%>";
	var code="<%=request.getParameter("code")%>";
	var wavemax="<%=request.getParameter("wavemax")%>";
	var wavemin="<%=request.getParameter("wavemin")%>";
	var nowavemax="<%=request.getParameter("nowavemax")%>";
	var nowavemin="<%=request.getParameter("nowavemin")%>";
	
	$(document).ready(function(){
		accordion_header();
		if(level==2){
	  		$("#editorign").css("display","none");
	  		$("#editbp").css("display","none");
			$("#editlocation").css("display","");
			$("#action").val("editlocation");
		}
		if(level==3){
			$('#bp').combobox({
				url:'<%=request.getContextPath()%>/ManagerServlet?action=getbpcode&locationcode='+code,
				valueField:'comid',
				textField:'text',
				editable:true,
				onLoadSuccess:function(){
					$('#bp').combobox("setValue","");
				},
				onSelect:function(data){
					//loadGrid(data.ID);
				},
				filter: function(q, row){
					var opts = $(this).combobox('options');
					return row[opts.textField].indexOf(q.toUpperCase()) == 0;
				}
			});
	  		$("#editorign").css("display","none");
	  		$("#editbp").css("display","");
			$("#editlocation").css("display","none");
			$("#action").val("editbp");
		}
		if(level==4){
	  		$("#editorign").css("display","");
	  		$("#editbp").css("display","none");
			$("#editlocation").css("display","none");
			$("#action").val("editorign");
		}
		defaultlist(parentid,level,"valid");
	});

	$("#form1").form({
		url: "<%=request.getContextPath()%>/ManagerServlet",
	    onSubmit: function(){
	    	 return $(this).form('enableValidation').form('validate');
	    },
	    success:function(data){
	    	$("#defaultlist").datagrid("reload");
	    	$("#edittable tbody").html("");
	    	window.top.isexitfn(false);
	    }
	});
	
	
	$('#orign').combobox({
		url:'<%=request.getContextPath()%>/ManagerServlet?action=getorign',
		valueField:'comid',
		textField:'text',
		editable:true,
		onLoadSuccess:function(){
		},
		onSelect:function(data){
			//loadGrid(data.ID);
		},
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q.toUpperCase()) == 0;
		}
	});
  	
	function changecheck(){
		if($("#check").is(":checked")){
			defaultlist(parentid,level,"all");
		}else{
			defaultlist(parentid,level,"valid");
		}
	}
	
	function defaultlist(id,level,type){
		$("#parentid").val(id);
		$("#defaultlist").datagrid({
			url:'<%=request.getContextPath()%>/ManagerServlet',
			queryParams : {
				"action" : "loaddefaultlist",
				"parentid" : parentid,
				"type":type
			},
			toolbar:[{
				 text:'Edit',
		         iconCls:'icon-edit',
		         handler:function(){
		        	 dotoolbar('edit');
		        	 //alert('Edit');
		         }
			},{
	            text:'Remove',
	            iconCls:'icon-remove',
	            handler:function(){
	            	dotoolbar('remove');
	            	//alert('cut');
	            }
	        }],
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
			singleSelect: false,
			fit:true,
			scrollbarSize : 0,
			rowStyler: function(index,row){
				if(row.ISDELETE==1){
                    return 'color:#FF0000;';
                }
            },
			columns : [ [ {
				field : 'ID',
				title : 'ID',
				width : 100,
				checkbox:true
			},{
				field : 'CODE',
				title : 'NAME',
				width : 100,formatter:function(val,row){
					if(row.DESC_CN!=null){
						return row.DESC_CN
					}
					return val;
				}
			},{
				field : 'NO_WAVE1_MIN',
				title : 'WBP',
				width : 100,
				formatter:function(val,row){
					return val+"--"+row.NO_WAVE1_MAX;
				}
			}, {
				field : 'NO_WAVE1_LAST_USED_ID',
				title : 'WBP_LAST',
				width : 100
			}, {
				field : 'WAVE1_MIN',
				title : 'CSSS',
				width : 100,
				formatter:function(val,row){
					return val+"--"+row.WAVE1_MAX;
				}
			}, {
				field : 'WAVE1_LAST_USED_ID',
				title : 'CSSS_LAST',
				width : 100
			},{
				field : 'ISDELETE',
				title : 'STATUS',
				width : 100,
				formatter:function(val,row){
					if(val==1){
						return "Locked";
					}
					return "Active";
				}
			} ] ],
			onLoadSuccess : function() {
				$("#mDefaultdiv").dialog("open");
				$("#acc").accordion("select",1);
			},
			onSelect: function(index,row){
				
			},
			onClickRow : function(rowIndex, rowData) {
				$("#defaultlist").datagrid("unselectRow",rowIndex);
			}
		});
	}
	
	function dotoolbar(type){
		var lists = $("#defaultlist").datagrid("getSelections");
		if(lists == 0){
			$.messager.alert("Message",'Please Check One Data');
			return;
		}
		if(type=="edit"){
			for(var j=0;j<lists.length;j++){
				if(lists[j].ISDELETE==1){
					continue;
				}
				var code = lists[j].CODE;
				if($('#'+code+'editid').length>0){
					continue;
				}
			    addnumberbox(code);//添加一行编辑
			    loadnumberbox(code,lists[j]);//填充数值
				i++;
			}
			$("#defaultlist").datagrid("uncheckAll");
		}else{
			$.messager.confirm('Confirm', 'Are you sure Remove?', function(r){
				if (r){
					var listhtml= "action=remove";
					for(var j=0;j<lists.length;j++){
						listhtml+= "&listid=" + lists[i].ID;
						var code = lists[j].CODE;
						$('#'+code+'editid').parent().parent().remove();
						$.ajax({
					           type: "POST",
					           url: "<%=request.getContextPath() %>/ManagerServlet",
					           data: listhtml,
					           success: function(msg){
					        	   $("#defaultlist").datagrid("reload");
							   }
						});
						if($("#edittable tbody").html()==""){
							$("#edittable tbody").html("");
							window.top.isexitfn(false);
						}
					}
				}
			});
		}
	}
	
	function addnumberbox(code){
		$("#edittable").append("<tr class='tr"+i+"'><td><input type='hidden' id='"+code+"editid' name='"+code+"editid'/><span id='"+code+"show' name='"+code+"show'></span><input type='hidden' name='codes' value='"+code+"'  style='width:60px' readonly /></td><td><input id='"+code+"nowavemin' name='"+code+"nowavemin' code='"+code+"' class='easyui-numberbox numbox' data-options='min:"+nowavemin+",max:"+nowavemax+",precision:0,required:true'></td><td><input id='"+code+"nowavemax' name='"+code+"nowavemax' class='easyui-numberbox numbox' code='"+code+"' data-options='min:"+nowavemin+",max:"+nowavemax+",precision:0,required:true'/></td><td><input id='"+code+"nowavelast' name='"+code+"nowavelast' class='easyui-numberbox numbox' code='"+code+"' data-options='min:"+nowavemin+",max:"+nowavemax+",precision:0,required:true'/></td><td><input id='"+code+"wavemin' name='"+code+"wavemin' code='"+code+"' class='easyui-numberbox numbox' data-options='min:"+wavemin+",max:"+wavemax+",precision:0,required:true'></td><td><input id='"+code+"wavemax' name='"+code+"wavemax' code='"+code+"' class='easyui-numberbox numbox' data-options='min:"+wavemin+",max:"+wavemax+",precision:0,required:true'></td><td><input id='"+code+"wavelast' name='"+code+"wavelast' code='"+code+"' class='easyui-numberbox numbox' data-options='min:"+wavemin+",max:"+wavemax+",precision:0,required:true'></td><td><button class='linkbuttons' onclick=removetr('tr"+i+"')>-</button></td></tr>");
		$(".numbox").numberbox({
			width:'130px',
			height:'28px',
			onChange:function(newValue,oldValue){
				//check(newValue);
			}
		});
		$("#"+code+"wavemin").numberbox({
			onChange:function(newValue,oldValue){
				if($("#"+code+"wavelast").numberbox("getValue")==""){
					$("#"+code+"wavelast").numberbox("setValue",newValue)
				}
			}
		});
		$("#"+code+"nowavemin").numberbox({
			onChange:function(newValue,oldValue){
				if($("#"+code+"nowavelast").numberbox("getValue")==""){
					$("#"+code+"nowavelast").numberbox("setValue",newValue)
				}
			}
		});
		$(".easyui-numberbox").numberbox("disableValidation");
	}
	
	function loadnumberbox(code,data){
			var show = data.DESC_CN==null?data.CODE:data.DESC_CN;
			$("#"+code+"show").html(show);
			$("#"+code+"wavemax").numberbox("setValue",data.WAVE1_MAX);
			$("#"+code+"wavemin").numberbox("setValue",data.WAVE1_MIN);
			$("#"+code+"nowavemin").numberbox("setValue",data.NO_WAVE1_MIN);
			$("#"+code+"nowavemax").numberbox("setValue",data.NO_WAVE1_MAX);
			$("#"+code+"wavelast").numberbox("setValue",data.WAVE1_LAST_USED_ID);
			$("#"+code+"nowavelast").numberbox("setValue",data.NO_WAVE1_LAST_USED_ID);
			$("#"+code+"editid").val(data.ID);
	}
	
	var i=0;
	function addtr(type){
		var code = "";
		if(type=="location"){
			var code = $('#location').combobox("getValue");
			if(code=="" || code == " " || !code){
				$.messager.alert("Error","Plese Select a Data");
				return;
			}
			$('#location').combobox("setValue","");
		}
		if(type=="bp"){
			code = $('#bp').combobox("getValue");
			if(code=="" || code == " " || !code){
				$.messager.alert("Error","Plese Select a Data");
				return;
			}
			
		}
		if(type=="orign"){
			code = $('#orign').combobox("getValue");
			if(code=="" || code==" " || !code){
				$.messager.alert("Error","Plese Select a data in Orign");
				return;
			}
			//$('#orign').combobox("setValue","");
		}
		if($('#'+code+'editid').length>0){
			$.messager.alert("Error","The Data already edit");
			return;
		}
		addnumberbox(code);
		loaddata(code,type);
		window.top.isexitfn(true);
		i++;
	}
	
	function iseditdefault(){
		var data = $("#defaultlist").datagrid("getData").rows;
		if(data.length==1 && data[0].CODE=="default"){
			if($('#defaulteditid').length==0){
				return false;
			}
		}
		return true;
	}
	
	function loaddata(code,type){
		if(type=="location"){
			$.ajax({            
					type: "post",//请求方式            
					url: "<%=request.getContextPath()%>/ManagerServlet?action=loadlocation&code="+code,
					dataType:'json',
					async:false,
					success: function(data) {
				  		for(var i=0;i<data.rows.length;i++){
				  			if(data.rows[i].CODE!="default"){
				  				loadnumberbox(code,data.rows[i]);
				  			}
				  		}
					}
			});
		}
		if(type=="bp"){
			$.ajax({            
				type: "post",//请求方式            
				url: "<%=request.getContextPath()%>/ManagerServlet?action=loadbp&code="+code,
				dataType:'json',
				async:false,
				success: function(data) {
					if(data.rows.length==0){
						var show = $('#bp').combobox("getText");
						$("#"+code+"show").html(show);
					}
			  		for(var i=0;i<data.rows.length;i++){
			  			if(data.rows[i].CODE!="default"){
			  				loadnumberbox(code,data.rows[i]);
			  			}
			  		}
			  		$('#bp').combobox("setValue","");
				}
			});
		}
		if(type=="orign"){
			$.ajax({            
					type: "post",//请求方式            
					url: "<%=request.getContextPath()%>/ManagerServlet?action=loadeditorign&code="+code+"&parentid="+parentid,
					dataType:'json',
					async:false,
					success: function(data) {
						if(data.rows.length==0){
							var show = $('#orign').combobox("getText");
							$("#"+code+"show").html(show);
						}
				  		for(var i=0;i<data.rows.length;i++){
				  			loadnumberbox(code,data.rows[i]);
				  		}
				  		$('#orign').combobox("setValue","");
					}
			});
		}
	}
	
	function sub(){
		if($("#edittable tbody").html()!=""){
			if(iseditdefault()){
				$.messager.confirm('Confirm', 'Are you sure Submit?', function(r){
					if (r){
						$("#form1").submit();
					}
				});
			}else{
				$.messager.alert("Error","first adding new record must change default value,please click default row and edit it!");
			}
		}else{
			$.messager.alert("Error","No Edited data");
		}
	}
	
	function removetr(id){
		$("."+id).remove();
		if($("#edittable tbody").html()==""){
			$("#edittable tbody").html("");
			window.top.isexitfn(false);
		}
	}
	
	function back(){
		if($("#edittable tbody").html()==""){
			location.href="RangeMaintain.jsp";
		}else{
			$.messager.confirm('Confirm', 'Are you sure to exit the editor?', function(r){
				if (r){
					location.href="RangeMaintain.jsp";
				}
			});
		}
	}
</script>
</html>