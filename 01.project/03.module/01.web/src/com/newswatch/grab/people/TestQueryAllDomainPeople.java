package com.newswatch.grab.people;

import java.util.List;

import com.newswatch.utils.BaseUtil;

/**
 * 查询库中新闻的所有域名前缀
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	none
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Administrator
 * @version 1.0, 2015年5月4日
 * @since newswatch
 *
 */
public class TestQueryAllDomainPeople {
	public static final String PEOPLE_WEBSITE = "人民网";
	/**
	 * 处理方法
	 */
	public void process() throws Exception {
		List<String> domainList = BaseUtil.queryAllDomainByWebsite(PEOPLE_WEBSITE);
		System.out.println("总域名数:" + domainList.size());
		for(int i=0;i<domainList.size();i++){
			System.out.println(domainList.get(i));
		}
	}
	
	/**
	 * 入口方法
	 * @param params
	 * @throws Exception
	 */
	public static void main(String[] params) throws Exception{
		TestQueryAllDomainPeople queryAllDomainPeople = new TestQueryAllDomainPeople();
		queryAllDomainPeople.process();
	}
}
