package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.DomainFilterDao;
import com.newswatch.dao.UrlFilterDao;
import com.newswatch.entities.DomainFilter;
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
public class CreateDomainFilterAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = 1L;
	private String website;
	private String domain;
	private boolean isWhite;
	private String remark;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "],domain:[" + domain + "],isWhite:[" + 
        		isWhite + "],remark:[" + remark + "]");
        //判字段为空
        if(StringUtils.isBlank(website) || StringUtils.isBlank(domain)){
            write(createResp(false, "您的操作有误，请重试！"));
            return null;
        }
        //判域名是否已经配置过
        if(DomainFilterDao.isDomainFilterExist(website, DomainFilter.TYPE_WHITE, domain) ||
        		DomainFilterDao.isDomainFilterExist(website, DomainFilter.TYPE_BLACK, domain)){
        	write(createResp(true, "该域名已配置过！"));
            return null;
        }
        //新增配置
        DomainFilter domainFilter = new DomainFilter(website, (isWhite?DomainFilter.TYPE_WHITE:DomainFilter.TYPE_BLACK),
        		domain, remark, DomainFilter.STATE_NORMAL, DateUtils.getCurrentDate(), DateUtils.getCurrentTime(),
        		StringUtils.EMPTY, StringUtils.EMPTY);
        DomainFilterDao.insertDomainFilter(domainFilter);
        //黑名单 新增地址过滤
        if(!isWhite){
        	UrlFilter urlFilter = new UrlFilter(website, UrlFilter.TYPE_BLACK, UrlFilter.FILTER_TYPE_INDEX_OF, 
        			domain, remark, UrlFilter.STATE_NORMAL, DateUtils.getCurrentDate(), DateUtils.getCurrentTime(), 
        			StringUtils.EMPTY, StringUtils.EMPTY);
        	UrlFilterDao.insertUrlFilter(urlFilter);
        }
        write(createResp(true, "配置成功！"));
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param tenDomains
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
