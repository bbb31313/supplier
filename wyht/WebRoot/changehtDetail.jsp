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

boolean IsOther=false;
Object other=request.getParameter("other");
if(other!=null) IsOther=true;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>合同信息</title>
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
        //是否编辑其他选项
        var isOther =<%=IsOther %>;

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
         			<%
         				if(!IsEdit){
         				out.println("{display:'租赁合同号',name:'zlcodeTo',newline:true,labelWidth:100,width:220,space:30,type:'select',validate:{required:true},comboboxName:'zlcode',options:{valueFieldID:'zlcodeid'}},");
         				}
         				if(IsView){
         				out.println("{display:'物业合同号',name:'htid',newline:true,labelWidth:100,width:220,space:30,type:'text',validate:{required:true}},");
         				}
         			%>
         			{display:"乙方名称",name:"yiname",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方地址",name:"yiaddress",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方电话",name:"yiphone",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方传真",name:"yifax",newline:false,labelWidth:100,width:220,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方联系人",name:"yilxname",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方身份证",name:"yisfz",newline:false,labelWidth:100,width:220,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"乙方商铺号",name:"yishop",newline:true,labelWidth:100,width:220,space:30,type:"text",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			<%
         				if(!IsOther){
         			%>
         			{display:"建筑面积(平方米)",name:"jzmj",newline:true,labelWidth:140,width:260,space:30,type:"text",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"合同有效开始时间",name:"startdate",newline:true,labelWidth:150,width:220,space:30,type:"date",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
					{display:"合同有效结束时间",name:"enddate",newline:false,labelWidth:150,width:220,space:30,type:"date",validate:{required:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"物业管理费计算方式",name:"wycosttypeTo",newline:true,labelWidth:150,width:220,space:30,type:"select",validate:{required:true},comboboxName:"wycosttypename",options:{valueFieldID:"wycosttype",data: [{ text: '按经营面积计算', id: '1' },{ text: '按建筑面积计算', id: '2' }]},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			<%
         			}
         			%>
         			
         			<%
         				if(!IsOther){
         			%>
         			{display:"管理费金额(按月计算;单位：元)",name:"wycost",newline:true,labelWidth:230,width:220,space:30,type:"text",validate:{required:true,number:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"空调费总计金额(单位：元)",name:"ktcost",newline:true,labelWidth:230,width:220,space:30,type:"text",validate:{required:true,number:true},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			{display:"交纳方式",name:"paymodeTo",newline:true,labelWidth:150,width:220,space:30,type:"select",validate:{required:true},comboboxName:"paymodename",options:{valueFieldID:"paymode",data: [{ text: '每半年度', id: '1' },{ text: '每季度', id: '2' },{ text: '每月度', id: '3' }]},groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"},
         			<%
         				}
         			%>
         			
         			{display:"补充信息",name:"remark",newline:true,labelWidth:100,width:540,space:30,type:"textarea",groupicon:"<%=basePath %>lib/icons/32X32/communication.gif"}
         			],
		 			toJSON:JSON2.stringify
        });
        if (isAddNew){
        	LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);
        }else{
        	LG.setAccountBtn(f_cancel,isView ? null : f_save,f_manger,f_kt);
        }
		

 		if(isEdit){ 
 		   if(isOther){
 		      mainform.attr("action", "<%=basePath %>bus/bus_updateOtherWyht.action");
 		   }else{
 		   	  mainform.attr("action", "<%=basePath %>bus/bus_modifyWyht.action");
 		   }
             
            
        }
        
       if (isAddNew) {
           		 mainform.attr("action", "<%=basePath %>/bus/bus_addWyht.action");
            //租赁合同号在弹出grid中选择
        	$.ligerui.get("zlcode").openSelect({
           	grid: { 
               columns: [
              {display:"租赁合同号",name:"hth",width:100},
              {display:"供应商名称",name:"wldmname",width:120},
              {display:"供应商地址",name:"address",width:180},
              {display:"供应商联系人",name:"lxrmc",width:150},
              {display:"签署门店",name:"mcmc",width:180}
               ], pageSize: 20, 
              url: '<%=basePath%>query/query_getClassList.action?view=WyZLht', sortName: 'hth',checkbox: false
            },
            search:{
		        fields:[
                    {display:"供应商名称",name:"wldmname",newline:true,type:"text",cssClass:"field",attr: { "op": "like"}},
                    {display:"租赁合同号",name:"hth",newline:false,type:"text",cssClass:"field",attr: { "op": "like"}}
                ]
            },  
            valueField:'hth',textField:'hth',top:60
        });
        
        $.ligerui.get("zlcode").set('onSelected', function(){
        	$.ajax({
            	type: "post",
            	dataType: "json",
            	url: "json/json_getYiInfo.action",
            data: "zlid=" + $("#zlcode").val(),
            error: function (msg) {
                             jQuery.ligerDialog.error("请求信息失败!");
                        } ,
            success: function(data){
               		$("#yiname").val(data.Data.wldmname);
               		$("#yiaddress").val(data.Data.address);
               		$("#yilxname").val(data.Data.lxrmc);
               		$("#yiphone").val(data.Data.phone);
               		$("#startdate").val(data.Data.startDate);
               		$("#enddate").val(data.Data.endDate);
               		$("#jzmj").val(data.Data.jzmj);
                }
                });
        });
        f_Default();
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
        
        function f_Default()
		{
			$.ligerui.get("jianame").setValue("湖南湘吉物业管理有限责任公司");
			$.ligerui.get("jiaaddress").setValue("吉首市武陵东路");
			$("#wycosttypename").ligerGetComboBoxManager().setValue(2);
			$("#paymodename").ligerGetComboBoxManager().setValue(3);
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
                    	if(<%=IsEdit %>){
                    		win.LG.closeAndReloadParent(null, "htmodify");
                    	}else{
                    		win.LG.closeAndReloadParent(null, "htmodify");
                    	}
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
        
          function f_manger()
        {
        	$.ligerDialog.open({ height: 400,width: 680, url: 'accountlist.jsp?ctype=1&htid='+currentID, isResize: true });
        }
        
        function f_kt()
        {
        	$.ligerDialog.open({ height: 400,width: 680, url: 'accountlist.jsp?ctype=2&htid='+currentID, isResize: true });
        }

    </script>

</body>
</html>