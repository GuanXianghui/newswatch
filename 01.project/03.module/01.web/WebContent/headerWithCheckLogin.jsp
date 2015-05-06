<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    //判登录
    if(!isLogin)
    {
        //域名链接
        response.sendRedirect(baseUrl + "login.jsp");
        return;
    }
    //外层
    String outLayer = StringUtils.EMPTY;
    //内层
    String inLayer = StringUtils.EMPTY;
%>