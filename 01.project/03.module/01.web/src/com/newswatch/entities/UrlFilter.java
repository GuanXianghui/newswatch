package com.newswatch.entities;

import com.newswatch.interfaces.UserInterface;

/**
 * 地址过滤实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class UrlFilter implements UserInterface {
	/**
	 * 类型:1 白名单 2 黑名单
	 */
	public static final int TYPE_WHITE = 1;
	public static final int TYPE_BLACK = 2;
	/**
	 * 过滤类型:1 包含 2 开始 3 结束
	 */
	public static final int FILTER_TYPE_INDEX_OF = 1;
	public static final int FILTER_TYPE_START_WITH = 2;
	public static final int FILTER_TYPE_END_WITH = 3;
	/**
	 * 状态:1 正常 2 删除
	 */
	public static final int STATE_NORMAL = 1;
	public static final int STATE_DELETE = 2;
	
    int id;
    String website;
    int type;
    int filterType;
    String filterUrlPart;
    String remark;
    int state;
    String createDate;
    String createTime;
    String updateDate;
    String updateTime;

    /**
     * 新增时使用
     * @param website
     * @param type
     * @param filterType
     * @param filterUrlPart
     * @param remark
     * @param state
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public UrlFilter(String website, int type, int filterType, String filterUrlPart, String remark, int state, 
    		String createDate, String createTime, String updateDate, String updateTime) {
        this.website = website;
        this.type = type;
        this.filterType = filterType;
        this.filterUrlPart = filterUrlPart;
        this.remark = remark;
        this.state = state;
        this.createDate = createDate;
        this.createTime = createTime;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
    }

    /**
     * 查询时使用
     * @param id
     * @param website
     * @param type
     * @param filterType
     * @param filterUrlPart
     * @param remark
     * @param state
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public UrlFilter(int id, String website, int type, int filterType, String filterUrlPart, String remark, int state, 
    		String createDate, String createTime, String updateDate, String updateTime) {
        this.id = id;
        this.website = website;
        this.type = type;
        this.filterType = filterType;
        this.filterUrlPart = filterUrlPart;
        this.remark = remark;
        this.state = state;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getTypeDesc(){
		if(TYPE_WHITE == this.type){
			return "白名单";
		}
		if(TYPE_BLACK == this.type){
			return "黑名单";
		}
		return "解析异常";
	}

	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	
	public String getFilterTypeDesc(){
		if(FILTER_TYPE_INDEX_OF == this.filterType){
			return "包含";
		}
		if(FILTER_TYPE_START_WITH == this.filterType){
			return "开始";
		}
		if(FILTER_TYPE_END_WITH == this.filterType){
			return "结束";
		}
		return "解析异常";
	}

	public String getFilterUrlPart() {
		return filterUrlPart;
	}

	public void setFilterUrlPart(String filterUrlPart) {
		this.filterUrlPart = filterUrlPart;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
