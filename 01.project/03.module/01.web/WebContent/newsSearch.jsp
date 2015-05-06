<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithCheckLogin.jsp" %>
    <%
        //外层
        outLayer = "新闻模块";
        //内层
        inLayer = "新闻检索";
        //开始日期
        String startDate = StringUtils.trimToEmpty(request.getParameter("startDate"));
        //结束日期
        String endDate = StringUtils.trimToEmpty(request.getParameter("endDate"));
        //当前页数
        String pageStr = StringUtils.trimToEmpty(request.getParameter("pageNum"));
        //当前页数
        int pageNum;
        try {
            pageNum = Integer.parseInt(pageStr);
        } catch (Exception e) {
            pageNum = 1;
        }
        //页面大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.NEWS_PAGE_SIZE));
        /**
         * 查询结果数量
         */
        int count = 1000;
        //是否为空
        boolean isEmpty = count == 0;
        //总页数
        int pageCount = (count - 1) / pageSize + 1;
        //删除最后一条，可能会少掉一页
        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        //根据页码查询
        //List<QrCode> qrCodes = QrCodeDao.queryQrCodesByUuidAndStateAndPayNum(startId, endId, startDate, endDate, uuid, state, pageNum);
    %>
<html>
<head>
    <title>新闻检索</title>
    <script type="text/javascript" src="<%=baseUrl%>scripts/jquery-min.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/base.js"></script>
    <script type="text/javascript" src="<%=baseUrl%>scripts/newsSearch.js"></script>
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
        //初始化起始日期
        var initStartDate = "<%=startDate%>";
        //初始化终止日期
        var initEndDate = "<%=endDate%>";
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
    <div id="message_id" class="notification information png_bg" style="display: none;">
        <a href="#" class="close">
            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
        </a>

        <div id="message_id_content"> 提示信息！</div>
    </div>

    <div class="content-box">
        <div class="content-box-header">
            <h3>新闻检索</h3>
        </div>
        <div class="content-box-content">
            <div class="tab-content default-tab">
                <form>
                    <div align="center">
                        <table id="selectTable">
                            <tr>
                                <td>
                                 开始日期：<input type="text" class="text-input small-input" id="startDate" value="<%=startDate%>">
                                </td>
                                <td>
                                结束日期：<input type="text" class="text-input small-input" id="endDate" value="<%=endDate%>">
                                </td>
                                <td>
                        <input class="button" type="button" onclick="jump2page(1);" value="查询" />
                        <input class="button" type="button" onclick="alert('下载');" value="下载" />
                                </td>
                            </tr>
                        </table>
                    </div>


                    <table id="cwr_table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>标题</th>
                            <th>媒体名称</th>
                            <th>栏目</th>
                            <th>更新时间</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="5">
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
                            <td colspan="5">
                                没找到内容
                            </td>
                        </tr>
                        <%
                        } else {//非空
                            /* for(int i=0;i<qrCodes.size();i++)
                            { */
                        %>
                        <tr>
                            <td>
                                XXX1
                            </td>
                            <td>
                                XXX2
                            </td>
                            <td>
                                XXX3
                            </td>
                            <td>
                                XXX4
                            </td>
                            <td>
                                XXX5
                            </td>
                        </tr>
                        <%
                                /* } */
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