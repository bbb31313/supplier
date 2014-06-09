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
    
    <title>物业合同列表</title>
    
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
                   	{display: "租赁合同号", name: "zlcode", width: 120 },
				   	{display: "物业合同号", name: "htid", width: 120},
				   	{display: "所属门店", name: "shopname", width: 120},
					{display:"乙方名称",name:"yiname", width: 120},
					{display:"乙方电话",name:"yiphone", width: 120},
					{display:"乙方联系人",name:"yilxname", width: 120},
					{display:"乙方商铺号",name:"yishop", width: 120},
					{display:"合同有效开始时间",name:"startdate", width: 180},
					{display:"合同有效结束时间",name:"enddate", width: 180},
					{display:"物业管理费计算方式",name:"wycosttypename", width: 180},
					{display:"管理费(月/元)",name:"wycost", width: 180},
					{display:"空调费金额(总计)",name:"ktcost", width: 180},
					{display:"其他费用(月/元)",name:"qtcost", width: 180}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=WyHT1',
           sortName: 'htid', 
           pageSize: 20, 
           toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display:"乙方名称",name:"yiname",newline:true,labelWidth:100,width:220,space:30,type:"text",cssClass:"field",attr: { "op": "like"}},
		    {display: "租赁合同号", name:"zlcode",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "like"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendSearchButtons("#formsearch", grid);
	  
	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
             case "editht":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '修改合同', '<%=basePath %>adhtshopDetail.jsp?htid=' + selected.htid );
             	break;
             case "viewht":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '查看合同', '<%=basePath %>adhtshopDetail.jsp?view=1&&htid=' + selected.htid );
             	break;  
             case "delht":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                $.ligerDialog.confirm('确实要删除合同吗?', function (yes)
                 {
                   	if(yes)f_delHT();
                 });
             	break;  
             case "submitht":
            	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                jQuery.ligerDialog.confirm('确定提交总部审核吗?', function (confirm) {
                      if (confirm)
                          f_auditzb();
                  });
                  break; 
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
      
      
      function f_auditzb() {
          var selected = grid.getSelected();
          if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
          if (selected) {
           LG.ajax({
                  url: rootPath+'bus/bus_updateshopWyht.action',
                  loading: '正在审核中...',
                  data: { htid: selected.htid },
                  success: function () {
                      jQuery.ligerDialog.success('审核成功!');
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
      
     function f_delHT()
     {
     	var selected = grid.getSelected();
          if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
          if (selected) {
           LG.ajax({
                  url: rootPath+'bus/bus_delSimulateWyht.action',
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