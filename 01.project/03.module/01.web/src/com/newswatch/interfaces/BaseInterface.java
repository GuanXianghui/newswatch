package com.newswatch.interfaces;

/**
 * 基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface BaseInterface {
    /**
     * mysql数据库链接
     */
    public static final String MYSQL_CONNECTION = "mysql_connection";
    /**
     * md5 key
     */
    public static final String MD5_KEY = "md5_key";
    /**
     * session缓存中的token集合
     */
    public static final String SESSION_TOKEN_LIST = "session_token_list";
    /**
     * TOKEN键
     */
    public static final String TOKEN_KEY = "token";
    /**
     * 用户键
     */
    public static final String USER_KEY = "user";
    /**
     * 是否管理员用户
     */
    public static final String IS_ADMIN_USER = "isAdminUser";
    /**
     * 管理员用户名
     */
    public static final String ADMIN_USER_NAME = "adminUserName";
    /**
     * 管理员用户
     */
    public static final String ADMIN_USER = "admin_user";
    /**
     * 新闻页面大小
     */
    public static final String NEWS_PAGE_SIZE = "news_page_size";
    /**
     * 地址过滤页面大小
     */
    public static final String URL_FILTER_PAGE_SIZE = "url_filter_page_size";
    /**
     * 图片验证码
     */
    public static final String SESSION_SECURITY_CODE = "session_security_code";
    /**
     * 对外通讯 邮件服务地址
     */
    public static final String EMAIL_HOST = "email_host";
    /**
     * 对外通讯 邮件服务端口
     */
    public static final String EMAIL_PORT = "email_port";
    /**
     * 对外通讯 邮件名
     */
    public static final String EMAIL_NAME = "email_name";
    /**
     * 对外通讯 邮件密码
     */
    public static final String EMAIL_PASSWORD = "email_password";
    /**
     * 主入口地址
     */
    public static final String INDEX_URLS = "index_urls";
    /**
     * 主入口地址
     */
    public static final String WEBSITES = "websites";
}
