package com.newswatch.utils;

import com.newswatch.interfaces.BaseInterface;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * token工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 14:02
 */
public class TokenUtil implements BaseInterface {
    /**
     * 得到sessionTokenList
     *
     * @param request
     * @return
     */
    public static List getSessionTokenList(HttpServletRequest request) {
        List sessionTokenList = (List) request.getSession().getAttribute(SESSION_TOKEN_LIST);
        if (sessionTokenList == null) {
            sessionTokenList = new ArrayList();
            request.getSession().setAttribute(SESSION_TOKEN_LIST, sessionTokenList);
        }
        return sessionTokenList;
    }

    /**
     * 创建token串
     *
     * @param request
     * @return
     */
    public static String createToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        List sessionTokenList = getSessionTokenList(request);
        sessionTokenList.add(uuid);
        return uuid;
    }

    /**
     * 校验token串
     *
     * @param request
     * @param token
     * @return
     */
    public static boolean checkToken(HttpServletRequest request, String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        List sessionTokenList = getSessionTokenList(request);
        if (sessionTokenList.contains(token)) {
            sessionTokenList.remove(token);
            return true;
        }
        return false;
    }
}