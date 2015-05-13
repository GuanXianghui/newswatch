package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.UserDao;
import com.newswatch.entities.User;
import com.newswatch.interfaces.UserInterface;

/**
 * 管理员登录
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class LoginAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = 1L;
	private String name;
    private String password;
    private String jumpUrl;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("name:[" + name + "],password:[" + password + "],jumpUrl:[" + jumpUrl + "]");
        //判字段为空
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password)){
            message = "请输入必输项";
            return ERROR;
        }

        //判图片验证码是否正确
        if(!checkSecurityCode()){
            message = "图片验证码输入错误";
            return ERROR;
        }

        //判名字存在
        if(!UserDao.isNameExist(name)){
            message = "该用户名不存在";
            return ERROR;
        }

        User user = UserDao.getUserByName(name);
        //判密码是否一致
        if(!StringUtils.equals(password, user.getPassword())){
            message = "你的密码输入有误";
            return ERROR;
        }
        //普通用户 登录成功
        //刷新session缓存中的user
        refreshSessionUser(user);
        message = "登录成功！";
        if(StringUtils.isNotBlank(jumpUrl)){
            response.sendRedirect(jumpUrl);
        } else {
        	response.sendRedirect("newsSearch.jsp");
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
