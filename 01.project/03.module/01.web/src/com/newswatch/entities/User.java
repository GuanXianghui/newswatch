package com.newswatch.entities;

import com.newswatch.interfaces.UserInterface;

/**
 * 用户实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 11:18
 */
public class User implements UserInterface {
    int id;
    String name;
    int userType;
    String password;
    String createDate;
    String createTime;

    /**
     * 新增时使用
     * @param name
     * @param userType
     * @param password
     * @param createDate
     * @param createTime
     */
    public User(String name, int userType, String password, String createDate, String createTime) {
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    /**
     * 查询时使用
     * @param id
     * @param name
     * @param userType
     * @param password
     * @param createDate
     * @param createTime
     */
    public User(int id, String name, int userType, String password, String createDate, String createTime) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
