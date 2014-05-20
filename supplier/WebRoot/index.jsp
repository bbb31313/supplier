<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pdz.supplier.bean.SupplierUser"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>步步高百货供应商管理平台</title>
		<link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css"  rel="stylesheet" type="text/css" />
		<link href="lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
		<script src="lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
		<script src="lib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
		<script src="lib/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
		<link href="lib/css/common.css" rel="stylesheet" type="text/css" />
		<link href="lib/css/index.css" rel="stylesheet" type="text/css" />
		<script src="lib/js/common.js" type="text/javascript"></script>
		<script src="lib/js/LG.js" type="text/javascript"></script>
		<script src="lib/js/login.js" type="text/javascript"></script>
		<script src="lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
		<script src="lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
		<script src="lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
		<script src="lib/js/changepassword.js" type="text/javascript"></script>
		<script src="lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>		

	<script type="text/javascript">
		
		//几个布局的对象
        var layout, tab, accordion;
        //tabid计数器，保证tabid不会重复
        var tabidcounter = 0;
        //窗口改变时的处理函数
        function f_heightChanged(options) {
            if (tab)
                tab.addHeight(options.diff);
            if (accordion && options.middleHeight - 24 > 0)
                accordion.setHeight(options.middleHeight - 24);
        }
        //增加tab项的函数
        function f_addTab(tabid, text, url) {
            if (!tab) return;
            if (!tabid)
            {
                tabidcounter++;
                tabid = "tabid" + tabidcounter; 
            }
            tab.addTabItem({ tabid: tabid, text: text, url: url });
        }

        //修改密码
        function f_changepassword()
        {
            LG.changepassword();
        }
       
        $(document).ready(function ()
        {

            //菜单初始化
            $("ul.menulist li").live('click', function ()
            {
                var jitem = $(this);
                var tabid = jitem.attr("tabid");
                var url = jitem.attr("url");
                if (!url) return;
                if (!tabid)
                {
                    tabidcounter++;
                    tabid = "tabid" + tabidcounter;
                    jitem.attr("tabid", tabid);
                    jitem.attr("url", url);
                }
                f_addTab(tabid, $("span:first", jitem).html(), url);
            }).live('mouseover', function ()
            {
                var jitem = $(this);
                jitem.addClass("over");
            }).live('mouseout', function ()
            {
                var jitem = $(this);
                jitem.removeClass("over");
            });

            //布局初始化 
            //layout
            layout = $("#mainbody").ligerLayout({ height: '95%', heightDiff: -3, leftWidth: 140, onHeightChanged: f_heightChanged, minLeftWidth: 120 });
            var bodyHeight = $(".l-layout-center:first").height();
            //Tab
            tab = $("#framecenter").ligerTab({ height: bodyHeight, contextmenu: true });


            //预加载dialog的背景图片
            LG.prevDialogImage();
			
			var mainmenu = $("#mainmenu");

            $.getJSON('<%=basePath%>json/json_getSupplierMenus.action', function (menus)
            {
            	
                $(menus).each(function (i, menu)
                {
                    var item = $('<div title="' + menu.menuName + '"><ul class="menulist"></ul></div>');

                    $(menu.children).each(function (j, submenu)
                    {
                        //var subitem = $('<li><img/><span></span><div class="menuitem-l"></div><div class="menuitem-r"></div></li>');
                        var subitem = $('<li><span></span><div class="menuitem-l"></div><div class="menuitem-r"></div></li>');
                        subitem.attr({
                            url: submenu.menuUrl,
                            menuno: submenu.menuNo
                        });
                        //$("img", subitem).attr("src", submenu.menuIcon || submenu.icon);
                        $("span", subitem).html(submenu.menuName || submenu.text);

                        $("ul:first", item).append(subitem);
                    });
                    mainmenu.append(item);

                });

                //Accordion
                accordion = $("#mainmenu").ligerAccordion({ height: bodyHeight - 24, speed: null });



                $("#pageloading").hide();
                
                $.ajax({
   				    type: 'post',
       				url: 'getusername.jsp',
       				success: function (result)
        			{
        				if(result!='1'){
        					$(".l-topmenu-username").html(result + "，");
        				}
        				
        			}
				});
            });
            

        });
    </script>			
	</head>
	<jsp:include page="viewpage.jsp"></jsp:include>
	<body
		style="text-align: center; background: #F0F0F0; overflow: hidden;">
		<div id="pageloading" style="display: block;"></div>
		<div id="topmenu" class="l-topmenu">
			<div class="l-topmenu-logo">
				步步高百货供应商管理平台
			</div>
			<div class="l-topmenu-welcome">
				<span class="l-topmenu-username"></span>欢迎您 &nbsp; [
				<a href="javascript:f_changepassword()">修改密码</a>] &nbsp; [
				<a href="outsys.jsp">退出</a>]
			</div>

		</div>
		<div id="mainbody" class="l-mainbody"
			style="width: 99.2%; margin: 0 auto; margin-top: 3px;">
			<div position="left" title="主要菜单" id="mainmenu"></div>
			<div position="center" id="framecenter">
				<div tabid="home" title="首页">
					<iframe frameborder="0" name="home" id="home" src="http://www.baidu.com/"></iframe>
				</div>
			</div>
		</div>
		<div style="height: 32px; line-height: 32px; text-align: center;">
			Copyright © 2011-2014 百货信息部
		</div>
		<div style="display: none"></div>
		
	</body>
</html>
