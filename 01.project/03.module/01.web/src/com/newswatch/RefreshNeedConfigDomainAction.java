package com.newswatch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.DomainFilterDao;
import com.newswatch.interfaces.SymbolInterface;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.BaseUtil;
import com.newswatch.utils.TokenUtil;

/**
 * 新增地址过滤
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class RefreshNeedConfigDomainAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = -2539151214845784591L;
	/**
	 * 网站
	 */
	private String website;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("website:[" + website + "]");
        //判字段为空
        if(StringUtils.isBlank(website)){
            write(createResp(false, new ArrayList<String>(), "请选择网站"));
            return null;
        }
        //根据网站查询库里所有域名
        List<String> domainList = BaseUtil.queryAllDomainByWebsite(website);
        for(int i=0;i<domainList.size();i++){
        	domainList.set(i, StringUtils.lowerCase(domainList.get(i)));
        }
        int domainCount = domainList.size();
        //根据网站查询所有过滤域名
        List<String> domainFilterList = DomainFilterDao.queryAllDomainFilterByWebsite(website);
        for(int i=0;i<domainFilterList.size();i++){
        	domainFilterList.set(i, StringUtils.lowerCase(domainFilterList.get(i)));
        }
        //取差集 获得所有未配置的域名
        domainList.removeAll(domainFilterList);
        //取前10条未配置的域名
        List<String> tenDomains = new ArrayList<String>();
        for(String domain : domainList){
        	tenDomains.add(domain);
        	if(tenDomains.size() == 10){
        		break;
        	}
        }
        //返回message
        if(domainList.size() > 0){
       	 	message = "根据网站[" + website + "]查询库里所有域名数[" + domainCount + "]，其中未配置数[" + domainList.size() + "]";
            if(domainList.size() > 10){
            	message += "，前10条如下：";
            }
    	} else {
    		message = "根据网站[" + website + "]查询库里所有域名数[" + domainCount + "]都已配置";
    	}
        write(createResp(true, tenDomains, message));
        return null;
    }
    
    /**
     * 创建返回的结果
     * @param isSuccess
     * @param tenDomains
     * @param message
     * @return
     */
    public String createResp(boolean isSuccess, List<String> tenDomains, String message){
    	String resp = "{isSuccess:" + isSuccess + ",message:'" + message + "',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'";
    	if(isSuccess){
    		resp += ",tenDomains:[";
    		String temp = StringUtils.EMPTY;
    		for(String domain : tenDomains){
    			if(StringUtils.isNotBlank(temp)){
    				temp += SymbolInterface.SYMBOL_COMMA;
    			}
    			temp += "'" + domain + "'";
    		}
    		resp += temp + "]";
    	}
    	resp += "}";
    	return resp;
    }

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
