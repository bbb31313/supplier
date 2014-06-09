<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String CurrentID="";
boolean ISAdd=true;
Object obj=request.getParameter("htid");
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
    
    <title>银行卡信息</title>
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
         			{display:"银行卡号",name:"card",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},group:'银行卡信息',groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"所属银行",name:"bankTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"bankname",options:{valueFieldID:"bank",data: [{ text: '农业银行', id: '1' },{ text: '交通银行', id: '2' },{ text: '招商银行', id: '4' },{ text: '建设银行', id: '3' }]},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"金额",name:"zmoney",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true,number:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"日期",name:"cdate",newline:true,labelWidth:100,width:220,space:30,type:"date",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"}
         			],
		 			toJSON:JSON2.stringify
        });
        
		LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);

        if(isEdit){ 
            mainform.attr("action", "<%=basePath %>bus/bus_auditWyht.action"); 
        }
        
        if (isAddNew) {
            mainform.attr("action", "<%=basePath %>/bus/bus_addCard.action");
            
        }
        else { 
            LG.loadForm(mainform, { url: '<%=basePath %>bus/bus_getWyht.action',data:{htid:<%=CurrentID==""?117143:CurrentID %>} },f_loaded);
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
                    jQuery.ligerDialog.error('错误:' + data.Message);
                }
                else { 
                    win.LG.showSuccess(data.Message, function () { 
                                    $("#card").val('');
           							$("#zmoney").val('');
           							$("#cdate").val('');
                    });
                }
            });
        }
        
        function f_cancel()
        {
            //var win = parent || window;
            //win.LG.closeCurrentTab(null);
            $("#card").val('');
            $("#zmoney").val('');
            $("#cdate").val('');
        }

    </script>

</body>
</html>