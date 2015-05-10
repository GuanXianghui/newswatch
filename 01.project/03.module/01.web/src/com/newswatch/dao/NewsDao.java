package com.newswatch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newswatch.entities.News;

/**
 * 新闻实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class NewsDao {

    /**
     * 判地址是否存在
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static boolean isUrlExist(String url) throws Exception {
    	News news = getNewsByUrl(url);
        return news != null;
    }

    /**
     * 根据地址查新闻
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static News getNewsByUrl(String url) throws Exception {
        String sql = "SELECT id,website,url,state,times,title,content,create_date,create_time,update_date,update_time FROM news " +
                "WHERE url='" + url + "'";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String website = rs.getString("website");
                int state = rs.getInt("state");
                int times = rs.getInt("times");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, content, createDate, createTime, updateDate, updateTime);
                return news;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增新闻
     *
     * @param news
     * @throws Exception
     */
    public static void insertNews(News news) throws Exception {
        String sql = "insert into news" +
                "(id,website,url,state,times,title,content,create_date,create_time,update_date,update_time)" +
                "values" +
                "(null,'" + news.getWebsite() + "','" + news.getUrl() + "'," + news.getState() + "," +
                news.getTimes() + ",'" + news.getTitle() + "','" + news.getContent() + "','" + 
                news.getCreateDate() + "','" + news.getCreateTime() + "','" + news.getUpdateDate() + "','" + 
                news.getUpdateTime() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 更新新闻
     *
     * @param news
     * @throws Exception
     */
    public static void updateNewsByUrl(News news) throws Exception {
        String sql = "update news set state=" + news.getState() + ", times=" + news.getTimes() + ", title='" + 
	    news.getTitle() + "', content='" + news.getContent() + "', update_date='" + news.getUpdateDate() + 
	    "', update_time='" + news.getUpdateTime() + "' where url='" + news.getUrl() + "'";
        DB.executeUpdate(sql);
    }

    /**
     * 根据地址查新闻
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static News getNextGrabNews() throws Exception {
        String sql = "SELECT id,website,url,state,times,title,content,create_date,create_time,update_date,update_time FROM news " +
                "WHERE state in (1,3) ORDER BY state,id limit 1";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String website = rs.getString("website");
                String url = rs.getString("url");
                int state = rs.getInt("state");
                int times = rs.getInt("times");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, content, createDate, createTime, updateDate, updateTime);
                return news;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 查询所有地址
     *
     * @return
     * @throws Exception
     */
    public static List<String> queryAllUrl() throws Exception {
    	List<String> urlList = new ArrayList<String>();
        String sql = "SELECT url FROM news";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String url = rs.getString("url");
                urlList.add(url);
            }
            return urlList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 查询最近一个抓取URL返回的新闻
     *
     * @return
     * @throws Exception
     */
    public static News getLastGrabUrlReturnNews() throws Exception {
        String sql = "SELECT id,website,url,state,times,title,content,create_date,create_time,update_date,update_time"
        		+ " FROM news where state > 1 order by id desc limit 1";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
            	int id = rs.getInt("id");
                String website = rs.getString("website");
                String url = rs.getString("url");
                int state = rs.getInt("state");
                int times = rs.getInt("times");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, content, createDate, createTime, updateDate, updateTime);
                return news;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据地址模糊查新闻
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static int countNewsByLikeUrl(String url) throws Exception {
        String sql = "SELECT count(1) count FROM news " +
                "WHERE url like '" + url + "%'";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int count = rs.getInt("count");
                return count;
            }
            return 0;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }
}