package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.UrlFilterDao;
import com.newswatch.entities.UrlFilter;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.DateUtils;
import com.newswatch.utils.TokenUtil;

/**
 * 新增地址过滤
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class CreateUrlFilterAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = 1L;
	private String website;
    private String type;
    private String filterType;
    private String filterUrlPart;
    private String remark;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "],type:[" + type + "],filterType:[" + filterType + 
        		"],filterUrlPart:[" + filterUrlPart + "],remark:[" + remark + "]");
        //判字段为空
        if(StringUtils.isBlank(website) || StringUtils.isBlank(type) || 
        		StringUtils.isBlank(filterType) || StringUtils.isBlank(filterUrlPart)){
            write(createResp(false, "请输入必输项"));
            return null;
        }
        //判已经存在网址过滤
        if(UrlFilterDao.isUrlFilterExist(website, Integer.parseInt(type), Integer.parseInt(filterType), filterUrlPart)){
            write(createResp(false, "该过滤已经存在"));
            return null;
        }
        //新增网址过滤
        UrlFilter urlFilter = new UrlFilter(website, Integer.parseInt(type), Integer.parseInt(filterType),
        		filterUrlPart, remark, UrlFilter.STATE_NORMAL, DateUtils.getCurrentDate(), 
        		DateUtils.getCurrentTime(), StringUtils.EMPTY, StringUtils.EMPTY);
        UrlFilterDao.insertUrlFilter(urlFilter);
        write(createResp(true, "新增地址过滤成功"));
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param message
     * @return
     */
    public String createResp(boolean isSuccess, String message){
    	String resp = "{isSuccess:" + isSuccess + ",message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
    	return resp;
    }

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterUrlPart() {
		return filterUrlPart;
	}

	public void setFilterUrlPart(String filterUrlPart) {
		this.filterUrlPart = filterUrlPart;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
