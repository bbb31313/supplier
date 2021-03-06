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
    
    <title>供应商列表</title>
    
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="<%=basePath %>lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>  
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/common.js" type="text/javascript"></script>   
    <script src="<%=basePath %>lib/js/LG.js" type="text/javascript"></script>
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
                   { display: "供应商名称", name: "supname", width: 180 },
                   { display: "推荐部门", name: "depname", width: 120 },
                   { display: "所属类别", name: "supcategory", width: 180 },
                   { display: "级别", name: "supLEVEL", width: 180 },
                   { display: "公司性质", name: "companyType", width: 180 },
                   { display: "注册资金", name: "supzczj", width: 180 }, 
                   { display: "成立时间", name: "buildtime",  width: 180 }
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getListSupdoc.action?view=SupplierDoc',
           sortName: 'supname', 
           pageSize: 10, 
           toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
      


      $("#formsearch").ligerForm({
		   fields:[
		   {display:"供应商名称",name:"supname",newline:true,labelWidth:100,width:220,space:30,type:"text",cssClass:"field"},
		   {display:"供应商级别",name:"suplevelTo",comboboxName:"supLEVEL",newline:false,labelWidth:100,width:220,space:30,type:"select",cssClass:"field",attr: { "op": "equal"},options:{valueFieldID:"supLEVELID",url:rootPath+"/json/json_supLevelBySelect.action"}},
		   {display:"供应商类型", name: "supcategoryTO", comboboxName: "supcategory", newline: true, labelWidth: 100, width: 220,space: 30,type: "select",cssClass: "field", attr: { "op": "equal"}, options: { valueFieldID: "supcategoryID",selectBoxHeight:400,tree: { url: "<%=basePath %>json/json_getCateTree.action", checkbox:false} } }
		   ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendSearchButtons("#formsearch", grid);

	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
          case "addbase":
           	var selected = grid.getSelected();
              	if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
              	top.f_addTab(null, '新增', '<%=basePath %>supplierDetail.jsp?sid=' + selected.supID );
           	break;
           case "viewbase":
           	var selected = grid.getSelected();
              	if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
             	top.f_addTab(null, '详细信息', '<%=basePath %>supplierInfo.jsp?view=1&&sid=' + selected.supID );
           	break;  
           case "modifybase":
           		var selected = grid.getSelected();
              	if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
              	top.f_addTab(null, '修改', '<%=basePath %>supplierDetail?view=1&&sid=' + selected.supID );
           	break;  
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
      
      
      function f_delete() {
          var selected = grid.getSelected();
          
          if (selected) {
           LG.ajax({
                  url: '/fault/flt/sev_delFaultBug.action',
                  loading: '正在删除中...',
                  data: { bid: selected.bugid },
                  success: function () {
                      jQuery.ligerDialog.success('删除成功!');
                      f_reload();
                  },
                  error: function (message) {
                      jQuery.ligerDialog.error(message);
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