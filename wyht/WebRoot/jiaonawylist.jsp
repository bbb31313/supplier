<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String cType=request.getParameter("ctype");
String htid=request.getParameter("htid");
String MenuNo=request.getParameter("MenuNo");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>缴纳物业费用列表</title>
    
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
      <ipnut type="hidden" id="cType" value="<%=cType  %>" />
      <ipnut type="hidden" id="MenuNo" value="<%=MenuNo  %>" />
   <div id="detail" style="display:none;"><form id="mainform" method="post"></form> </div>
   <div id="wydetail" style="display:none;"><form id="wymainform" method="post"></form> </div>
  <div id="maingrid"></div>   
  <script type="text/javascript">
      //相对路径
      var rootPath = "<%=basePath %>";
      var cost=0 ;
      var znj=0;
      var checknum=0;
      //列表结构
      var grid = $("#maingrid").ligerGrid({
      			checkbox: true,
          		columns: 
                [
					{display: "物业合同号", name: "htid", width: 120},
					{display:"费用名称",name:"costname", width: 120},
					{display:"收入归属月份",name:"accountmonth", width: 120},
					{display:"合同缴款日期",name:"accountdate", width: 120},
					{display:"费用金额",name:"account", width: 120},
					{display:"滞纳金金额",name:"accountznj", width: 120}
                 ], 
           dataAction: 'server',
 		   url: rootPath + 'query/query_getClassList.action?view=WyJiaoNa&htid=<%=htid %>&ctype=<%=cType %>',
           sortName: 'accountmonth', 
           pageSize: 50, 
           toolbar: {},
           width: '98%', height: '100%',
           onCheckRow: f_onCheckRow, 
           onCheckAllRow: f_onCheckAllRow
      });
	  
	  
	  LG.loadToolbar(grid, toolbarBtnItemClick);
	  
	  //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
          	case "paidwy":
                  f_paidwy();
                  break;
            case "viewht":
                top.f_addTab(null, '查看合同', '<%=basePath %>htDetail.jsp?view=1&&htid=<%=htid %>' );
             	break;  
             case "paidwyzn":
             	f_paidznj();
             	break;  
          }
      }
      
      
      function f_reload() {
          grid.loadData();
      }
               
      
   var detailWin = null, curentData = null;
   function showZNJDetail(data)
      {
          curentData = data;
          var mainform = $("#mainform");

          if (detailWin)
          {
          	$.ligerui.get("accznj").setValue(curentData.wyznj);
          	$.ligerui.get("znjreduce").setValue('');
          	$.ligerui.get("payznj").setValue('');
            detailWin.show(); 
          }
          else
          {
              //创建表单结构
             
              mainform.ligerForm({
                  inputWidth: 280,
                  fields: [
                  {name:"htid",type:"hidden"},
                  {name:"costtype",type:"hidden"},
        		  {display:"应收滞纳金", name: "accznj", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true, number:true} },
        		  {display:"滞纳金金额", name: "payznj", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true, number:true} },
        		  {display:"支付方式",name:"paymodeTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"paymodename",options:{valueFieldID:"paymode",data: [{ text: '现金', id: '1' },{ text: '刷卡', id: '2' },{ text: '减免', id: '3' }]}},
        		  {display:"减免原因", name: "znjreduce", newline: true, labelWidth: 100, width: 220, space: 30, type:"text"}         		  
         ],
                  toJSON: JSON2.stringify
              });
              
        jQuery.metadata.setType("attr", "validate");
        LG.validate(mainform);
        $.ligerui.get("accznj").setValue(curentData.wyznj);
        $("#accznj").attr("readonly", "readonly");
		
		mainform.attr("action", "<%=basePath %>/money/money_addWyZNJ.action");
            detailWin = $.ligerDialog.open({
                  target: $("#detail"),
                  width: 450, height: 200,top:90,title:"滞纳金",
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
              curentData.money=$("#payznj").val();
              curentData.res=$("#znjreduce").val();
              curentData.paymode=$("#paymode").val();
          	}
          if(mainform.valid()){
              LG.ajax({
             	  url: rootPath+'money/money_addWyZNJ.action',
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
                      LG.tip(message);
                  }
              });
          }
          
			checknum=0;
			znj=0;
			cost=0;
          }
      }

   var wydetailWin = null, wycurentData = null;
   function showWYDetail(data)
      {
	   	  wycurentData = data;
          var wymainform = $("#wymainform");

          if (wydetailWin)
          {
          	$.ligerui.get("accwy").setValue(wycurentData.wy);
          	$.ligerui.get("invoice").setValue('');
          	$.ligerui.get("paydate").setValue('');
            wydetailWin.show(); 
          }
          else
          {
              //创建表单结构
             
              wymainform.ligerForm({
                  inputWidth: 280,
                  fields: [
                  {name:"htid",type:"hidden"},
                  {name:"costtype",type:"hidden"},
        		  {display:"应收金额", name: "accwy", newline: true, labelWidth: 100, width: 220, space: 30, type: "text", validate: { required: true, number:true} },
        		  {display:"支付方式",name:"wypaymodeTo",newline:true,labelWidth:100,width:220,space:30,type:"select",validate:{required:true},comboboxName:"wypaymodename",options:{valueFieldID:"wypaymode",data: [{ text: '现金', id: '1' },{ text: '刷卡', id: '2' },{ text: '其他', id: '4' }]}},
        		  {display:"到账日期", name: "paydate", newline: true, labelWidth: 100, width: 220, space: 30, type:"date",validate:{required:true}},
        		  {display:"发票", name: "invoice", newline: true, labelWidth: 100, width: 220, space: 30, type:"text",validate:{required:true}} 
         ],
                  toJSON: JSON2.stringify
              });
              
        jQuery.metadata.setType("attr", "validate");
        LG.validate(wymainform);
        $.ligerui.get("accwy").setValue(wycurentData.wy);
        $("#accwy").attr("readonly", "readonly");
		
		
            wydetailWin = $.ligerDialog.open({
                  target: $("#wydetail"),
                  width: 450, height: 200,top:90,title:"物业管理费",
                  buttons: [
                  { text: '确定', onclick: function () { save(); } },
                  { text: '取消', onclick: function () { wydetailWin.hide(); } }
                  ]
              });
          }


          function save()
          {
            if (wycurentData)
          	{
              wycurentData.paymode=$("#wypaymode").val();
              wycurentData.invoice=$("#invoice").val();
              wycurentData.paydate=$("#paydate").val();
          	}
          if(wymainform.valid()){
              LG.ajax({
            	  url: rootPath+'money/money_addWy.action',
                  loading: '正在保存数据中...',
                  data: wycurentData,
                  success: function ()
                  {
                      grid.loadData();
                      LG.tip('保存成功!');
                      wydetailWin.hide();
                  },
                  error: function (message)
                  {
                      LG.tip(message);
                  }
              });
          }
          
			checknum=0;
			znj=0;
			cost=0;
          }
      }




   function f_onCheckAllRow(checked)
        {
            for (var rowid in this.records)
            {
                if(checked)
                    addCheckedCustomer(changeTwoDecimal(this.records[rowid]['account']),changeTwoDecimal(this.records[rowid]['accountznj']));
                else
                    removeCheckedCustomer(changeTwoDecimal(this.records[rowid]['account']),changeTwoDecimal(this.records[rowid]['accountznj']));
            }
        }

        

        
        function addCheckedCustomer(wycost,wyznj)
        {
            //if(findCheckedCustomer(CustomerID) == -1)
                //checkedCustomer.push(CustomerID);
             
             cost=cost+wycost;
             znj=znj+wyznj;
             checknum=checknum+1;
             
        }
        
        function removeCheckedCustomer(wycost,wyznj)
        {
            
            cost=cost-wycost;
            znj=znj-wyznj;
            checknum=checknum-1;
        }

        function f_onCheckRow(checked, data)
        {
            if (checked) addCheckedCustomer(changeTwoDecimal(data.account),changeTwoDecimal(data.accountznj));
            else removeCheckedCustomer(changeTwoDecimal(data.account),changeTwoDecimal(data.accountznj));
        }
        
        function f_getChecked()
        {
            //alert(checkedCustomer.join(','));
            alert(cost+"_"+znj);
            
        }
        
        function changeTwoDecimal(x)
				{
   				var f_x = parseFloat(x);
   				if (isNaN(f_x))
   				{
     		 		alert('function:changeTwoDecimal->parameter error');
     		 		return false;
   				}
   				var f_x = Math.round(x*100)/100;

   				return f_x;
				}
		
        function f_paidwy()
        {
        	var selected = grid.getSelected();
            if (!selected) { jQuery.ligerDialog.warn('请先选择行!'); return }
        	if(znj>0){
        		jQuery.ligerDialog.warn('请先处理滞纳金!');  
        	}else{
        		var rows = grid.getSelecteds();
        		var str='';
        		var wymoney='';
        		for(var i=0;i<rows.length;i++){
					str=str+rows[i].accountmonth+',' ;
					wymoney=wymoney+rows[i].account+',' ;
                }
        		showWYDetail({
                    htid:selected ? selected.htid : '',
                    costtype: '1',
                    money:wymoney,
                    invoice:'',
                    paymode:'',
                    paydate:'',
                    accountmonth: str,
                    wy:cost
                	});
        	}
        }
        
        function f_paidznj(){
        	
        	var selected = grid.getSelected();
            if (!selected) { jQuery.ligerDialog.warn('请先选择行!'); return }
            if(znj>0){
            	if(checknum==1){
            		showZNJDetail({
                      htid:selected ? selected.htid : '',
                      costtype: '1',
                      money:'',
                      res:'',
                      paymode:'',
                      accountmonth: selected ? selected.accountmonth : '',
                      wyznj:znj
                  	});
            	}else{
            		jQuery.ligerDialog.warn('一次只能处理一个归属月份的滞纳金!');
            	}
            }else{
            	jQuery.ligerDialog.warn('归属月份的滞纳金已经处理完毕!');
            }
            
        	
        }
      
  </script>
</body>
</html>