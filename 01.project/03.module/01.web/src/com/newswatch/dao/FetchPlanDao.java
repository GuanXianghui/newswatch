package com.newswatch.dao;

import com.newswatch.entities.FetchPlan;

/**
 * 新闻实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 20:22
 */
public class FetchPlanDao {
    /**
     * 新增内容抓取方案
     *
     * @param fetchPlan
     * @throws Exception
     */
    public static void insertFetchPlan(FetchPlan fetchPlan) throws Exception {
        String sql = "insert into fetch_plan" +
                "(id,website,like_url,not_like_url,state,order_index,title_pre,title_post,time_pre,time_post,"
                + "source_pre,source_post,author_pre,author_post,content_pre,content_post,times,create_date,create_time,update_date,update_time)" +
                "values" +
                "(null,'" + fetchPlan.getWebsite() + "','" + fetchPlan.getLikeUrl() + "','" + fetchPlan.getNotLikeUrl() + "'," +
                fetchPlan.getState() + ",'" + fetchPlan.getOrderIndex() + "','" + fetchPlan.getTitlePre() + "','" + fetchPlan.getTitlePost() + "','" + 
                fetchPlan.getTimePre() + "','" + fetchPlan.getTimePost() + "','" + fetchPlan.getSourcePre() + "','" + 
                fetchPlan.getSourcePost() + "','" + fetchPlan.getAuthorPre() + "','" + fetchPlan.getAuthorPost() + "','" + 
                fetchPlan.getContentPre() + "','" + fetchPlan.getContentPost() + "'," + fetchPlan.getTimes() + ",'" + 
                fetchPlan.getCreateDate() + "','" + fetchPlan.getCreateTime() + "','" + fetchPlan.getUpdateDate() + "','" + 
                fetchPlan.getUpdateTime() + "')";
        DB.executeUpdate(sql);
    }
}