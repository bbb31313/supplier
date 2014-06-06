<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="pdz.supplier.bean.SupplierUser,pdz.supplier.bean.SupplierLicense"%>
<%@ page import="pdz.supplier.service.SupBaseInfoService"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	boolean isAdmin = false;
	Object userobj = request.getSession().getAttribute("supuser");
	if (userobj == null) {
		out.println("<script type='text/javascript'>top.location.href='"
				+ basePath + "login.htm'</script>");
	} else {
		SupplierUser sUser = (SupplierUser) userobj;
		if (sUser.getRole() == 1)
			isAdmin = true;
	}

	String CurrentID = "";
	boolean ISAdd = true;
	Object obj = request.getParameter("sid");
	System.out.println("obj:" + obj.toString());
	if (obj != null) {
		CurrentID = (String) obj;
		ISAdd = false;
	}

	boolean IsView = false;
	Object objview = request.getParameter("view");
	if (objview != null) {
		String view = (String) objview;
		if (view.equalsIgnoreCase("1")) {
			IsView = true;
		}
	}

	boolean IsEdit = !ISAdd && !IsView;
	
	WebApplicationContext context = (WebApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	SupBaseInfoService xCom=(SupBaseInfoService)context.getBean("supBaseInfoService");
	List<SupplierLicense> licenes=xCom.getLicensesBySupId(CurrentID);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">

<title>供应商信息</title>
<link href="<%=basePath%>lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>lib/css/common.css" rel="stylesheet"
	type="text/css" />
<script src="<%=basePath%>lib/jquery/jquery-1.5.2.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/ligerUI/js/ligerui.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/js/common.js" type="text/javascript"></script>
<script src="<%=basePath%>lib/js/LG.js" type="text/javascript"></script>
<script src="<%=basePath%>lib/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="<%=basePath%>lib/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath%>lib/json2.js" type="text/javascript"></script>
<script src="<%=basePath%>lib/js/validator.js" type="text/javascript"></script>
<script src="<%=basePath%>lib/js/ligerui.expand.js"
	type="text/javascript"></script>
<link href="<%=basePath%>lib/css/cloud-zoom.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>lib/js/cloud-zoom.1.0.2.js"></script>
<style type="text/css">
.cell-label {
	width: 80px;
}

#tabcontainer .l-tab-links {
	border-top: 1px solid #D0D0D0;
	border-left: 1px solid #D0D0D0;
	border-right: 1px solid #D0D0D0;
}

.projectgrid .l-selected .l-grid-row-cell,.projectgrid .l-selected {
	background: none;
}

.access-icon {
	background: url(../lib/ligerUI/skins/Aqua/images/controls/checkbox.gif)
		0px 0px;
	height: 13px;
	line-height: 13px;
	width: 13px;
	margin: 4px 20px;
	display: block;
	cursor: pointer;
}

.access-icon-selected {
	background-position: 0px -13px;
}

