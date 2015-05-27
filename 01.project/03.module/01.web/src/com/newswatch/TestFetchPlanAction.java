package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.entities.FetchPlan;
import com.newswatch.entities.GrabContentResult;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.GrabContentUtils;
import com.newswatch.utils.TokenUtil;

/**
 * 设置仅展示
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class TestFetchPlanAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = -2539151214845784591L;
	/**
	 * 网站
	 */
	private String website;
	/**
	 * 网址
	 */
	private String url;
	/**
	 * 模糊网址
	 */
	private String likeUrl;
	/**
	 * 不包括模糊网址
	 */
	private String notLikeUrl;
	/**
	 * 采集顺序
	 */
	private String orderIndex;
	/**
	 * 抬头前定位
	 */
	private String titlePre;
	/**
	 * 抬头后定位
	 */
	private String titlePost;
	/**
	 * 时间前定位
	 */
	private String timePre;
	/**
	 * 时间后定位
	 */
	private String timePost;
	/**
	 * 来源前定位
	 */
	private String sourcePre;
	/**
	 * 来源后定位
	 */
	private String sourcePost;
	/**
	 * 作者前定位
	 */
	private String authorPre;
	/**
	 * 作者后定位
	 */
	private String authorPost;
	/**
	 * 内容前定位
	 */
	private String contentPre;
	/**
	 * 内容后定位
	 */
	private String contentPost;
	/**
	 * 测试网址
	 */
	private String testUrl;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "],url:[" + url + "],likeUrl:[" + likeUrl + "],"
        		+ "notLikeUrl:[" + notLikeUrl + "],orderIndex:[" + orderIndex + "],titlePre:[" + titlePre + "],"
        		+ "titlePost:[" + titlePost + "],timePre:[" + timePre + "],timePost:[" + timePost + "],"
				+ "sourcePre:[" + sourcePre + "],sourcePost:[" + sourcePost + "],authorPre:[" + authorPre + "],"
				+ "authorPost:[" + authorPost + "],contentPre:[" + contentPre + "],contentPost:[" + contentPost + "],"
				+ "testUrl:[" + testUrl + "]");
    	
        //判字段为空
        if(StringUtils.isBlank(testUrl)){
            createResp(false, "操作有误，请重试！");
            writeJson();
            return null;
        }
        
        //内容抓取方案实体
        FetchPlan fetchPlan = new FetchPlan(website, likeUrl, notLikeUrl, 0, orderIndex, titlePre, titlePost, 
        		timePre, timePost, sourcePre, sourcePost, authorPre, authorPost, contentPre, contentPost, 
        		0, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
        
        //抓取内容
        GrabContentResult grabContentResult = GrabContentUtils.grabContent(fetchPlan, testUrl);
        createResp(grabContentResult);
        writeJson();
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param message
     * @return
     */
    public void createResp(boolean isSuccess, String message){
    	json.put("isSuccess", isSuccess);
    	json.put("message", message);
    	json.put("hasNewToken", true);
    	json.put("token", TokenUtil.createToken(request));
    }
    
    /**
     * 创建返回的结果
     * @param grabContentResult
     * @return
     */
    public void createResp(GrabContentResult grabContentResult){
    	json.put("isSuccess", grabContentResult.getIsSuccess());
    	json.put("message", grabContentResult.getMessage());
    	json.put("hasNewToken", true);
    	json.put("token", TokenUtil.createToken(request));
    	if(grabContentResult.getIsSuccess()){
    		json.put("title", grabContentResult.getTitle());
    		json.put("date", grabContentResult.getDate());
    		json.put("time", grabContentResult.getTime());
    		json.put("source", grabContentResult.getSource());
    		json.put("author", grabContentResult.getAuthor());
    		json.put("content", grabContentResult.getContent());
    	}
    }

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLikeUrl() {
		return likeUrl;
	}

	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}

	public String getNotLikeUrl() {
		return notLikeUrl;
	}

	public void setNotLikeUrl(String notLikeUrl) {
		this.notLikeUrl = notLikeUrl;
	}

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getTitlePre() {
		return titlePre;
	}

	public void setTitlePre(String titlePre) {
		this.titlePre = titlePre;
	}

	public String getTitlePost() {
		return titlePost;
	}

	public void setTitlePost(String titlePost) {
		this.titlePost = titlePost;
	}

	public String getTimePre() {
		return timePre;
	}

	public void setTimePre(String timePre) {
		this.timePre = timePre;
	}

	public String getTimePost() {
		return timePost;
	}

	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}

	public String getSourcePre() {
		return sourcePre;
	}

	public void setSourcePre(String sourcePre) {
		this.sourcePre = sourcePre;
	}

	public String getSourcePost() {
		return sourcePost;
	}

	public void setSourcePost(String sourcePost) {
		this.sourcePost = sourcePost;
	}

	public String getAuthorPre() {
		return authorPre;
	}

	public void setAuthorPre(String authorPre) {
		this.authorPre = authorPre;
	}

	public String getAuthorPost() {
		return authorPost;
	}

	public void setAuthorPost(String authorPost) {
		this.authorPost = authorPost;
	}

	public String getContentPre() {
		return contentPre;
	}

	public void setContentPre(String contentPre) {
		this.contentPre = contentPre;
	}

	public String getContentPost() {
		return contentPost;
	}

	public void setContentPost(String contentPost) {
		this.contentPost = contentPost;
	}

	public String getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}
}
