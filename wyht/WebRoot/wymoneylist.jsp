<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String MenuNo=request.getParameter("MenuNo");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>物业收费列表</title>
    
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="<%=basePath %>lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/common.js" type="text/javascript"></script>   
    <script src="<%=basePath %>lib/js/LG.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/ajaxfileupload.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/ligerui.expand.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/test.js" type="text/javascript"></script>
  </head>
<body style="padding:10px;height:100%; text-align:center;">
<jsp:include page="viewpage.jsp"></jsp:include>
   <ipnut type="hidden" id="MenuNo" value="<%=MenuNo  %>" />
 <div id="mainsearch" style=" width:98%">
    <div class="searchtitle">
        <span>搜索</span>
        
    </div>
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
        <div class="searchbox">
        <form id="formsearch" class="l-form"></form>
    </div>
  </div>
  <div id="maingrid"></div>   
  <script type="text/javascript">
      //相对路径
      var rootPath = "<%=basePath %>";
      //列表结构
      var grid = $("#maingrid").ligerGrid({
          columns: 
                [
				   	{display: "物业合同号", name: "htid", width: 120},
				   	{display: "租赁合同号", name: "zlcode", width: 120},
					{display:"乙方名称",name:"yiname", width: 120},
					{display:"所缴月份",name:"paidmonth", width: 100},
					{display:"缴费类别",name:"costname", width: 80},
					{display:"缴费金额(元)",name:"paid", width: 120,type:'currency'},
					{display:"缴纳滞纳金(元)",name:"paidznj", width: 120,type:'currency'},
					{display:"支付方式",name:"payment", width: 100},
					{display:"录入时间",name:"entrydate", width: 140},
					{display:"收款人",name:"entryuser", width: 100},
					{display:"发票",name:"invoice", width: 100},
					{display:"滞纳金减免原因",name:"znjreduce", width: 100}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=WyMoney',
           sortName: 'paidmonth', 
           pageSize: 20, 
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display:"租赁合同号",name:"zlcode",newline:true,labelWidth:100,width:220,space:30,type:"text",cssClass:"field",attr: { "op": "like"}},
		    {display: "物业合同号", name:"htid",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "like"}},
		    {display:"录入时间",name:"entrydate",newline:true,labelWidth:100,width:220,space:30,type:"date",cssClass:"field",attr: { "op": "equal"}},
		    {display: "录入人",name:"userTo",newline:false,labelWidth:100,width:220,space:30,type:"select",comboboxName:"entryuser",options:{valueFieldID:"entryuserid",url:'<%=basePath %>/json/json_wyuserBySeclect.action'},cssClass: "field", attr: { "op": "equal"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendSearchButtons("#formsearch", grid);
	  
	  
      
      function f_reload() {
          grid.loadData();
      }
      
      
      
      
     
      



      
  </script>
</body>
</html>