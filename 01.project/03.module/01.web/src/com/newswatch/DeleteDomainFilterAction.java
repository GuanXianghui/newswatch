package com.newswatch;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.DomainFilterDao;
import com.newswatch.entities.DomainFilter;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.TokenUtil;

/**
 * 删除域名过滤
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteDomainFilterAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = 1L;
	private String id;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("id:[" + id + "]");
        //判字段为空
        if(StringUtils.isBlank(id)){
            write(createResp(false, "请输入必输项"));
            return null;
        }
        //判已经存在域名过滤
        DomainFilter domainFilter = DomainFilterDao.getDomainFilterById(Integer.parseInt(id));
        if(domainFilter == null){
            write(createResp(false, "该过滤不存在"));
            return null;
        }
        DomainFilterDao.deleteDomainFilterById(Integer.parseInt(id));
        write(createResp(true, "删除域名过滤成功"));
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
