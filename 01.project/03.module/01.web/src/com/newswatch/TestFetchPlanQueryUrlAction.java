package com.newswatch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.NewsDao;
import com.newswatch.interfaces.SymbolInterface;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.TokenUtil;

/**
 * 测试抓取方案查询网址
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class TestFetchPlanQueryUrlAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = -2539151214845784591L;
	/**
	 * 网站
	 */
	private String website;
	/**
	 * 模糊网址
	 */
	private String likeUrl;
	/**
	 * 不包括模糊网址
	 */
	private String notLikeUrl;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "],likeUrl:[" + likeUrl + "],notLikeUrl:[" + notLikeUrl + "]");
        //判字段为空
        if(StringUtils.isBlank(website) || StringUtils.isBlank(likeUrl)){
            write(createResp(false, new ArrayList<String>(), "操作有误，请重试！"));
            return null;
        }
        write(createResp(true, NewsDao.queryNewsByWebsiteAndLikeAndNotLike(website, likeUrl, notLikeUrl), "测试抓取方案查询网址成功！"));
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param newsList
     * @param message
     * @return
     */
    public String createResp(boolean isSuccess, List<String> urlList, String message){
    	String resp = "{isSuccess:" + isSuccess + ",message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'";
    	if(isSuccess){
    		resp += ",urlList:[";
    		String temp = StringUtils.EMPTY;
    		for(String url : urlList){
    			if(StringUtils.isNotBlank(temp)){
    				temp += SymbolInterface.SYMBOL_COMMA;
    			}
    			temp += "'" + url + "'";
    		}
    		resp += temp + "]";
    	}
    	resp += "}";
    	return resp;
    }

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLikeUrl() {
		return likeUrl;
	}

	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}

	public String getNotLikeUrl() {
		return notLikeUrl;
	}

	public void setNotLikeUrl(String notLikeUrl) {
		this.notLikeUrl = notLikeUrl;
	}
}
