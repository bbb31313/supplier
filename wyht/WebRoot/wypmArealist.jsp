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
    
    <title>楼层面积信息列表</title>
    
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
                   	{display: "门店名称", name: "shopname", width: 120 },
				   	{display: "楼层", name: "floor", width: 120},
					{display:"不可租面积",name:"notrentArea", width: 120},
					{display:"可租未租面积",name:"norentArea", width: 120},
					{display:"楼层面积",name:"allArea", width: 120}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=WYPMarea',
           sortName: 'shopid,floor', 
           pageSize: 20, 
           toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display: "所属门店",name:"shopTo",newline:false,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"shopname",options:{valueFieldID:"shopid",url:'<%=basePath %>/json/json_shopBySeclect.action'},cssClass: "field", attr: { "op": "equal"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendSearchButtons("#formsearch", grid);
	  
	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
          	case "addwypmarea":
                  top.f_addTab(null, '新增面积信息', '<%=basePath %>wypmAreaDetail.jsp');
                  break;
             case "viewypmarea":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '查看面积信息', '<%=basePath %>wypmAreaDetail.jsp?view=1&&wpaid=' + selected.wpaid );
             	break;  
             case "updatewypmarea":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '变更面积信息', '<%=basePath %>wypmAreaDetail.jsp?&wpaid=' + selected.wpaid );
             	break;
             case "delwypmarea":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                jQuery.ligerDialog.confirm('确定删除吗?',function(confirm){
                	if(confirm){f_delete();}
                });
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
                  url: rootPath+'pm/pm_delWyPMarea.action',
                  loading: '正在删除中...',
                  data: { wpaid: selected.wpaid },
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