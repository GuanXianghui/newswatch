<%@page import="com.newswatch.entities.DomainFilter"%>
<%@page import="com.newswatch.dao.DomainFilterDao"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "配置模块";
        //内层
        inLayer = "抓取方案配置";
    %>
<html>
<head>
    <title>域名过滤配置</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/fetchPlanConfig.js"></script>
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
        #selectTable0 td {
            text-align: center;
        }
        #selectTable0 th {
            text-align: center;
        }
        #cwr_table0 td {
            text-align: center;
        }
        #cwr_table0 th {
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
            <h3>抓取方案配置</h3>
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
                    <div align="center">
                        <table id="selectTable0">
                            <tr>
                                <td style="width: 13%; text-align: right;">
                                	网站名称：
                                </td>
                                <td style="width: 20%; text-align: left;">
                                	<select class="text-input medium-input" id="website0">
	                                	<%
	                                		for(int i=0;i<websiteArray.length;i++){
	                            		%>
	                            			<option value="<%=websiteArray[i] %>"><%=websiteArray[i] %></option>
										<%	
	                                		}
	                                	%>
	                                </select>
                                </td>
                                <td style="width: 70%; text-align: left;">
                        			<input class="button" type="button" onclick="refreshNeedFetchPlanNews();" value="刷新" />
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>

                    <table id="cwr_table0" style="display: none;">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>网站名称</th>
                            <th>网址</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="cwr_tbody0">
                        <tr>
                            <td>
                                	人民网
                            </td>
                            <td>
                                people.com.cn
                            </td>
                            <td>
                            	<input class="button" type="button" onclick="xxx('');" value="仅展示" />
                            	<input class="button" type="button" onclick="xxx('');" value="配置抓取方案" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="fetch_plan_config" style="display: none;">
				<a href="#fetch_plan_config" rel="facebox" id="fetch_plan_config_a"></a>
				<form>
					<table style="width: 800px;">
						<thead>
							<tr>
								<th colspan="2" 
									style="text-align: center; font-size: 18px; font-weight: bold;"
									>抓取方案配置<br>
									注意1：采集顺序：1抬头，2时间，3来源，4作者，5内容，比如13245<br>
									注意2：模糊地址采用mysql的_和%模糊占位，比如http://119.people.com.cn/GB/%.html</th>
							</tr>
						</thead>
						<tbody>
							<tr style="line-height: 36px;"><td>网站</td><td id="website"></td></tr>
							<tr style="line-height: 36px;"><td>地址</td><td id="url"></td></tr>
							<tr>
								<td>模糊地址</td>
								<td>
									<input class="text-input large-input" type="text" id="like_url"/>
								</td>
							</tr>
							<tr>
								<td>不包括模糊地址</td>
								<td>
									<input class="text-input large-input" type="text" id="not_like_url"/>
								</td>
							</tr>
							<tr><td>采集顺序</td><td><input class="text-input large-input" type="text" id="order_index"/></td></tr>
							<tr><td>抬头前定位</td><td><input class="text-input large-input" type="text" id="title_pre"/></td></tr>
							<tr><td>抬头后定位</td><td><input class="text-input large-input" type="text" id="title_post"/></td></tr>
							<tr><td>时间前定位</td><td><input class="text-input large-input" type="text" id="time_pre"/></td></tr>
							<tr><td>时间后定位</td><td><input class="text-input large-input" type="text" id="time_post"/></td></tr>
							<tr><td>来源前定位</td><td><input class="text-input large-input" type="text" id="source_pre"/></td></tr>
							<tr><td>来源后定位</td><td><input class="text-input large-input" type="text" id="source_post"/></td></tr>
							<tr><td>作者前定位</td><td><input class="text-input large-input" type="text" id="author_pre"/></td></tr>
							<tr><td>作者后定位</td><td><input class="text-input large-input" type="text" id="author_post"/></td></tr>
							<tr><td>内容前定位</td><td><input class="text-input large-input" type="text" id="content_pre"/></td></tr>
							<tr><td>内容后定位</td><td><input class="text-input large-input" type="text" id="content_post"/></td></tr>
							<tr><td colspan="2" ><input class="button" type="button" onclick="testFetchPlanConfig();" value="测试" /></td></tr>
						</tbody>
					</table>
				</form>
			</div>
			<div id="test_fetch_plan" style="display: none;">
				<a href="#test_fetch_plan" rel="facebox" id="test_fetch_plan_a"></a>
				<form style="text-align: center;">
					<table style="width: 500px;">
						<tr>
							<td>选择网址</td>
							<td>
							<select class="text-input large-input" name="testFetchPlanUrl" id="testFetchPlanUrl">
							</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input class="button" type="button" onclick="testFetchPlan();" value="抓取"/>
								<input class="button" type="button" onclick="backFetchPlanConfig();" value="返回"/>
								<input class="button" type="button" onclick="saveFetchPlan();" value="保存方案"/>
							</td>
						</tr>
						<tr><td>抬头</td><td><textarea class="text-input large-input" id="test_title"></textarea></td></tr>
						<tr><td>日期</td><td><textarea class="text-input large-input" id="test_date"></textarea></td></tr>
						<tr><td>时间</td><td><textarea class="text-input large-input" id="test_time"></textarea></td></tr>
						<tr><td>来源</td><td><textarea class="text-input large-input" id="test_source"></textarea></td></tr>
						<tr><td>作者</td><td><textarea class="text-input large-input" id="test_author"></textarea></td></tr>
						<tr><td>内容</td><td><textarea class="text-input large-input" id="test_content"></textarea></td></tr>
					</table>
				</form>
			</div>
			<div id="set_only_display" style="display: none;">
				<a href="#set_only_display" rel="facebox" id="set_only_display_a"></a>
				<form style="text-align: center;">
					<table style="width: 500px;">
						<tr>
							<td>网站</td>
							<td>
								<span id="set_only_display_website"></span>
							</td>
						</tr>
						<tr>
							<td>模糊网址</td>
							<td>
								<input class="text-input large-input" type="text" id="set_only_display_like_url"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<input class="button" type="button" onclick="setOnlyDisplay();" value="设置仅展示"/>
							</td>
						</tr>
					</table>
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