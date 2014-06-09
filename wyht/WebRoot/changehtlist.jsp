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
    
    <title>物业合同变更列表</title>
    
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
   <div id="ktdetail" style="display:none;"><form id="ktmainform" method="post"></form> </div>
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
					{display:"履约保证金(元)",name:"qtcost", width: 180},
					{display:"交纳方式",name:"paymodename", width: 180}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=WyHT4',
           sortName: 'htid', 
           pageSize: 20, 
           toolbar: {},
           width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });
	  
      $("#formsearch").ligerForm({
		   fields:[
		   	{display:"乙方名称",name:"yiname",newline:true,labelWidth:100,width:220,space:30,type:"text",cssClass:"field",attr: { "op": "like"}},
		    {display: "租赁合同号", name:"zlcode",newline:false,labelWidth:100,width:220,space:30,type:"text",cssClass: "field", attr: { "op": "like"}},
		    {display:"门店名称",name:"ToShop",newline:true,labelWidth:100,width:220,space:30,type:"select",comboboxName:"shopname",options:{valueFieldID:"shop",url:'<%=basePath %>/json/json_shopBySeclect.action'},cssClass:"field",attr: { "op": "like"}}
		    ],
		   toJSON: JSON2.stringify
	  });
	  
	  LG.appendSearchButtons("#formsearch", grid);
	  
	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
          	case "adddate":
                var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '顺延合同', '<%=basePath %>changehtDetail.jsp?htid=' + selected.htid );
                break;
             case "overdate":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '终止合同', '<%=basePath %>changehtDetail.jsp?htid='+ selected.htid );
             	break;  
             case "otheroption":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '编辑合同', '<%=basePath %>changehtDetail.jsp?other=1&htid='+ selected.htid );
             	break;              	
             case "modifywymoney":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '修改物业管理费', '<%=basePath %>modifywycost.jsp?MenuNo=modifywymoney&htid='+ selected.htid );
                //showDetail({htid:selected ? selected.htid : '',paymonth:'',money:''});
             	break;  
             case "modifyktmoney":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                showktDetail({htid:selected ? selected.htid : '',money:''});
             	break; 
             case "lookmodify":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                top.f_addTab(null, '查看合同', '<%=basePath %>changehtDetail.jsp?view=1&&htid=' + selected.htid );
             	break;  
             case "auditmodify":
             	var selected = grid.getSelected();
                if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
                f_auditmodifyHT();
                break;  
             case "changenote":
                top.f_addTab(null, '变更记录', '<%=basePath %>htchangeNotelist.jsp' );
                break; 
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
      
      
      function f_auditmodifyHT() {
          var selected = grid.getSelected();
          if (!selected) { jQuery.ligerDialog.warn('请选择行!'); return }
          if (selected) {
           LG.ajax({
                  url: rootPath+'bus/bus_auditModifyWyht.action',
                  loading: '正在提交审核...',
                  data: { htid: selected.htid },
                  success: function () {
                      jQuery.ligerDialog.success('审核通过!');
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
      
    
   var detailWin = null, curentData = null;
   function showDetail(data)
      {
          curentData = data;
           var mainform = $("#mainform");

          if (detailWin)
          {
          	var manger;
          	manger=$("#paymonth").ligerComboBox();
          	 $.ajax({
            	type: "post",//使用get方法访问后台
            	dataType: "json",//返回json格式的数据
            	url: rootPath+"json/json_aMonthBySeclect.action",//要访问的后台地址
            	data: "htid="+curentData.htid,//要发送的数据
            	error: function (errorThrown) {
                             alert("加载失败!");
                                        } ,
            	success: function(msg){//msg为返回的数据，在这里做数据绑定
                var newData = msg;
                manger.setData(newData);
                }
                });
              detailWin.show(); 
          }
          else
          {
              //创建表单结构
             
              mainform.ligerForm({
                  inputWidth: 280,
                  fields: [
                  {name:"htid",type:"hidden"},
        		  {display: "所修改时间段", name: "paymonthTo", newline: true, labelWidth: 100, width: 220, space: 30, type: "select", validate: { required: true},comboboxName:"paymonth", options:{valueFieldID:"paymonthid",url:rootPath+"json/json_aMonthBySeclect.action?htid="+curentData.htid}},
        		  {display: "物业金额", name: "money", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true, number:true} }
         ],
                  toJSON: JSON2.stringify
              });
              
        jQuery.metadata.setType("attr", "validate");
        LG.validate(mainform);

			mainform.attr("action", "<%=basePath %>/bus/bus_addWyMoney.action");
            detailWin = $.ligerDialog.open({
                  target: $("#detail"),
                  width: 450, height: 200,top:90,title:"修改物业管理费",
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
              curentData.paymonth=$("#paymonthid").val();
          	}
          	if(mainform.valid()){
              LG.ajax({
             	  url: rootPath+'bus/bus_modifyWyMoney.action',
                  loading: '正在保存数据中...',
                  data: curentData,
                  success: function ()
                  {
                      grid.loadData();
                      LG.tip('修改成功!');
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
      
      
	var ktdetailWin = null, ktcurentData = null;
	function showktDetail(data)
      {
          ktcurentData = data;
          var mainform = $("#ktmainform");

          if (ktdetailWin)
          {
          	$("#ktmoney").val('');
            ktdetailWin.show(); 
          }
          else
          {
              //创建表单结构
             
              mainform.ligerForm({
                  inputWidth: 280,
                  fields: [
        		  {display: "空调费总金额", name: "ktmoney", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true, number:true} }
         ],
                  toJSON: JSON2.stringify
              });
              
        jQuery.metadata.setType("attr", "validate");
        LG.validate(mainform);

			
            ktdetailWin = $.ligerDialog.open({
                  target: $("#ktdetail"),
                  width: 450, height: 100,top:90,title:"修改空调费",
                  buttons: [
                  { text: '确定', onclick: function () { save(); } },
                  { text: '取消', onclick: function () { ktdetailWin.hide(); } }
                  ]
              });
          }


          function save()
          {
            if (ktcurentData)
          	{
              ktcurentData.money=$("#ktmoney").val();
          	}
          	if(mainform.valid()){
              LG.ajax({
             	  url: rootPath+'bus/bus_modifyKTMoney.action',
                  loading: '正在保存数据中...',
                  data: ktcurentData,
                  success: function ()
                  {
                      grid.loadData();
                      LG.tip('修改成功!');
                      ktdetailWin.hide();
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