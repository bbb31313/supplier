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
    
    <title>履约金情况列表</title>
    
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
				   	{display: "物业合同号", name: "wycode", width: 120},
					{display:"乙方名称",name:"yiname", width: 120},
					{display:"合同约定履约金",name:"lymoney", width: 120},
					{display:"现有履约金",name:"nowlymoney", width: 120}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=wyly',
           sortName: 'htid', 
           pageSize: 20, 
           toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display:"乙方名称",name:"yiname",newline:true,labelWidth:100,width:220,space:30,type:"text",cssClass:"field",attr: { "op": "like"}},
		    {display: "物业合同号", name:"wycode",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "like"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendSearchButtons("#formsearch", grid);
	  
	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
          	case "queryly":
          		var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请先选择合同!'); return }
                top.f_addTab(null, '缴费扣费查询', '<%=basePath %>lyquerylist.jsp?htid='+selected.htid);
                break;
             case "incost":
             	var selected = grid.getSelected();
                  if (!selected) { jQuery.ligerDialog.warn('请先选择合同!'); return }
                  showDetail({
                      htid:selected ? selected.htid : '',
                      costtype: '1',
                      wycode: selected ? selected.wycode : '',
                      yiname: selected ? selected.yiname : '',
                      money:'',
                      res:'',
                      payuserid:'',
                      payuser:''
                  });
             	break; 
             case "outcost":
             	var selected = grid.getSelected();
                  if (!selected) { jQuery.ligerDialog.warn('请先选择合同!'); return }
                  showDetail({
                      htid:selected ? selected.htid : '',
                      costtype: '2',
                      wycode: selected ? selected.wycode : '',
                      yiname: selected ? selected.yiname : '',
                      money:'',
                      res:'',
                      payuserid:'',
                      payuser:''
                  });
             	break;
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
      
       

   function title(id) {
   		if(id==1){
   			return '缴纳履约金';
   		}
   		if(id==2){
   			return '扣除履约金';
   		}
      }         
      
   var detailWin = null, curentData = null;
   function showDetail(data)
      {
          curentData = data;
           var mainform = $("#mainform");

          if (detailWin)
          {
          	  detailWin.set('title',title(curentData.costtype));
         	  $("#res").val('');
              detailWin.show(); 
          }
          else
          {
              //创建表单结构
             
              mainform.ligerForm({
                  inputWidth: 280,
                  fields: [
        		  {display: "金额", name: "money", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true, number:true} },
        		  {display: "事由", name: "res", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true} },
        		  {display: "收费人", name: "payuserTo", newline: true, labelWidth: 100, width: 220, space: 30, type:"select",validate:{required:true},comboboxName:"payuser",options:{valueFieldID:"payuserid",url:rootPath+"json/json_wyuserBySeclect.action" }}         		  
         ],
                  toJSON: JSON2.stringify
              });

        jQuery.metadata.setType("attr", "validate");
        LG.validate(mainform);
			
            detailWin = $.ligerDialog.open({
                  target: $("#detail"),
                  width: 450, height: 200,top:90,title:title(curentData.costtype),
                  buttons: [
                  { text: '确定', onclick: function () { save(); } },
                  { text: '取消', onclick: function () { detailWin.hide(); } }
                  ]
              });
          }

          function save()
          {
              if (curentData)
          	{
              curentData.money=$("#money").val();
              curentData.payuserid=$("#payuserid").val();
              curentData.payuser=$("#payuser").val();
              curentData.res=$("#res").val();
          	}
          if(mainform.valid()){
           LG.ajax({
             	  url: rootPath+'/ly/ly_addWylyMoney.action',
                  loading: '正在保存数据中...',
                  data: curentData,
                  success: function ()
                  {
                      grid.loadData();
                      LG.tip('保存成功!');
                      detailWin.hide();
                  },
                  error: function (message)
                  {
                      LG.tip(message.Message);
                  }
              });
          }
             

          }
      }


      
  </script>
</body>
</html>