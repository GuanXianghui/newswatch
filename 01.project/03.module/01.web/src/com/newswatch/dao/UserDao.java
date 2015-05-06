package com.newswatch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.newswatch.entities.User;

/**
 * 用户实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class UserDao {

    /**
     * 根据id查用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    public static User getUserById(int id) throws Exception {
        String sql = "SELECT name,user_type,password,create_date,create_time FROM user " +
                "WHERE id=" + id;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String name = rs.getString("name");
                int userType = rs.getInt("user_type");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                User user = new User(id, name, userType, password, createDate, createTime);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据姓名查用户
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static User getUserByName(String name) throws Exception {
        String sql = "SELECT id,user_type,password,create_date,create_time FROM user " +
                "WHERE name='" + name + "'";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                int userType = rs.getInt("user_type");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                User user = new User(id, name, userType, password, createDate, createTime);
                return user;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 判该名字是否已存在
     *
     * @param name
     * @return
     * @throws Exception
     */
    public static boolean isNameExist(String name) throws Exception {
        User user = getUserByName(name);
        return user != null;
    }

    /**
     * 新增用户
     *
     * @param user
     * @throws Exception
     */
    public static void insertUser(User user) throws Exception {
        String sql = "insert into user" +
                "(id,name,user_type,password,create_date,create_time)" +
                "values" +
                "(null,'" + user.getName() + "'," + user.getUserType() + ",'" + user.getPassword() + "','" +
                user.getCreateDate() + "','" + user.getCreateTime() + "')";
        DB.executeUpdate(sql);

        //根据姓名和用户类型查用户
        user = getUserByName(user.getName());
    }

    /**
     * 更新用户密码
     *
     * @param user
     * @throws Exception
     */
    public static void updateUserPassword(User user) throws Exception {
        String sql = "update user set password='" + user.getPassword() + "' where id=" + user.getId();
        DB.executeUpdate(sql);
    }
}