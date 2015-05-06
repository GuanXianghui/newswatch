<%@page import="com.newswatch.utils.IConstants"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.newswatch.entities.User" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="com.newswatch.utils.PropertyUtil" %>
<%@ page import="com.newswatch.utils.TokenUtil" %>
<%@ page import="com.newswatch.interfaces.BaseInterface" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //域名链接
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/newswatch/";
    //md5 key
    String md5Key = PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY);
    //token串
    String token = TokenUtil.createToken(request);
    //session中获取user
    User user = (User)request.getSession().getAttribute(BaseInterface.USER_KEY);
    //是否已经登录
    boolean isLogin = user != null;
    //消息
    String message = new String(StringUtils.trimToEmpty((String)request.getAttribute("message")).getBytes("ISO-8859-1"), "UTF-8");
    if(StringUtils.isBlank(message)){
        message = new String(StringUtils.trimToEmpty(request.getParameter("message")).getBytes("ISO-8859-1"), "UTF-8");
    }
    
    //跳转地址
    String jumpUrl = StringUtils.trimToEmpty(request.getParameter("jumpUrl"));
    //所有网站名称
    //String[] websiteArray = PropertyUtil.getInstance().getProperty(BaseInterface.WEBSITES).split(IConstants.COMMA);
    String[] websiteArray = "人民网,网易".split(IConstants.COMMA);
%>
<script type="text/javascript">
    //域名链接
    var baseUrl = "<%=baseUrl%>";
    //md5 key
    var md5Key = "<%=md5Key%>";
    //token穿
    var token = "<%=token%>";
    //是否已经登录
    var isLogin = <%=isLogin%>;
    //弹出消息框
    var message = '<%=message%>';
</script>
<!-- 图标 -->
<link rel="shortcut icon" type="image/x-icon" href="<%=baseUrl %>/images/newswatch_logo.png" />