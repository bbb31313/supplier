<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String htid=(String)request.getParameter("htid");
String MenuNo=request.getParameter("MenuNo");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改物业费</title>
    
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>   
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <link href="<%=basePath %>lib/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="<%=basePath %>lib/js/common.js" type="text/javascript"></script>   
    <script src="<%=basePath %>lib/js/LG.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/js/ligerui.expand.js" type="text/javascript"></script>  
    <script src="<%=basePath %>lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/js/ligerui.expand.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/jquery.form.js" type="text/javascript"></script>
    <style type="text/css">
    .l-panel td.l-grid-row-cell-editing { padding-bottom: 2px;padding-top: 2px;}
    </style>
  </head>
<body style="padding:10px;height:100%; text-align:center;">
<jsp:include page="viewpage.jsp"></jsp:include>
   <ipnut type="hidden" id="MenuNo" value="<%=MenuNo  %>" />
   <form id="mainform"><div id="maingrid" style="margin:2px;"></div></form>
  <script type="text/javascript">
  
  
      //相对路径
      var rootPath = "<%=basePath %>";
  
      var maingform = $("#mainform");
      $.metadata.setType("attr", "validate");
      LG.validate(maingform, { debug: true });
      
      var moneyfilter={
    	        op: 'and',
    	        rules: [
    	        { field: 'htid', value: '<%=htid %>', op: 'equal' }
    	        ]
    	    };
      
      //列表结构
      var grid = $("#maingrid").ligerGrid({
          columns: 
                [
				   	{display: "物业合同号", name: "htid",align: 'center', width: 120 },
				   	{display: "费用名称", name: "costname",align: 'center', width: 120 },
					{display:"应收月份",name:"accountmonth",align: 'center', width: 180 },
					{display:"金额",name:"account", width: 180,align: 'center', validate: { required:true,number: true }, editor: { type: 'text' }}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=WyAccount',
           sortName: 'accountmonth',
           sortOrder:'desc',
           pageSize: 100, 
           toolbar: {},
           parms:{ where:JSON2.stringify(moneyfilter) },
           width: '98%', height: '100%',heightDiff:-10, checkbox: false,enabledEdit: true, clickToEdit: false,
           fixedCellHeight: true, rowHeight: 25
      });
	  
      	  
	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  grid.bind('beforeSubmitEdit', function (e)
	    	    {
	    	        if (!LG.validator.form())
	    	        {
	    	            LG.showInvalid();
	    	            return false;
	    	        }
	    	        return true;
	    	    });
	  grid.bind('afterSubmitEdit', function (e)
	    	    {
	    	        var data = e.newdata;
	    	        
	    	        //$.ligerDialog.warn(e.record.htid+'   '+e.record.accountmonth+' , '+data.account);
	    	          	         
	                LG.ajax({
	               	  	url: rootPath+'bus/bus_modifyWyMoney.action',
	                    loading: '正在保存数据中...',
	                    data: {htid:e.record.htid,paymonth:e.record.accountmonth,money:data.account},
	                    success: function ()
	                    {
	                        grid.loadData();
	                        $.ligerDialog.success('修改成功!');
	                    },
	                    error: function (message)
	                    {
	                        $.ligerDialog.error(message.Message);
	                    }
	                });
	    	    }); 
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
    	  var editingrow = grid.getEditingRow();
          switch (item.id) {
          	case "modify":
          		if (editingrow == null)
                {
                    beginEdit();
                } else
                {
                    LG.tip('请先提交或取消修改');
                }
                break;
             case "save":
            	 if (editingrow != null)
                 {
                     grid.endEdit(editingrow);
                 } else
                 {
                     LG.tip('现在不在编辑状态!');
                 }
                 break; 
             case "cancel":
            	 if (editingrow != null)
                 {
                     grid.cancelEdit(editingrow); 
                 } else
                 {
                     LG.tip('现在不在编辑状态!');
                 }
                 break;              	
             
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
      
      function beginEdit()
      {
          var row = grid.getSelectedRow();
          if (!row) { LG.tip('请选择行'); return; }
          grid.beginEdit(row); 
      }
     
  </script>
</body>
</html>