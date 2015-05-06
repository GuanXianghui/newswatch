package com.newswatch.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码过滤器
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:12
 */
public class EncodingFilter implements Filter {
    /**
     * 文件配置
     */
    private FilterConfig filterConfig = null;

    /**
     * 构造函数
     *
     * @param filterConfig
     * @throws javax.servlet.ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * 过滤
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //设置请求字符编码
        request.setCharacterEncoding("UTF-8");

        //执行下一个过滤器
        filterChain.doFilter(req, res);
    }

    /**
     * 销毁
     */
    public void destroy() {
        this.filterConfig = null;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
