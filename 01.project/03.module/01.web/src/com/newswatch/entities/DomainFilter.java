package com.newswatch.entities;

import com.newswatch.interfaces.UserInterface;

/**
 * 域名过滤实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class DomainFilter implements UserInterface {
	/**
	 * 类型:1 白名单 2 黑名单
	 */
	public static final int TYPE_WHITE = 1;
	public static final int TYPE_BLACK = 2;
	/**
	 * 状态:1 正常 2 删除
	 */
	public static final int STATE_NORMAL = 1;
	public static final int STATE_DELETE = 2;
	
    int id;
    String website;
    int type;
    String domain;
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
     * @param domain
     * @param remark
     * @param state
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public DomainFilter(String website, int type, String domain, String remark, int state, 
    		String createDate, String createTime, String updateDate, String updateTime) {
        this.website = website;
        this.type = type;
        this.domain = domain;
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
     * @param domain
     * @param remark
     * @param state
     * @param createDate
     * @param createTime
     * @param updateDate
     * @param updateTime
     */
    public DomainFilter(int id, String website, int type, String domain, String remark, int state, 
    		String createDate, String createTime, String updateDate, String updateTime) {
        this.id = id;
        this.website = website;
        this.type = type;
        this.domain = domain;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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
