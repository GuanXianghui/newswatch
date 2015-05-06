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
        //网站名称
        String website = new String(StringUtils.trimToEmpty(request.getParameter("website")).getBytes("ISO-8859-1"), "UTF-8");
        //类型
        String typeStr = StringUtils.trimToEmpty(request.getParameter("type"));
        //类型
        int type;
        try {
        	type = Integer.parseInt(typeStr);
        } catch (Exception e) {
        	type = 0;
        }
        //过滤类型
        String filterTypeStr = StringUtils.trimToEmpty(request.getParameter("filterType"));
        //过滤类型
        int filterType;
        try {
        	filterType = Integer.parseInt(filterTypeStr);
        } catch (Exception e) {
        	filterType = 0;
        }
        //模糊过滤网址
        String filterUrlPart = StringUtils.trimToEmpty(request.getParameter("filterUrlPart"));
        //备注
        String remark = new String(StringUtils.trimToEmpty(request.getParameter("remark")).getBytes("ISO-8859-1"), "UTF-8");
        //当前页数
        String pageNumStr = StringUtils.trimToEmpty(request.getParameter("pageNum"));
        //当前页数
        int pageNum;
        try {
            pageNum = Integer.parseInt(pageNumStr);
        } catch (Exception e) {
            pageNum = 1;
        }
        //页面大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.URL_FILTER_PAGE_SIZE));
        /**
         * 查询结果数量
         */
        int count = UrlFilterDao.countUrlFilterByConditions(website, type, filterType, filterUrlPart, remark, pageNum);
        //是否为空
        boolean isEmpty = count == 0;
        //总页数
        int pageCount = (count - 1) / pageSize + 1;
        //删除最后一条，可能会少掉一页
        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        //根据页码查询
        List<UrlFilter> urlFilterList = UrlFilterDao.queryUrlFilterByConditions(website, type, filterType, filterUrlPart, remark, pageNum);
    %>
<html>
<head>
    <title>地址过滤配置</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/urlFilterConfig.js"></script>
    <!--日期控件-->
    <link type="text/css" rel="stylesheet" href="css/jquery-ui.css"/>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-ui.min.js"></script>
    <!-- 页面样式 -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="scripts/facebox.js"></script>
    <script type="text/javascript">
        //当前页数
        var pageNum = <%=pageNum%>;
        //初始化网站名称
        var initWebsite = "<%=website%>";
        //初始化类型
        var initType = <%=type%>;
        //初始化过滤类型
        var initFilterType = <%=filterType%>;
        //初始化模糊过滤网址
        var initFilterUrlPart = "<%=filterUrlPart%>";
        //初始化备注
        var initRemark = "<%=remark%>";
    </script>
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
            <h3>地址过滤配置</h3>
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
                        <table id="selectTable">
                            <tr>
                                <td style="width: 13%; text-align: right;">
                                	网站名称：
                                </td>
                                <td style="width: 20%; text-align: left;">
                                	<select class="text-input medium-input" id="website">
	                                	<option value="">全部</option>
	                                	<%
	                                		for(int i=0;i<websiteArray.length;i++){
	                            		%>
	                            			<option value="<%=websiteArray[i] %>"<%=StringUtils.equals(websiteArray[i], website)?" SELECTED":"" %>><%=websiteArray[i] %></option>
										<%	
	                                		}
	                                	%>
	                                </select>
                                </td>
                                <td style="width: 13%; text-align: right;">
                                	类型：
                                </td>
                                <td style="width: 20%; text-align: left;">
                                	<select class="text-input medium-input" id="type">
		                                <option value="0"<%=type==0?" SELECTED":"" %>>全部</option>
		                                <option value="1"<%=type==1?" SELECTED":"" %>>白名单</option>
		                                <option value="2"<%=type==2?" SELECTED":"" %>>黑名单</option>
	                                </select>
                                </td>
                                <td style="width: 13%; text-align: right;">
                               	 	过滤类型：
                                </td>
                                <td style="width: 20%; text-align: left;">
                               	 	<select class="text-input medium-input" id="filterType">
		                                <option value="0"<%=filterType==0?" SELECTED":"" %>>全部</option>
		                                <option value="1"<%=filterType==1?" SELECTED":"" %>>包含</option>
		                                <option value="2"<%=filterType==2?" SELECTED":"" %>>开始</option>
		                                <option value="3"<%=filterType==3?" SELECTED":"" %>>结束</option>
	                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 13%; text-align: right;">
                               		模糊过滤网址：
                                </td>
                                <td style="width: 20%; text-align: left;">
                               		<input type="text" class="text-input large-input" id="filterUrlPart" value="<%=filterUrlPart%>">
                                </td>
                                <td style="width: 13%; text-align: right;">
                               		备注：
                                </td>
                                <td style="width: 20%; text-align: left;">
                               		<input type="text" class="text-input large-input" id="remark" value="<%=remark%>">
                                </td>
                                <td colspan="2" style="width: 30%; text-align: center;">
                        			<input class="button" type="button" onclick="jump2page(1);" value="查询" />
                        			<input class="button" type="button" onclick="javascript:location.href='createUrlFilter.jsp';" value="新增" />
                                </td>
                            </tr>
                        </table>
                    </div>

                    <table id="cwr_table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>网站名称</th>
                            <th>类型</th>
                            <th>过滤类型</th>
                            <th>模糊过滤网址</th>
                            <th>备注</th>
                            <th>创建日期</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="9">
                                <div class="pagination">
                                    <a href="javascript: jump2page(1)" title="首页">&laquo; 首页</a>
                                    <%
                                        if(pageNum > 1){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum-1%>)" title="上一页">&laquo; 上一页</a>
                                    <%
                                        }
                                    %>
                                    <%
                                        //显示前2页，本页，后2页
                                        for(int i=pageNum-2;i<pageNum+3;i++){
                                            if(i >= 1 && i <= pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=i%>)" class="number<%=(i==pageNum)?" current":""%>" title="<%=i%>"><%=i%></a>
                                    <%
                                            }
                                        }
                                    %>
                                    <%
                                        if(pageNum < pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum+1%>)" title="下一页">下一页 &raquo;</a>
                                    <%
                                        }
                                    %>
                                    <a href="javascript: jump2page(<%=pageCount%>)" title="尾页">尾页 &raquo;</a>
                                </div>
                                <div class="clear"></div>
                            </td>
                        </tr>
                        </tfoot>
                        <tbody>
                        <%
                            //判是否为空
                            if (isEmpty) {
                        %>
                        <tr>
                            <td colspan="9">
                            	没找到内容
                            </td>
                        </tr>
                        <%
                        } else {//非空
                            for(int i=0;i<urlFilterList.size();i++)
                            { 
                        %>
                        <tr>
                            <td>
                                <%=urlFilterList.get(i).getId() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getWebsite() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getTypeDesc() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getFilterTypeDesc() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getFilterUrlPart() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getRemark() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getCreateDate() %>
                            </td>
                            <td>
                                <%=urlFilterList.get(i).getCreateTime() %>
                            </td>
                            <td>
                            	<input class="button" type="button" onclick="deleteUrlFilter(<%=urlFilterList.get(i).getId() %>);" value="删除" />
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
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