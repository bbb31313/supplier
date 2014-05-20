<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>欢迎使用供应商管理平台</title>
		<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
		<link href="lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet"
			type="text/css" />
		<script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
		<script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
		<link href="lib/css/common.css" rel="stylesheet" type="text/css" />
		<link href="lib/css/welcome.css" rel="stylesheet" type="text/css" />
		<script src="lib/jquery-validation/jquery.validate.min.js"
			type="text/javascript"></script>
		<script src="lib/jquery-validation/jquery.metadata.js"
			type="text/javascript"></script>
		<script src="lib/jquery-validation/messages_cn.js"
			type="text/javascript"></script>
		<script src="lib/jquery.form.js" type="text/javascript"></script>
		<script src="lib/js/common.js" type="text/javascript"></script>
		<script src="lib/js/LG.js" type="text/javascript"></script>
		<script src="lib/js/addfavorite.js" type="text/javascript"></script>

		<script type="text/javascript">
	$(function() {

		loadInfo()

	});

	function loadInfo() {
		$.ajax( {
			type : 'post',
			url : 'getusername.jsp',
			success : function(result) {
				if (result != '1') {
					$("#labelusername").html(result);
				}

			}
		});

		var now = new Date(), hour = now.getHours();
		if (hour > 4 && hour < 6) {
			$("#labelwelcome").html("凌晨好！")
		} else if (hour < 9) {
			$("#labelwelcome").html("早上好！")
		} else if (hour < 12) {
			$("#labelwelcome").html("上午好！")
		} else if (hour < 14) {
			$("#labelwelcome").html("中午好！")
		} else if (hour < 17) {
			$("#labelwelcome").html("下午好！")
		} else if (hour < 19) {
			$("#labelwelcome").html("傍晚好！")
		} else if (hour < 22) {
			$("#labelwelcome").html("晚上好！")
		} else {
			$("#labelwelcome").html("夜深了，注意休息！")
		}
	}
</script>

	</head>
	<body
		style="padding: 10px; overflow: auto; text-align: center; background: #FFFFFF;">
		<jsp:include page="viewpage.jsp"></jsp:include>
		<div class="navbar">
			<div class="navbar-l"></div>
			<div class="navbar-r"></div>
			<div class="navbar-icon">
				<img src="lib/icons/32X32/hire_me.gif" />
			</div>
			<div class="navbar-inner">
				<b><span id="labelusername"></span><span>，</span><span
					id="labelwelcome"></span><span>欢迎使用供应商管理平台</span> </b>
			</div>
		</div>

		<div class="navline">
		</div>


		<div class="l-clear"></div>


		<div class="navbar">
			<div class="navbar-l"></div>
			<div class="navbar-r"></div>
			<div class="navbar-icon">
				<img src="lib/icons/32X32/communication.gif" />
			</div>
			<div class="navbar-inner">
				<b>使用说明</b>
			</div>
		</div>

		<p style="margin: 10px;">
			1、在合同及收费管理栏目里，能够方便的对物业合同进行多种维护的操作,能够方便的记录收取物业管理费、空调费和其他费用。
		</p>
		<p style="margin: 10px;">
			2、新增合同，点击保存后。合同将保存在门店审核栏目里。在门店审核栏目里的合同，如提交审核，合同将会保存到总部审核栏目里。
		</p>
		<p style="margin: 10px;">
			3、在总部审核栏目里的合同，如点击审核通过，合同将保存在合同及收费管理栏目。该合同可以进行正常的收费工作。
		</p>
		<p style="margin: 10px;">
			4、报表汇总栏目里汇总了各种信息的报表，方便浏览。
		</p>



		<div class="navline"></div>

		<div class="navbar">
			<div class="navbar-l"></div>
			<div class="navbar-r"></div>
			<div class="navbar-icon">
				<img src="lib/icons/32X32/communication.gif" />
			</div>
			<div class="navbar-inner">
				<b>版本说明</b>
			</div>
		</div>





		<p style="margin: 10px;">
			1.5版本更新说明：
		</p>
		<p style="margin: 10px;">
			1、增加记录合同变更操作的功能
			</br>
			2、优化《收款员实收报表》增加归属月份字段
			</br>
			3、优化《归属月度收入报表》增加是否缴纳字段
			</br>
			4、增加《供应商欠款余额报表》
			</br>
			5、增加与EBS的接口
		</p>
		
		<div class="navline"></div>
		<p style="margin: 10px;">
			1.4版本更新说明：
		</p>
		<p style="margin: 10px;">
			1、增加数据导出功能
			</br>
			2、优化报表数据
			</br>
			3、所有报表增加物业合同号查询选项方便操作
			</br>
		</p>
		<div class="navline"></div>
		<p style="margin: 10px;">
			1.3版本更新说明：
		</p>
		<p style="margin: 10px;">
			1、增加合同变更功能
		</p>
		
		<div class="navline"></div>
		<p style="margin: 10px;">
			1.2版本更新说明：
		</p>
		<p style="margin: 10px;">
			1、增加费用收取功能。费用收取时分物业费和空调费。
			</br>
			2、增加《归属月度收入报表》，《收款员实收报表》，《供应商费用欠交明细表》，《滞纳金收取报表》，《滞纳金月度收取报表》
			</br>
			3、增加默认值选项功能。减少录入人员工作量。
			</br>
		</p>
	</body>
</html>
