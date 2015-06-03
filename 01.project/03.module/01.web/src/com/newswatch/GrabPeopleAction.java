package com.newswatch;

import com.newswatch.grab.people.GrabPeople;
import com.newswatch.interfaces.UserInterface;
import com.newswatch.utils.TokenUtil;

/**
 * 抓取人民网
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class GrabPeopleAction extends BaseAction implements UserInterface{
	private static final long serialVersionUID = -2539151214845784591L;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
    	new GrabPeople().process();
    	json.put("isSuccess", true);
    	json.put("message", "抓取人民网");
        writeJson();
        return null;
    }
    
    /**
     * 创建返回成功的结果
     * @return
     */
    public void createSuccessResp(){
    	json.put("isSuccess", true);
    	json.put("message", "保存抓取方案成功，请刷新网址！");
    	json.put("hasNewToken", true);
    	json.put("token", TokenUtil.createToken(request));
    }
}
