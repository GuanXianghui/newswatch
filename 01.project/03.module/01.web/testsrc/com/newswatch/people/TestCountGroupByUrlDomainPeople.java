package com.newswatch.people;

import java.util.List;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.UrlFilter;
import com.newswatch.utils.BaseUtil;

/**
 * 查询所有域名前缀的数量
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
public class TestCountGroupByUrlDomainPeople {
	public static final String PEOPLE_WEBSITE = "人民网";
	
	/**
	 * 处理方法
	 */
	public void process() throws Exception {
		List<String> domainList = BaseUtil.queryAllDomainByWebsite(PEOPLE_WEBSITE);
		for(String domain : domainList){
			int count = NewsDao.countNewsByWebsiteAndPositionAndUrl(PEOPLE_WEBSITE, UrlFilter.FILTER_TYPE_START_WITH,domain);
			if(count > 200){
				System.out.println("count:[" + count + "],domain:[" + domain + "]");
			}
		}
	}
	
	/**
	 * 入口方法
	 * @param params
	 * @throws Exception
	 */
	public static void main(String[] params) throws Exception{
		TestCountGroupByUrlDomainPeople queryAllUrlDomainPeople = new TestCountGroupByUrlDomainPeople();
		queryAllUrlDomainPeople.process();
	}
}
