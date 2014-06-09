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
    
    <title>供应商欠款余额表</title>
    
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
					{display:"租赁合同号",name:"zlcode", width: 90},
					{display:"物业合同号",name:"htid", width: 90},
					{display:"供应商编码",name:"ghdwdm", width: 90},
					{display:"供应商名称",name:"wldmname", width: 120},
					{display:"建筑面积",name:"jzmj", width: 90},
					{display:"合同开始时间",name:"startdate", width: 90},
					{display:"合同结束时间",name:"enddate", width: 90},
					{display:"应收月份",name:"accountmonth", width: 90},
					{display:"应收物业费",name:"wymoney", width: 90},
					{display:"应收空调费",name:"ktmoney", width: 90},
					{display:"应收物业滞纳金",name:"wyznj", width: 120},
					{display:"应收空调滞纳金",name:"ktznj", width: 90},
					{display:"应收合计",name:"accountsum", width: 90},
					{display:"已收物业费",name:"wypaid", width: 90},
					{display:"已收空调费",name:"ktpaid", width: 90},					
					{display:"已收物业滞纳金",name:"wyznjpaid", width: 90},
					{display:"已收空调滞纳金",name:"ktznjpaid", width: 90},
					{display:"已收合计",name:"paidsum", width: 120},
					{display:"欠缴物业费",name:"wylack", width: 90},
					{display:"欠缴空调费",name:"ktlack", width: 90},
					{display:"欠缴物业滞纳金",name:"wyznjlack", width: 90},
					{display:"欠缴空调滞纳金",name:"ktznjlack", width: 90},						
					{display:"欠缴合计",name:"lacksum", width: 90}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'wyrpt/rpt_getClassList.action?view=WyWldmLack',
           sortName: 'accountmonth', 
           pageSize: 20, 
           //toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display:"门店名称",name:"ToShop",newline:true,labelWidth:100,width:220,space:30,type:"select",comboboxName:"shopname",options:{valueFieldID:"shop",url:'<%=basePath %>/json/json_shopBySeclect.action'},cssClass:"field",attr: { "op": "like"}},
		   	{display: "供应商编码", name:"ghdwdm",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "like"}},
		    {display:"查询开始月份",name:"accountmonth",newline:true,labelWidth:100,width:220,space:30,type:"date",cssClass:"field",attr: { "op": "greaterorequal"}},
		    {display:"查询结束月份",name:"endaccountmonth",newline:false,labelWidth:100,width:220,space:30,type:"date",cssClass:"field",attr: { "op": "lessorequal"}},
		    {display: "物业合同号", name:"htid",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "equal"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendDownLoadButtons("#formsearch", grid);
	  
	  $("#accountmonth").ligerDateEditor({ format: "yyyy-MM" });
	  $("#endaccountmonth").ligerDateEditor({ format: "yyyy-MM" });

	  
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