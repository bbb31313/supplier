<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="pdz.supplier.bean.SupplierUser"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	src="<%=basePath%>lib/js/cloud-zoom.1.0.2.min.js"></script>
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
</style>
</head>
<body style=" overflow:hidden;">

	<div class="zoom-section">
		<div class="zoom-small-image">
			<a href='<%=basePath%>attachment/1/1_yyzz.jpg' class='cloud-zoom'
				rel="position:'inside',showTitle:false,adjustX:-4,adjustY:-4"><img
				src="<%=basePath%>attachment/1/1_yyzz.jpg" width=290 heigth=320 />
			</a>
		</div>
	</div>
	<!--zoom-section end-->
	<script type="text/javascript"> 

        
        var rootpath='<%=basePath%>
		';

		if (!isView) {
			//验证
			jQuery.metadata.setType("attr", "validate");
			LG.validate(mainform);
		}

		function f_loaded() {
			if (!isView)
				return;
			//查看状态，控制不能编辑
			$("input,select,textarea", mainform).attr("readonly", "readonly");
		}
	</script>

</body>
</html>