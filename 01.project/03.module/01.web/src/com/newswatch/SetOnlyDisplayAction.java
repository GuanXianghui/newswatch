package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.News;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.TokenUtil;

/**
 * 设置仅展示
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SetOnlyDisplayAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = -2539151214845784591L;
	/**
	 * 网站
	 */
	private String website;
	/**
	 * 网址
	 */
	private String url;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "]");
        //判字段为空
        if(StringUtils.isBlank(website) || StringUtils.isBlank(url)){
            write(createResp(false, "操作有误，请重试！"));
            return null;
        }
        News news = NewsDao.getNewsByUrl(url);
        if(news == null || !StringUtils.equals(news.getWebsite(), website) || news.getState() != News.STATE_GRAB_URL_SUCCESS){
        	write(createResp(false, "操作有误，请重试！"));
            return null;
        }
        news.setState(News.STATE_ONLY_DISPLAY);
        NewsDao.updateNewsStateByUrl(news);
        write(createResp(true, "设置仅展示成功！"));
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param newsList
     * @param message
     * @return
     */
    public String createResp(boolean isSuccess, String message){
    	String resp = "{isSuccess:" + isSuccess + ",message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
    	return resp;
    }

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
