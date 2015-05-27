package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.FetchPlanDao;
import com.newswatch.entities.FetchPlan;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.DateUtils;
import com.newswatch.utils.TokenUtil;

/**
 * 设置仅展示
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class SaveFetchPlanAction extends BaseAction implements UserInterface{
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
	 * 模糊网址
	 */
	private String likeUrl;
	/**
	 * 不包括模糊网址
	 */
	private String notLikeUrl;
	/**
	 * 采集顺序
	 */
	private String orderIndex;
	/**
	 * 抬头前定位
	 */
	private String titlePre;
	/**
	 * 抬头后定位
	 */
	private String titlePost;
	/**
	 * 时间前定位
	 */
	private String timePre;
	/**
	 * 时间后定位
	 */
	private String timePost;
	/**
	 * 来源前定位
	 */
	private String sourcePre;
	/**
	 * 来源后定位
	 */
	private String sourcePost;
	/**
	 * 作者前定位
	 */
	private String authorPre;
	/**
	 * 作者后定位
	 */
	private String authorPost;
	/**
	 * 内容前定位
	 */
	private String contentPre;
	/**
	 * 内容后定位
	 */
	private String contentPost;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "],url:[" + url + "],likeUrl:[" + likeUrl + "],"
        		+ "notLikeUrl:[" + notLikeUrl + "],orderIndex:[" + orderIndex + "],titlePre:[" + titlePre + "],"
        		+ "titlePost:[" + titlePost + "],timePre:[" + timePre + "],timePost:[" + timePost + "],"
				+ "sourcePre:[" + sourcePre + "],sourcePost:[" + sourcePost + "],authorPre:[" + authorPre + "],"
				+ "authorPost:[" + authorPost + "],contentPre:[" + contentPre + "],contentPost:[" + contentPost + "]");
        
        //内容抓取方案实体
        FetchPlan fetchPlan = new FetchPlan(website, likeUrl, notLikeUrl, FetchPlan.STATE_NORMAL, orderIndex, titlePre, 
        		titlePost, timePre, timePost, sourcePre, sourcePost, authorPre, authorPost, contentPre, 
        		contentPost, 0, DateUtils.getCurrentDate(), DateUtils.getCurrentTime(), StringUtils.EMPTY, StringUtils.EMPTY);
        FetchPlanDao.insertFetchPlan(fetchPlan);
        createSuccessResp();
        writeJson();
        return null;
    }
    
    /**
     * 创建返回成功的结果
     * @return
     */
    public void createSuccessResp(){
    	json.put("isSuccess", true);
    	json.put("message", "保存抓取方案成功，请刷新网址！");
    	json.put("hasNewToken", true);
    	json.put("token", TokenUtil.createToken(request));
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

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getTitlePre() {
		return titlePre;
	}

	public void setTitlePre(String titlePre) {
		this.titlePre = titlePre;
	}

	public String getTitlePost() {
		return titlePost;
	}

	public void setTitlePost(String titlePost) {
		this.titlePost = titlePost;
	}

	public String getTimePre() {
		return timePre;
	}

	public void setTimePre(String timePre) {
		this.timePre = timePre;
	}

	public String getTimePost() {
		return timePost;
	}

	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}

	public String getSourcePre() {
		return sourcePre;
	}

	public void setSourcePre(String sourcePre) {
		this.sourcePre = sourcePre;
	}

	public String getSourcePost() {
		return sourcePost;
	}

	public void setSourcePost(String sourcePost) {
		this.sourcePost = sourcePost;
	}

	public String getAuthorPre() {
		return authorPre;
	}

	public void setAuthorPre(String authorPre) {
		this.authorPre = authorPre;
	}

	public String getAuthorPost() {
		return authorPost;
	}

	public void setAuthorPost(String authorPost) {
		this.authorPost = authorPost;
	}

	public String getContentPre() {
		return contentPre;
	}

	public void setContentPre(String contentPre) {
		this.contentPre = contentPre;
	}

	public String getContentPost() {
		return contentPost;
	}

	public void setContentPost(String contentPost) {
		this.contentPost = contentPost;
	}
}
