<%@page import="com.newswatch.entities.UrlFilter"%>
<%@page import="com.newswatch.dao.UrlFilterDao"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "配置模块";
        //内层
        inLayer = "地址过滤配置";
    %>
<html>
<head>
    <title>新增地址过滤</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/createUrlFilter.js"></script>
    <!--日期控件-->
    <link type="text/css" rel="stylesheet" href="css/jquery-ui.css"/>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-ui.min.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="scripts/facebox.js"></script>
    <style type="text/css">
        #selectTable td {
            text-align: center;
        }
        #selectTable th {
            text-align: center;
        }
        #cwr_table td {
            text-align: center;
        }
        #cwr_table th {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="body-wrapper">
<div id="sidebar">
    <div id="sidebar-wrapper">
        <h1 id="sidebar-title"><a href="#">XX网</a></h1>
        <div align="center">
            <img id="logo" src="images/newswatch_logo.png" width="50" alt="Simpla Admin logo"/>
        </div>
        <div id="profile-links">
            Hello, [<%=user.getName()%>],XX网欢迎您！
            <br/>
            <br/>
            <a href="javascript: logOut()" title="Sign Out">退出</a>
        </div>
        <%@ include file="layers.jsp" %>
    </div>
</div>
<div id="main-content">
    <div class="content-box">
        <div class="content-box-header">
            <h3>新增地址过滤</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
			    <div id="message_id" class="notification information png_bg" style="display: none;">
			        <a href="#" class="close">
			            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
			        </a>
			
			        <div id="message_id_content"> 提示信息！</div>
			    </div>
                <form>
	                <fieldset>
			            <p>
			                <span>网站名称：</span>&nbsp;&nbsp;
			                <select class="text-input small-input" id="website" name="website">
                               	<%
                               		for(int i=0;i<websiteArray.length;i++){
                           		%>
                           			<option value="<%=websiteArray[i] %>"><%=websiteArray[i] %></option>
								<%	
                               		}
                               	%>
                            </select>
			                <br>
			                <span>类型：</span>&nbsp;&nbsp;
			                <select class="text-input small-input" id="type" name="type">
                                <option value="1">白名单</option>
                                <option value="2">黑名单</option>
			                </select>
			                <br>
			                <span>过滤类型：</span>&nbsp;&nbsp;
			                <select class="text-input small-input" id="filterType" name="filterType">
                                <option value="1">包含</option>
                                <option value="2">开始</option>
                                <option value="3">结束</option>
			                </select>
			                <br>
			                <span>模糊过滤网址：</span>&nbsp;&nbsp;
			                <input class="text-input small-input" type="text" id="filterUrlPart" name="filterUrlPart"/>
			                <br>
			                <span>备注：</span>&nbsp;&nbsp;
			                <input class="text-input small-input" type="text" id="remark" name="remark"/>
			                <br>
			                <input class="button" type="button" onclick="createUrlFilter();" value="新建" />
			                <input class="button" type="button" onclick="location.href='urlFilterConfig.jsp';" value="返回" />
			            </p>
			        </fieldset>
	       			<div class="clear"></div>
                </form>
            </div>
        </div>
    </div>

    <div class="clear"></div>
    <div id="footer">
        <small>
            &#169; Copyright 2015 newswatch
        </small>
    </div>
</div>
</div>
</body>
</html>