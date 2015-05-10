package com.newswatch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.entities.UrlFilter;
import com.newswatch.interfaces.BaseInterface;
import com.newswatch.utils.DateUtils;
import com.newswatch.utils.PropertyUtil;

/**
 * 地址过滤实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class UrlFilterDao {

    /**
     * 根据id查地址过滤
     * @param id
     * @return
     * @throws Exception
     */
    public static UrlFilter getUrlFilterById(int id) throws Exception {
        String sql = "SELECT id,website,type,filter_type,filter_url_part,remark,state,create_date,create_time,update_date,update_time " +
        			"FROM url_filter " +
        			"WHERE id=" + id;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
            	String website = rs.getString("website");
                int type = rs.getInt("type");
                int filterType = rs.getInt("filter_type");
                String filterUrlPart = rs.getString("filter_url_part");
                String remark = rs.getString("remark");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                UrlFilter urlFilter = new UrlFilter(id, website, type, filterType, filterUrlPart, remark,
                		state, createDate, createTime, updateDate, updateTime);
                return urlFilter;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据条件查地址过滤
     * @param website
     * @param type
     * @param filterType
     * @param filterUrlPart
     * @return
     * @throws Exception
     */
    public static UrlFilter getUrlFilterByConditions(String website, int type, int filterType, String filterUrlPart) throws Exception {
        String sql = "SELECT id,website,type,filter_type,filter_url_part,remark,state,create_date,create_time,update_date,update_time " +
        			"FROM url_filter " +
        			"WHERE website='" + website + "' and type=" + type + " and filter_type=" + filterType + 
        			" and filter_url_part='" + filterUrlPart + "' and state=" + UrlFilter.STATE_NORMAL;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String remark = rs.getString("remark");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                UrlFilter urlFilter = new UrlFilter(id, website, type, filterType, filterUrlPart, remark, UrlFilter.STATE_NORMAL, createDate, createTime, updateDate, updateTime);
                return urlFilter;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 判地址过滤是否存在
     * @param website
     * @param type
     * @param filterType
     * @param filterUrlPart
     * @return
     * @throws Exception
     */
    public static boolean isUrlFilterExist(String website, int type, int filterType, String filterUrlPart) throws Exception {
    	UrlFilter urlFilter = getUrlFilterByConditions(website, type, filterType, filterUrlPart);
        return urlFilter != null;
    }

    /**
     * 根据条件查地址过滤总数
     * @param website
     * @param type
     * @param filterType
     * @param filterUrlPart
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static int countUrlFilterByConditions(String website, int type, int filterType, String filterUrlPart, String remark, int pageNum) throws Exception {
        String sql = "SELECT count(1) count " +
    				"FROM url_filter " +
    				"WHERE 1=1";
        if(StringUtils.isNotBlank(website)){
        	sql += " AND website='" + website + "'";
        }
        if(type > 0){
        	sql += " AND type=" + type;
        }
        if(filterType > 0){
        	sql += " AND filter_type=" + filterType;
        }
        if(StringUtils.isNotBlank(filterUrlPart)){
        	sql += " AND filter_url_part like '%" + filterUrlPart + "%'";
        }
        if(StringUtils.isNotBlank(remark)){
        	sql += " AND remark like '%" + remark + "%'";
        }
        sql += " AND state=" + UrlFilter.STATE_NORMAL;
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
     * 根据条件查地址过滤
     * @param website
     * @param type
     * @param filterType
     * @param filterUrlPart
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<UrlFilter> queryUrlFilterByConditions(String website, int type, int filterType, String filterUrlPart, String remark, int pageNum) throws Exception {
    	List<UrlFilter> urlFilterList = new ArrayList<UrlFilter>();
    	//地址过滤页面大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.URL_FILTER_PAGE_SIZE));
        
        String sql = "SELECT id,website,type,filter_type,filter_url_part,remark,state,create_date,create_time,update_date,update_time " +
    				"FROM url_filter " +
    				"WHERE 1=1";
        if(StringUtils.isNotBlank(website)){
        	sql += " AND website='" + website + "'";
        }
        if(type > 0){
        	sql += " AND type=" + type;
        }
        if(filterType > 0){
        	sql += " AND filter_type=" + filterType;
        }
        if(StringUtils.isNotBlank(filterUrlPart)){
        	sql += " AND filter_url_part like '%" + filterUrlPart + "%'";
        }
        if(StringUtils.isNotBlank(remark)){
        	sql += " AND remark like '%" + remark + "%'";
        }
        sql += " AND state=" + UrlFilter.STATE_NORMAL;
        sql += " ORDER BY ID desc LIMIT " + ((pageNum-1) * pageSize) + "," + pageSize;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
            	String website2 = rs.getString("website");
                int type2 = rs.getInt("type");
                int filterType2 = rs.getInt("filter_type");
                String filterUrlPart2 = rs.getString("filter_url_part");
                String remark2 = rs.getString("remark");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                UrlFilter urlFilter = new UrlFilter(id, website2, type2, filterType2, filterUrlPart2, remark2, state, createDate, createTime, updateDate, updateTime);
                urlFilterList.add(urlFilter);
            }
            return urlFilterList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据条件查地址过滤
     * @param website
     * @param type
     * @return
     * @throws Exception
     */
    public static List<UrlFilter> queryUrlFilterByWebsiteAndType(String website, int type) throws Exception {
    	List<UrlFilter> urlFilterList = new ArrayList<UrlFilter>();
        String sql = "SELECT id,website,type,filter_type,filter_url_part,remark,state,create_date,create_time,update_date,update_time " +
    				"FROM url_filter " +
    				"WHERE website='" + website + "'";
        	sql += " AND type=" + type;
	        sql += " AND state=" + UrlFilter.STATE_NORMAL;
	        sql += " ORDER BY ID ASC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
            	String website2 = rs.getString("website");
                int type2 = rs.getInt("type");
                int filterType = rs.getInt("filter_type");
                String filterUrlPart = rs.getString("filter_url_part");
                String remark = rs.getString("remark");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                UrlFilter urlFilter = new UrlFilter(id, website2, type2, filterType, filterUrlPart, remark, state, createDate, createTime, updateDate, updateTime);
                urlFilterList.add(urlFilter);
            }
            return urlFilterList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增地址过滤
     * @param urlFilter
     * @throws Exception
     */
    public static void insertUrlFilter(UrlFilter urlFilter) throws Exception {
        String sql = "insert into url_filter" +
                "(id,website,type,filter_type,filter_url_part,remark,state,create_date,create_time,update_date,update_time)" +
                "values" +
                "(null,'" + urlFilter.getWebsite() + "'," + urlFilter.getType() + "," + urlFilter.getFilterType() + ",'" +
                urlFilter.getFilterUrlPart() + "','" + urlFilter.getRemark() + "'," + urlFilter.getState() + ",'" + 
                urlFilter.getCreateDate() + "','" + urlFilter.getCreateTime() + "','" + urlFilter.getUpdateDate() + "','" + 
                urlFilter.getUpdateTime() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 删除地址过滤
     * @param id
     * @throws Exception
     */
    public static void deleteUrlFilterById(int id) throws Exception {
        String sql = "update url_filter set state=" + UrlFilter.STATE_DELETE + ",update_date='" + DateUtils.getCurrentDate() +
        		"',update_time='" + DateUtils.getCurrentTime() + "' where id=" + id;
        DB.executeUpdate(sql);
    }
}