package com.newswatch.entities;

import com.newswatch.interfaces.UserInterface;

/**
 * 内容抓取方案实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class FetchPlan implements UserInterface {
	/**
	 * 状态:1 正常 2 删除
	 */
	public static final int STATE_NORMAL = 1;
	public static final int STATE_DELETE = 2;
	/**
	 * 采集顺序  不为空 1抬头，2时间，3来源，4作者，5内容，比如：12345，125
	 */
	public static final int ORDER_INDEX_TITLE = 1;
	public static final int ORDER_INDEX_TIME = 2;
	public static final int ORDER_INDEX_SOURCE = 3;
	public static final int ORDER_INDEX_AUTHOR = 4;
	public static final int ORDER_INDEX_CONTENT = 5;
	
    int id;
    String website;
    String likeUrl;
    String notLikeUrl;
    int state;
    String orderIndex;
    String titlePre;
    String titlePost;
    String timePre;
    String timePost;
    String sourcePre;
    String sourcePost;
    String authorPre;
    String authorPost;
    String contentPre;
    String contentPost;
    int times;
    String createDate;
    String createTime;
    String updateDate;
    String updateTime;

    /**
     * 新增时使用
     * @param website
     * @param likeUrl
     * @param notLikeUrl
     * @param state
     * @param orderIndex
     * @param titlePre
     * @param titlePost
     * @param timePre
     * @param timePost
     * @param sourcePre
     * @param sourcePost
     * @param authorPre
     * @param authorPost
     * @param contentPre
     * @param contentPost
     * @param times
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public FetchPlan(String website, String likeUrl, String notLikeUrl, int state, String orderIndex, String titlePre, String titlePost,
    		String timePre, String timePost, String sourcePre, String sourcePost, 
    		String authorPre, String authorPost, String contentPre, String contentPost, int times, 
    		String createDate, String createTime, String updateDate, String updateTime) {
    	this.website = website;
        this.likeUrl = likeUrl;
        this.notLikeUrl = notLikeUrl;
        this.state = state;
        this.orderIndex = orderIndex;
        this.titlePre = titlePre;
        this.titlePost = titlePost;
        this.timePre = timePre;
        this.timePost = timePost;
        this.sourcePre = sourcePre;
        this.sourcePost = sourcePost;
        this.authorPre = authorPre;
        this.authorPost = authorPost;
        this.contentPre = contentPre;
        this.contentPost = contentPost;
        this.times = times;
        this.createDate = createDate;
        this.createTime = createTime;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
    }

    /**
     * 查询时使用
     * @param id
     * @param website
     * @param likeUrl
     * @param notLikeUrl
     * @param state
     * @param orderIndex
     * @param titlePre
     * @param titlePost
     * @param timePre
     * @param timePost
     * @param sourcePre
     * @param sourcePost
     * @param authorPre
     * @param authorPost
     * @param contentPre
     * @param contentPost
     * @param times
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public FetchPlan(int id, String website, String likeUrl, String notLikeUrl, int state, String orderIndex, String titlePre, String titlePost,
    		String timePre, String timePost, String sourcePre, String sourcePost, 
    		String authorPre, String authorPost, String contentPre, String contentPost, int times, 
    		String createDate, String createTime, String updateDate, String updateTime) {
    	this.id = id;
    	this.website = website;
        this.likeUrl = likeUrl;
        this.notLikeUrl = notLikeUrl;
        this.state = state;
        this.orderIndex = orderIndex;
        this.titlePre = titlePre;
        this.titlePost = titlePost;
        this.timePre = timePre;
        this.timePost = timePost;
        this.sourcePre = sourcePre;
        this.sourcePost = sourcePost;
        this.authorPre = authorPre;
        this.authorPost = authorPost;
        this.contentPre = contentPre;
        this.contentPost = contentPost;
        this.times = times;
        this.createDate = createDate;
        this.createTime = createTime;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setLikeUrls(String likeUrl) {
		this.likeUrl = likeUrl;
	}

	public String getNotLikeUrl() {
		return notLikeUrl;
	}

	public void setNotLikeUrl(String notLikeUrl) {
		this.notLikeUrl = notLikeUrl;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
