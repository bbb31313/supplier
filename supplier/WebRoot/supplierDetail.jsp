<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String CurrentID="";
boolean ISAdd=true;
Object obj=request.getParameter("sid");
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
    
    <title>供应商信息</title>
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
         			{name:"htid",type:"hidden"},
         			{display:"甲方名称",name:"jianame",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},group:'合同信息',groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"甲方地址",name:"jiaaddress",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方名称",name:"yiname",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方地址",name:"yiaddress",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方电话",name:"yiphone",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方传真",name:"yifax",newline:false,labelWidth:100,width:220,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方联系人",name:"yilxname",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方身份证",name:"yisfz",newline:false,labelWidth:100,width:220,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方商铺号",name:"yishop",newline:true,labelWidth:100,width:220,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"建筑面积(平方米)",name:"jzmj",newline:true,labelWidth:140,width:260,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"合同有效开始时间",name:"startdate",newline:true,labelWidth:150,width:220,space:30,type:"date",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
					{display:"合同有效结束时间",name:"enddate",newline:false,labelWidth:150,width:220,space:30,type:"date",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"物业管理费计算方式",name:"wycosttypeTo",newline:true,labelWidth:150,width:220,space:30,type:"select",validate:{required:true},comboboxName:"wycosttypename",options:{valueFieldID:"wycosttype",data: [{ text: '按经营面积计算', id: '1' },{ text: '按建筑面积计算', id: '2' }]},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"管理费金额(按月计算;单位：元)",name:"wycost",newline:true,labelWidth:230,width:220,space:30,type:"text",validate:{required:true,number:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"空调费总计金额(单位：元)",name:"ktcost",newline:true,labelWidth:230,width:220,space:30,type:"text",validate:{required:true,number:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"交纳方式",name:"paymodeTo",newline:true,labelWidth:150,width:220,space:30,type:"select",validate:{required:true},comboboxName:"paymodename",options:{valueFieldID:"paymode",data: [{ text: '每半年度', id: '1' },{ text: '每季度', id: '2' },{ text: '每月度', id: '3' }]},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"补充信息",name:"remark",newline:true,labelWidth:100,width:540,space:30,type:"textarea",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"}
         			],
		 			toJSON:JSON2.stringify
        });
        
        
		LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);

        if(isEdit){ 
            mainform.attr("action", "<%=basePath %>baseinfo/baseinfo_updateSupplier.action"); 
        }
        
        
        LG.loadForm(mainform, { url: '<%=basePath %>baseinfo/baseinfo_getSupplier.action',data:{sid:<%=CurrentID==""?117143:CurrentID %>} },f_loaded);
     
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
                    win.LG.showSuccess(data.Message, function () { 
                        win.LG.closeAndReloadParent(null, "baseInfo");
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