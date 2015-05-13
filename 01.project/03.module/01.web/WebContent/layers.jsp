<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="main-nav">
    <li><a href="#" class="nav-top-item<%=outLayer.equals("新闻模块")?" current":""%>"> 新闻模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>newsSearch.jsp"<%=inLayer.equals("新闻检索")?" class=\"current\"":""%>>新闻检索</a></li>
        </ul>
    </li>
    <li><a href="#" class="nav-top-item<%=outLayer.equals("配置模块")?" current":""%>"> 配置模块 </a>
        <ul>
            <li><a href="<%=baseUrl%>urlFilterConfig.jsp"<%=inLayer.equals("地址过滤配置")?" class=\"current\"":""%>>地址过滤配置</a></li>
        	<li><a href="<%=baseUrl%>domainFilterConfig.jsp"<%=inLayer.equals("域名过滤配置")?" class=\"current\"":""%>>域名过滤配置</a></li>
        </ul>
    </li>
</ul>