.l-panel td.l-grid-row-cell-editing {
	padding-bottom: 2px;
	padding-top: 2px;
}
/* zoom-section */
.zoom-section{display:inline;clear:both;}
.zoom-desc{margin-left:10px;float:left;width:800px;}
.zoom-small-image{float:left;width:290px;heigth:200px;}
.zoom-tiny-image{border:1px solid #CCC;margin:0px;}
.zoom-tiny-image:hover{border:1px solid #C00;}
</style>
</head>
<body style=" overflow:hidden;">

	<div id="layout" style="margin:2px; margin-right:3px;">
		<div position="center" id="mainmenu" title="供应商信息">
			<div id="tabcontainer" style="margin:2px;">
				<div title="基本信息" tabid="infolist">
					<form id="mainform" method="post"></form>
				</div>
				<div title="合作项目" tabid="prjlist">
					<div id="projectgrid" ></div>
				</div>

			</div>
		</div>
		<div position="bottom" title="附件信息">
			<div class="zoom-section">
			<%
				if(licenes.size()>0){
					SupplierLicense s1=licenes.get(0);
					out.println("<div class='zoom-small-image'><a href='"+basePath+s1.getLicenseImage()+"' class = 'cloud-zoom' id='zoom1' rel=\"adjustX:10, adjustY:-4\"><img src='"+basePath+s1.getLicenseImage()+"' width='290' heigth='200' /></div>");
					out.println("<div class='zoom-desc'><p>");
					for(SupplierLicense suplice : licenes){
						out.println("<a href='"+basePath+suplice.getLicenseImage()+"' class='cloud-zoom-gallery'  rel=\"useZoom: 'zoom1', smallImage: '"+basePath+suplice.getLicenseImage()+"' \"><img class='zoom-tiny-image' src='"+basePath+suplice.getLicenseImage()+"' width='120' heigth='120' /></a>");
					}
					out.println("<p></div>");
				}else{
					out.println("<h2>供应商暂无附件.</h2>");
				}
			%>
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
        //当前ID
        var currentID = '<%=CurrentID%>';
        //是否新增状态
        var isAddNew = currentID == "" || currentID == "0";
        //是否查看状态
        var isView = <%=IsView%>;
        //是否编辑状态
        var isEdit = !isAddNew && !isView;
        
        var rootPath='<%=basePath%>' ;

        //覆盖本页面grid的loading效果
        LG.overrideGridLoading(); 

        var layout = $("#layout").ligerLayout({
            //5分之3的高度
            bottomHeight: 2 * $(window).height() / 5,
            heightDiff: -6
        });
        var bottomHeader = $(".l-layout-bottom > .l-layout-header:first");

        var tab = $("#tabcontainer").ligerTab();

        var attachmentLoaded = false;
        
        var projectFilter={
                op: 'and',
                rules: [
                        { field: 'supid', value: '<%=CurrentID%>' , op: 'equal' }
                    ]
            };
        
        var projectgrid;
        
        
        
        projectgrid=$("#projectgrid").ligerGrid({
            columns:
                [
                { display: '供应商名称', name: 'supname', width: 150, align: 'center' },
                { display: '合作门店', name: 'shopname', width: 150, align: 'center' },
                { display: '项目名称', name: 'projectname', width: 150, align: 'center' },
                { display: '评价结果', name: 'evallevel', width: 150, align: 'center' ,
                	render: function (record, rowindex, value, column) {
								var result;
								if(value==1)result="很差";
								if(value==2)result="差";
								if(value==3)result="合格";
								if(value==4)result="好";
								if(value==5)result="优秀";
								return result;
					}},
				{ display: '项目评价', name: 'evalproject', width: 550, align: 'center' }
                ], showToggleColBtn: false, rowHeight: 20, fixedCellHeight: true,
            	 frozen: false, sortName: 'shopname', usePager: false, checkbox: false, rownumbers: true,
            	url: '<%=basePath%>query/query_getListSupdoc.action?view=Supplierbbgproject', parms: { where: JSON2.stringify(projectFilter) }
        });
        
        //创建表单结构
        var mainform = $("#mainform");  
        mainform.ligerForm({ 
         inputWidth: 370,
         fields : [
         			{display:"公司名称",name:"supname",newline:true,labelWidth:100,width:220,space:10,type:"text",validate:{required:true}},
         			{display:"类别",name:"supcategory",newline:false,labelWidth:100,width:120,space:10,type:"text",validate:{required:true}},
         			{display:"级别",name:"supLEVEL",newline:false,labelWidth:100,width:120,space:10,type:"text",validate:{required:true}},
         			
         			{display:"注册地址",name:"supzcdz",newline:true,labelWidth:100,width:220,space:10,type:"text",validate:{required:true}},
         			{display:"注册年份",name:"supzcnf",newline:false,labelWidth:100,width:120,space:10,type:"text",validate:{required:true}},
         			{display:"注册资金",name:"supzczj",newline:false,labelWidth:100,width:120,space:10,type:"text",validate:{required:true}},
         			{display:"公司类型",name:"companyType",newline:false,labelWidth:100,width:120,space:10,type:"text",validate:{required:true}},
         			
         			{display:"营业执照",name:"businessLicense",newline:true,labelWidth:100,width:220,space:10,type:"text",validate:{required:true}},
         			{display:"营业执照有效期",name:"businessLicensetime",newline:false,labelWidth:100,width:120,space:10,type:"text"},
         			{display:"机构代码",name:"orgcode",newline:true,labelWidth:100,width:220,space:10,type:"text",validate:{required:true}},
         			{display:"机构代码有效期",name:"orgcodetime",newline:false,labelWidth:100,width:120,space:10,type:"text"},
         			
         			<%if (isAdmin) {
				out.println("{display:'法定代表人',name:'representative',newline:true,labelWidth:100,width:220,space:30,type:'text',validate:{required:true}},");
				out.println("{display:'电话号码',name:'office_phone',newline:false,labelWidth:100,width:220,space:30,type:'text',validate:{required:true}},");
				out.println("{display:'联系人',name:'contactman',newline:true,labelWidth:100,width:220,space:30,type:'text',validate:{required:true}},");
				out.println("{display:'联系电话',name:'contactphone',newline:false,labelWidth:100,width:220,space:30,type:'text',validate:{required:true}},");
			}%>
         			{display:"备注",name:"remark",newline:true,labelWidth:100,width:540,space:10,type:"text"}
         			],
		 			toJSON:JSON2.stringify
        });
        
        LG.loadForm(mainform, { url: '<%=basePath%>baseinfo/baseinfo_getSupDoc.action',data:{supID:<%=CurrentID == "" ? 117143 : CurrentID%>} },f_loaded);    
        
                
		function f_loaded()
        {
            if(!isView) return; 
            //查看状态，控制不能编辑
            $("input,select,textarea",mainform).attr("readonly", "readonly");
        }
        
		

		

    </script>

</body>
</html>