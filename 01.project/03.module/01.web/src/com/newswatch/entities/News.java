package com.newswatch.entities;

import com.newswatch.interfaces.UserInterface;

/**
 * 新闻实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class News implements UserInterface {
	/**
	 * 状态:1 初始 2 采集地址成功 3采集地址失败 4 采集内容成功 5 采集内容失败 6 仅展示
	 */
	public static final int STATE_INIT = 1;
	public static final int STATE_GRAB_URL_SUCCESS = 2;
	public static final int STATE_GRAB_URL_FAIL = 3;
	public static final int STATE_GRAB_CONTENT_SUCCESS = 4;
	public static final int STATE_GRAB_CONTENT_FAIL = 5;
	public static final int STATE_ONLY_DISPLAY = 6;
	
    int id;
    String website;
    String url;
    int state;
    int times;
    String title;
    String content;
    String createDate;
    String createTime;
    String updateDate;
    String updateTime;

    /**
     * 新增时使用
     * @param website
     * @param url
     * @param state
     * @param times
     * @param title
     * @param content
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public News(String website, String url, int state, int times, String title, String content, 
    		String createDate, String createTime, String updateDate, String updateTime) {
    	this.website = website;
        this.url = url;
        this.state = state;
        this.times = times;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.createTime = createTime;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
    }

    /**
     * 查询时使用
     * @param website
     * @param url
     * @param state
     * @param times
     * @param title
     * @param content
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public News(int id, String website, String url, int state, int times, String title, String content, 
    		String createDate, String createTime, String updateDate, String updateTime) {
    	this.id = id;
    	this.website = website;
        this.url = url;
        this.state = state;
        this.times = times;
        this.title = title;
        this.content = content;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
