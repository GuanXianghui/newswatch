package com.newswatch.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.newswatch.interfaces.BaseInterface;
import com.newswatch.interfaces.SymbolInterface;

/**
 * 基础工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-30 12:09
 */
public class BaseUtil implements BaseInterface, SymbolInterface {
    /**
     * 判图片验证码是否正确
     *
     * @param request
     * @param securityCode
     */
    public static boolean checkSecurityCode(HttpServletRequest request, String securityCode) throws Exception {
        if(!StringUtils.equals((String)request.getSession().getAttribute(BaseInterface.SESSION_SECURITY_CODE), securityCode)) {
            return false;
        }
        return true;
    }

    /**
     * 判登录
     *
     * @param request
     */
    public static boolean isLogin(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute(BaseInterface.USER_KEY) == null) {
            return false;
        }
        return true;
    }
}
