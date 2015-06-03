package com.newswatch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.entities.News;
import com.newswatch.entities.UrlFilter;
import com.newswatch.utils.DateUtils;

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
        String sql = "SELECT id,website,url,state,times,title,date,time,author,source,content,create_date,create_time,update_date,update_time FROM news " +
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
                String date = rs.getString("date");
                String time = rs.getString("time");
                String author = rs.getString("author");
                String source = rs.getString("source");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, date, time, author, source, content, createDate, createTime, updateDate, updateTime);
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
                "(id,website,url,state,times,title,date,time,author,source,content,create_date,create_time,update_date,update_time)" +
                "values" +
                "(null,'" + news.getWebsite() + "','" + news.getUrl() + "'," + news.getState() + "," +
                news.getTimes() + ",'" + news.getTitle() + "','" + news.getDate() + "','" + news.getTime() + "','" + news.getAuthor() + "','" + news.getSource() + "','" + news.getContent() + "','" + 
                news.getCreateDate() + "','" + news.getCreateTime() + "','" + news.getUpdateDate() + "','" + 
                news.getUpdateTime() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 更新新闻只展示
     *
     * @param website
     * @param likeUrl
     * @throws Exception
     */
    public static void updateNewsOnlyDisplay(String website, String likeUrl) throws Exception {
        String sql = "update news set state=" + News.STATE_ONLY_DISPLAY + ", update_date='" + DateUtils.getCurrentDate() + 
        		"', update_time='" + DateUtils.getCurrentTime() + "' where website='" + website + "'" + 
	    		" AND url like '" + likeUrl + "' AND state=" + News.STATE_GRAB_URL_SUCCESS;
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
	    news.getTitle() + "', date='" + news.getDate() + "', time='" + news.getTime() + "', author='" + news.getAuthor() + "', source='" + news.getSource() + "', content='" + news.getContent() + "', update_date='" + news.getUpdateDate() + 
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
    public static News getNextGrabNews(List<String> processingUrlList) throws Exception {
        String sql = "SELECT id,website,url,state,times,title,date,time,author,source,content,create_date,create_time,update_date,update_time FROM news " +
                "WHERE state in (1,3)";
        for(String url : processingUrlList){
        	sql += " AND url != '" + url + "'";
        }
        sql += " ORDER BY state,id limit 1";
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
                String date = rs.getString("date");
                String time = rs.getString("time");
                String author = rs.getString("author");
                String source = rs.getString("source");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, date, time, author, source, content, createDate, createTime, updateDate, updateTime);
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
     * 根据网站分页查询所有地址
     * @param website
     * @param pageNo 页数 从1开始表示查询第一页
     * @return
     * @throws Exception
     */
    public static List<String> queryAllUrlByWebsiteAndPage(String website, int pageNo) throws Exception {
    	List<String> urlList = new ArrayList<String>();
        String sql = "SELECT url FROM news where website='" + website + "'"
        		+ " limit " + ((pageNo-1)*10000) + ", " + 10000;//每页10000条
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
        String sql = "SELECT id,website,url,state,times,title,date,time,author,source,content,create_date,create_time,update_date,update_time"
        		+ " FROM news where state > 1 order by update_date desc, update_time desc limit 1";
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
                String date = rs.getString("date");
                String time = rs.getString("time");
                String author = rs.getString("author");
                String source = rs.getString("source");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, date, time, author, source, content, createDate, createTime, updateDate, updateTime);
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
     * 查询新闻总数
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static int countAllNews() throws Exception {
        String sql = "SELECT count(1) count FROM news";
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

    /**
     * 根据网站，位置和地址模糊查新闻总数
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static int countNewsByWebsiteAndPositionAndUrl(String website, int position, String url) throws Exception {
        String sql = "SELECT count(1) count FROM news " +
                "WHERE website='" + website + "' AND url";
        if(UrlFilter.FILTER_TYPE_START_WITH == position){
        	sql += " like '" + url + "%'";
        }
        if(UrlFilter.FILTER_TYPE_INDEX_OF == position){
        	sql += " like '%" + url + "%'";
        }
        if(UrlFilter.FILTER_TYPE_END_WITH == position){
        	sql += " like '%" + url + "'";
        }
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

    /**
     * 根据位置和地址模糊删除新闻
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static void deleteNewsByBlackListWithWebsiteAndPositionAndUrl(String website, int position, String url) throws Exception {
        String sql = "DELETE FROM news " +
                "WHERE website='" + website + "' AND url";
        if(UrlFilter.FILTER_TYPE_START_WITH == position){
        	sql += " like '" + url + "%'";
        }
        if(UrlFilter.FILTER_TYPE_INDEX_OF == position){
        	sql += " like '%" + url + "%'";
        }
        if(UrlFilter.FILTER_TYPE_END_WITH == position){
        	sql += " like '%" + url + "'";
        }
        DB.executeUpdate(sql);
    }

    /**
     * 查询需要配置抓取方案的新闻
     *
     * @return
     * @throws Exception
     */
    public static List<News> queryNeedFetchPlanNews(String website) throws Exception {
    	List<News> newsList = new ArrayList<News>();
    	String sql = "SELECT id,website,url,state,times,title,date,time,author,source,content,create_date,create_time,update_date,update_time"
        		+ " FROM news where website='" + website + "' and state=2 and id not in ( "
        		+ " SELECT distinct n.id"
        		+ " FROM news n, fetch_plan f"
        		+ " where n.website='" + website + "' and f.website='" + website + "'"
				+ " and n.state=2 and f.state=1 and n.url like f.like_url"
				+ " and (f.not_like_url is null or TRIM(f.not_like_url) = '' or n.url not like f.not_like_url)"
        		+ " ) order by url limit 0, 50";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
            	int id = rs.getInt("id");
                String url = rs.getString("url");
                int state = rs.getInt("state");
                int times = rs.getInt("times");
                String title = rs.getString("title");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String author = rs.getString("author");
                String source = rs.getString("source");
                String content = rs.getString("content");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                News news = new News(id, website, url, state, times, title, date, time, author, source, content, createDate, createTime, updateDate, updateTime);
                newsList.add(news);
            }
            return newsList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据网站名称，模糊网址和不包括模糊网址
     * @param website
     * @param likeUrl
     * @param notLikeUrl
     * @return
     * @throws Exception
     */
    public static List<String> queryNewsByWebsiteAndLikeAndNotLike(String website, String likeUrl, String notLikeUrl) throws Exception {
        List<String> urlList = new ArrayList<String>();
    	String sql = "SELECT id,website,url,state,times,title,date,time,author,source,content,create_date,create_time,update_date,update_time FROM news " +
                "WHERE state=" + News.STATE_GRAB_URL_SUCCESS
                + " AND website='" + website + "'"
                + " AND url like '" + likeUrl + "'"
                + (StringUtils.isBlank(notLikeUrl)?"":" AND url not like '" + notLikeUrl + "'")
                + " ORDER BY url";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
            	urlList.add(rs.getString("url"));
            }
            return urlList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }
}