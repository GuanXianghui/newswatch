package com.newswatch.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.NewsDao;
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
    
    /**
     * 根据网站查询库里所有域名
     * @param website
     * @return
     * @throws Exception
     */
    public static List<String> queryAllDomainByWebsite(String website) throws Exception {
		List<String> domainList = new ArrayList<String>();
		int pageNo = 1;
		List<String> urlList = NewsDao.queryAllUrlByWebsiteAndPage(website, pageNo++);
		while(urlList.size() > 0){
			for(String url : urlList){
				String httpPref = "http://";
				if(url.startsWith(httpPref)){
					url = url.substring(httpPref.length());
					int index = url.indexOf("/");
					if(index > 0){
						url = url.substring(0, index);
					}
					url = httpPref + url;
					if(!domainList.contains(url)){
						domainList.add(url);
					}
					continue;
				}
				String httpsPref = "https://";
				if(url.startsWith(httpsPref)){
					url = url.substring(httpsPref.length());
					int index = url.indexOf("/");
					if(index > 0){
						url = url.substring(0, index);
					}
					url = httpsPref + url;
					if(!domainList.contains(url)){
						domainList.add(url);
					}
					continue;
				}
				int index = url.indexOf("/");
				if(index > 0){
					url = url.substring(0, index);
				}
				if(!domainList.contains(url)){
					domainList.add(url);
				}
			}
			urlList = NewsDao.queryAllUrlByWebsiteAndPage(website, pageNo++);
		}
		Collections.sort(domainList);
		return domainList;
    }
}
