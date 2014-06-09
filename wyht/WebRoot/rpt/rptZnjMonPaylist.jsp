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
    
    <title>滞纳金月度收入报表</title>
    
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
    <script src="<%=basePath %>lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
  </head>
<body style="padding:10px;height:100%; text-align:center;">
<jsp:include page="../viewpage.jsp"></jsp:include>
   <ipnut type="hidden" id="MenuNo" value="<%=MenuNo  %>" />
   <div id="detail" style="display:none;"><form id="mainform" method="post"></form> </div>
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
                   	{display: "所属门店", name: "shopname", width: 120 },
				   	{display: "归属月份", name: "paidmonth", width: 90},
					{display:"租赁合同号",name:"zlcode", width: 90},
					{display:"物业合同号",name:"htid", width: 90},
					{display:"供应商编码",name:"ghdwdm", width: 90},
					{display:"供应商名称",name:"wldmname", width: 120},
					{display:"应缴物业滞纳金",name:"wyznj", width: 90},
					{display:"应缴空调滞纳金",name:"ktznj", width: 90},
					{display:"减免物业滞纳金",name:"jmwyznj", width: 90},
					{display:"实收物业滞纳金",name:"shwyznj", width: 90},
					{display:"减免空调滞纳金",name:"jmktznj", width: 90},
					{display:"实收空调滞纳金",name:"shktznj", width: 90}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'wyrpt/rpt_getClassList.action?view=RptZnjPayMon',
           sortName: 'htid', 
           pageSize: 20, 
           //toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display:"门店名称",name:"ToShop",newline:true,labelWidth:100,width:220,space:30,type:"select",comboboxName:"shopname",options:{valueFieldID:"shop",url:'<%=basePath %>/json/json_shopBySeclect.action'},cssClass:"field",attr: { "op": "like"}},
		   	{display: "供应商编码", name:"ghdwdm",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "like"}},
		    {display:"查询月份",name:"paidmonth",newline:true,labelWidth:100,width:220,space:30,type:"date",cssClass:"field",attr: { "op": "equal"}},
		    {display: "物业合同号", name:"htid",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "equal"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendDownLoadButtons("#formsearch", grid);
	  
	  $("#paidmonth").ligerDateEditor({ format: "yyyy-MM" });
	  $("#endpaidmonth").ligerDateEditor({ format: "yyyy-MM" });
	  
	  //LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
          	case "exprot":
                  //top.f_addTab(null, '新增合同', '<%=basePath %>htDetail.jsp');
                  break;
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
      
      
      function f_delete() {
          var selected = grid.getSelected();
          if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
          if (selected) {
           LG.ajax({
                  url: rootPath+'bus/bus_delWyht.action',
                  loading: '正在删除中...',
                  data: { htid: selected.htid },
                  success: function () {
                      jQuery.ligerDialog.success('删除成功!');
                      f_reload();
                  },
                  error: function (message) {
                      jQuery.ligerDialog.error(message.Message);
                  }
              });
          }
          else {
              jQuery.ligerDialog.error('请选择行!');
          }
      }
      

   


      
  </script>
</body>
</html>