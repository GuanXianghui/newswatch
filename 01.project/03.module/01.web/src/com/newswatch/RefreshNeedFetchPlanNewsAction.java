package com.newswatch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.News;
import com.newswatch.interfaces.SymbolInterface;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.TokenUtil;

/**
 * 刷新需要内容抓取方案配置的新闻
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class RefreshNeedFetchPlanNewsAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = -2539151214845784591L;
	/**
	 * 网站
	 */
	private String website;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "]");
        //判字段为空
        if(StringUtils.isBlank(website)){
            write(createResp(false, new ArrayList<News>(), "请选择网站"));
            return null;
        }
        //查询需要配置抓取方案的新闻
        List<News> newsList = NewsDao.queryNeedFetchPlanNews(website);
        //返回message
        if(newsList.size() > 0){
        	message = "根据网站[" + website + "]查询库里所有采集地址成功的新闻，前50的新闻地址：";
        } else {
    		message = "根据网站[" + website + "]查询库里所有采集地址成功的新闻都已配置！";
        }
        write(createResp(true, newsList, message));
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param newsList
     * @param message
     * @return
     */
    public String createResp(boolean isSuccess, List<News> newsList, String message){
    	String resp = "{isSuccess:" + isSuccess + ",message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'";
    	if(isSuccess){
    		resp += ",newsList:[";
    		String temp = StringUtils.EMPTY;
    		for(News news : newsList){
    			if(StringUtils.isNotBlank(temp)){
    				temp += SymbolInterface.SYMBOL_COMMA;
    			}
    			temp += "'" + news.getUrl() + "'";
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
}
