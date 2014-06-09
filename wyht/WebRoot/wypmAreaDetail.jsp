<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String CurrentID="";
boolean ISAdd=true;
Object obj=request.getParameter("wpaid");
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
    
    <title>楼层面积信息</title>
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
         			{name:"wpaid",type:"hidden"},
         			{display:"所属门店",name:"shopTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"shopname",options:{valueFieldID:"shopid",url:'<%=basePath %>/json/json_shopBySeclect.action'},group:'楼层面积信息',groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"所属楼层",name:"floorTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"floorname",options:{valueFieldID:"floor",data: [{ text: '1', id: '1' },{ text: '2', id: '2' },{ text: '3', id: '3' },{ text: '4', id: '4' },{ text: '5', id: '5' },{ text: '6', id: '6' },{ text: '7', id: '7' },{ text: '8', id: '8' },{ text: '9', id: '9' },{ text: '10', id: '10' },{ text: '11', id: '11' },{ text: '12', id: '12' }]},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"可租未租区",name:"norentArea",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"不可租区",name:"notrentArea",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"楼层面积",name:"allArea",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"}
         			],
		 			toJSON:JSON2.stringify
        });
        
		LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);

 		if(isEdit){ 
            mainform.attr("action", "<%=basePath %>pm/pm_updateWyPMarea.action"); 
        }
        
       if (isAddNew) {
        	mainform.attr("action", "<%=basePath %>pm/pm_addWyPMarea.action");
        }
        else { 
            LG.loadForm(mainform, { url: '<%=basePath %>pm/pm_getWyPMarea.action',data:{wpaid:<%=CurrentID==""?117143:CurrentID %>} },f_loaded);
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
                    	win.LG.closeAndReloadParent(null, "setpmarea");
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