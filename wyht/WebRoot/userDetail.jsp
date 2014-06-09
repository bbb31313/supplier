<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String CurrentID="";
boolean ISAdd=true;
Object obj=request.getParameter("uid");
if(obj!=null){
	CurrentID=(String)obj;
	ISAdd=false;
}

boolean IsView=false;
Object objview=request.getParameter("view");
if(objview!=null){
	String view=(String)objview;
	if(view.equalsIgnoreCase("1")){
		IsView=true;
	}
}

boolean IsEdit=!ISAdd && !IsView;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/css/common.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  
    <script src="<%=basePath %>lib/js/common.js" type="text/javascript"></script>    
    <script src="<%=basePath %>lib/js/LG.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery.form.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/validator.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/ligerui.expand.js" type="text/javascript"></script>
    <style type="text/css"> 
    .form-bar{height:30px; line-height:30px; background:#EAEAEA;border-top:1px solid #C6C6C6; overflow:hidden; margin-bottom:0px; position:fixed; bottom:0; left:0; width:100%; padding-top:5px; text-align:right;}
	/* ie6 */
	.form-bar{_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));}
	.form-bar-inner{ margin-right:20px;}
	.bar{ margin:0px; padding:0px; width:550px; height:40px;float:left;}
    </style> 
</head>
<body style="padding-bottom:31px;">
<jsp:include page="viewpage.jsp"></jsp:include>

    <form id="mainform" method="post"></form> 
	<div class="bar"></div>
    <script type="text/javascript"> 
        //当前ID
        var currentID = '<%=CurrentID %>';
        //是否新增状态
        var isAddNew = currentID == "" || currentID == "0";
        //是否查看状态
        var isView = <%=IsView %>;
        //是否编辑状态
        var isEdit = !isAddNew && !isView;

        //覆盖本页面grid的loading效果
        LG.overrideGridLoading(); 



        //创建表单结构
        var mainform = $("#mainform");  
        mainform.ligerForm({ 
         inputWidth: 370,
         fields : [
         			{name:"userID",type:"hidden"},
         			{display:"账号",name:"usersx",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},group:'用户信息',groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"真实姓名",name:"userName",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true}},
         			{display:"所属门店",name:"shopTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"shopname",options:{valueFieldID:"shop",url:'<%=basePath %>/json/json_shopBySeclect.action'}},
         			{display:"角色",name:"roleTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"rolename",options:{valueFieldID:"role",data: [{ text: '管理员', id: '1' },{ text: '操作员', id: '2' },{ text: '审核员', id: '3' } ]}}
         			],
		 			toJSON:JSON2.stringify
        });
        
        
		LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);

        if(isEdit){ 
            mainform.attr("action", "<%=basePath %>bus/bus_updateUser.action?uid=<%=CurrentID==""?1:CurrentID %>"); 
        }
        
        if (isAddNew) {
            mainform.attr("action", "<%=basePath %>/bus/bus_addUser.action");
        }
        else { 
            LG.loadForm(mainform, { url: '<%=basePath %>bus/bus_getUser.action',data:{uid:<%=CurrentID==""?1:CurrentID %>} },f_loaded);
        }  

        
        if(!isView) 
        {
            //验证
            jQuery.metadata.setType("attr", "validate"); 
            LG.validate(mainform);
        }   

		function f_loaded()
        {
            if(!isView) return; 
            //查看状态，控制不能编辑
            $("input,select,textarea",mainform).attr("readonly", "readonly");
        }
        
        function f_save()
        {
        	LG.submitForm(mainform, function (data) {
                var win = parent || window;
                if (data.IsError) {  
                    win.LG.showError('错误:' + data.Message);
                }
                else { 
                    win.LG.showSuccess('保存成功', function () { 
                        win.LG.closeAndReloadParent(null, "wyhtuser");
                        //win.LG.closeCurrentTab(null);
                    });
                }
            });
        }
        
        function f_cancel()
        {
            var win = parent || window;
            win.LG.closeCurrentTab(null);
        }

    </script>

</body>
</html>