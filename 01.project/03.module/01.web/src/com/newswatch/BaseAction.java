package com.newswatch;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.newswatch.entities.User;
import com.newswatch.interfaces.BaseInterface;
import com.newswatch.utils.BaseUtil;
import com.newswatch.utils.DateUtil;
import com.newswatch.utils.IPAddressUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 12:44
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;

	/**
     * 日志处理器
     */
    Logger logger = Logger.getLogger(BaseAction.class);

    /**
     * 图片验证码
     */
    String securityCode;

    /**
     * 当前时间
     */
    String date;
    String time;
    String defaultDateTime;

    /**
     * token串 一定会有值，否则过不了StrutsFilter
     */
    String token;

    /**
     * 消息
     */
    String message;

    /**
     * request，response
     */
    HttpServletRequest request;
    HttpServletResponse response;
    
    /**
     * 返回json
     */
    Map<String, Object> json = new HashMap<String, Object>(); 

    /**
     * 构造函数
     */
    public BaseAction() {
        this.date = DateUtil.getNowDate();
        this.time = DateUtil.getNowTime();
        this.defaultDateTime = DateUtil.getDefaultDateTime(new Date());
    }

    /**
     * 获取ip
     * @return
     */
    public String getIp() {
        return IPAddressUtil.getIPAddress(request);
    }

    /**
     * 获取session
     * @return
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 获取application
     * @return
     */
    public ServletContext getApplication() {
        return request.getSession().getServletContext();
    }

    /**
     * 获取User
     * @return
     */
    public User getUser() {
        return (User) getSession().getAttribute(BaseInterface.USER_KEY);
    }

    /**
     * 刷新session缓存中的user
     * @param user
     */
    public void refreshSessionUser(User user) {
        getSession().setAttribute(BaseInterface.USER_KEY, user);
    }

    /**
     * ajax写出结果
     * @param resp
     */
    public void write(String resp) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(resp);
        writer.flush();
        writer.close();
    }
    
    /**
     * ajax写出json
     * @throws Exception
     */
    public void writeJson() throws Exception {
    	write(JSONObject.fromObject(json).toString());
    }

    /**
     * 判图片验证码是否正确
     * @return
     * @throws Exception
     */
    public boolean checkSecurityCode() throws Exception {
        return BaseUtil.checkSecurityCode(request, securityCode);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

	public Map<String, Object> getJson() {
		return json;
	}

	public void setJson(Map<String, Object> json) {
		this.json = json;
	}
}