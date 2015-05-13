package com.newswatch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.entities.DomainFilter;
import com.newswatch.interfaces.BaseInterface;
import com.newswatch.utils.DateUtils;
import com.newswatch.utils.PropertyUtil;

/**
 * 域名过滤实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class DomainFilterDao {

    /**
     * 根据id查域名过滤
     * @param id
     * @return
     * @throws Exception
     */
    public static DomainFilter getDomainFilterById(int id) throws Exception {
        String sql = "SELECT id,website,type,domain,remark,state,create_date,create_time,update_date,update_time " +
        			"FROM domain_filter " +
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
                String domain = rs.getString("domain");
                String remark = rs.getString("remark");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                DomainFilter domainFilter = new DomainFilter(id, website, type, domain, remark,
                		state, createDate, createTime, updateDate, updateTime);
                return domainFilter;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据条件查域名过滤
     * @param website
     * @param type
     * @param domain
     * @return
     * @throws Exception
     */
    public static DomainFilter getDomainFilterByConditions(String website, int type, String domain) throws Exception {
        String sql = "SELECT id,website,type,domain,remark,state,create_date,create_time,update_date,update_time " +
        			"FROM domain_filter " +
        			"WHERE website='" + website + "' and type=" + type +
        			" and domain='" + domain + "' and state=" + DomainFilter.STATE_NORMAL;
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
                DomainFilter domainFilter = new DomainFilter(id, website, type, domain, remark, DomainFilter.STATE_NORMAL, createDate, createTime, updateDate, updateTime);
                return domainFilter;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 判域名过滤是否存在
     * @param website
     * @param type
     * @param domain
     * @return
     * @throws Exception
     */
    public static boolean isDomainFilterExist(String website, int type, String domain) throws Exception {
    	DomainFilter domainFilter = getDomainFilterByConditions(website, type, domain);
        return domainFilter != null;
    }

    /**
     * 根据条件查域名过滤总数
     * @param website
     * @param type
     * @param domain
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static int countDomainFilterByConditions(String website, int type, String domain, String remark, int pageNum) throws Exception {
        String sql = "SELECT count(1) count " +
    				"FROM domain_filter " +
    				"WHERE 1=1";
        if(StringUtils.isNotBlank(website)){
        	sql += " AND website='" + website + "'";
        }
        if(type > 0){
        	sql += " AND type=" + type;
        }
        if(StringUtils.isNotBlank(domain)){
        	sql += " AND domain like '%" + domain + "%'";
        }
        if(StringUtils.isNotBlank(remark)){
        	sql += " AND remark like '%" + remark + "%'";
        }
        sql += " AND state=" + DomainFilter.STATE_NORMAL;
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
     * 根据条件查域名过滤
     * @param website
     * @param type
     * @param domain
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<DomainFilter> queryDomainFilterByConditions(String website, int type, String domain, String remark, int pageNum) throws Exception {
    	List<DomainFilter> domainFilterList = new ArrayList<DomainFilter>();
    	//地址过滤页面大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.DOMAIN_FILTER_PAGE_SIZE));
        
        String sql = "SELECT id,website,type,domain,remark,state,create_date,create_time,update_date,update_time " +
    				"FROM domain_filter " +
    				"WHERE 1=1";
        if(StringUtils.isNotBlank(website)){
        	sql += " AND website='" + website + "'";
        }
        if(type > 0){
        	sql += " AND type=" + type;
        }
        if(StringUtils.isNotBlank(domain)){
        	sql += " AND domain like '%" + domain + "%'";
        }
        if(StringUtils.isNotBlank(remark)){
        	sql += " AND remark like '%" + remark + "%'";
        }
        sql += " AND state=" + DomainFilter.STATE_NORMAL;
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
                String domain2 = rs.getString("domain");
                String remark2 = rs.getString("remark");
                int state = rs.getInt("state");
                String createDate = rs.getString("create_date");
                String createTime = rs.getString("create_time");
                String updateDate = rs.getString("update_date");
                String updateTime = rs.getString("update_time");
                DomainFilter domainFilter = new DomainFilter(id, website2, type2, domain2, remark2, state, createDate, createTime, updateDate, updateTime);
                domainFilterList.add(domainFilter);
            }
            return domainFilterList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据网站查询所有过滤域名
     * @param website
     * @return
     * @throws Exception
     */
    public static List<String> queryAllDomainFilterByWebsite(String website) throws Exception {
    	List<String> domainFilterList = new ArrayList<String>();
        String sql = "SELECT domain" +
    				" FROM domain_filter" +
    				" WHERE website='" + website + "' AND state=" + DomainFilter.STATE_NORMAL;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String domain = rs.getString("domain");
                domainFilterList.add(domain);
            }
            return domainFilterList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增域名过滤
     * @param domainFilter
     * @throws Exception
     */
    public static void insertDomainFilter(DomainFilter domainFilter) throws Exception {
        String sql = "insert into domain_filter" +
                "(id,website,type,domain,remark,state,create_date,create_time,update_date,update_time)" +
                "values" +
                "(null,'" + domainFilter.getWebsite() + "'," + domainFilter.getType() + ",'" +
                domainFilter.getDomain() + "','" + domainFilter.getRemark() + "'," + domainFilter.getState() + ",'" + 
                domainFilter.getCreateDate() + "','" + domainFilter.getCreateTime() + "','" + domainFilter.getUpdateDate() + "','" + 
                domainFilter.getUpdateTime() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 删除域名过滤
     * @param id
     * @throws Exception
     */
    public static void deleteDomainFilterById(int id) throws Exception {
        String sql = "update domain_filter set state=" + DomainFilter.STATE_DELETE + ",update_date='" + DateUtils.getCurrentDate() +
        		"',update_time='" + DateUtils.getCurrentTime() + "' where id=" + id;
        DB.executeUpdate(sql);
    }
}