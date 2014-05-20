<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String CurrentID="";
Object obj=request.getParameter("supid");
if(obj!=null){
	CurrentID=(String)obj;
}
boolean IsView=false;
Object objview=request.getParameter("view");
if(objview!=null){
	String view=(String)objview;
	if(view.equalsIgnoreCase("1")){
		IsView=true;
	}
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>供应商基本信息</title>
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

.form-bar-inner{ margin-right:20px;}
    </style> 
</head>
<body style="padding-bottom:31px;">
<jsp:include page="../viewpage.jsp"></jsp:include>
<div class="form-bar">
<div class="form-bar-inner">
</div>
</div>
    <form id="mainform" method="post"></form> 
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
         inputWidth: 280,
         fields : [
         			{name:"supID",type:"hidden"},
         			{display:"公司名称",name:"supname",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true,maxlength:20},group:"基本信息",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"注册地址",name:"supzcdz",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>/lib/icons/32X32/communication.gif"},
         			{display:"注册年份",name:"supzcnf",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true} ,groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"注册资金",name:"supzczj",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"公司类型", name: "supcategoryTO", comboboxName: "supcategory", newline: true, labelWidth: 100, width: 580,space: 30, validate:{required:true},type: "select", options: { valueFieldID: "supcategoryID",selectBoxHeight:400,tree: { url: "<%=basePath %>json/json_getCateTree.action", checkbox:false} } },
         			{display:"营业地址",name:"business_address",newline:true,labelWidth:100,width:580,space:30,type:"text",validate:{required:true},group:"",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"法定代表人",name:"representative",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"办公电话",name:"office_phone",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"公司联系人",name:"contactman",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"联系人电话号码",name:"contactphone",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"电子邮箱",name:"email",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"邮政编码",name:"zip_code",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"主营范围",name:"main_Range",newline:true,labelWidth:100,width:520,space:30,type:"textarea",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"}
         			],
		 			toJSON:JSON2.stringify
        });
        
		LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);
        
      
      	
        if(isEdit){ 
            mainform.attr("action", "<%=basePath %>/baseinfo/baseinfo_updateSupDoc.action"); 
        }
        
        if (isAddNew) {
            mainform.attr("action", "<%=basePath %>/baseinfo/baseinfo_addSupDoc.action");
        }
        else { 
            LG.loadForm(mainform, { url: '<%=basePath %>/baseinfo/baseinfo_getSupDoc.action',data:{supID:<%=CurrentID==""?1:CurrentID %>} },f_loaded);
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
                        win.LG.closeAndReloadParent(null, "baseInfobase");
                       // win.LG.closeCurrentTab(null);
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