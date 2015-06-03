package com.newswatch.grab.people;

import java.util.List;

import com.newswatch.dao.NewsDao;
import com.newswatch.dao.UrlFilterDao;
import com.newswatch.entities.UrlFilter;

/**
 * 删除库中黑名单对应的新闻
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
 * @version 1.0, 2015年5月10日
 * @since newswatch
 *
 */
public class TestDeleteBlackListPeople {
	public static final String PEOPLE_WEBSITE = "人民网";

	/**
	 * 处理方法
	 */
	public void process() throws Exception {
		/**
		 * 删除前新闻总数
		 */
		int count = NewsDao.countAllNews();
		System.out.println("==========>删除前新闻总数：" + count);
		int blackNewsCount = 0;
		/**
		 * 根据黑名单查询需要删除的数量
		 */
		List<UrlFilter> blackList = UrlFilterDao.
				queryUrlFilterByWebsiteAndType(PEOPLE_WEBSITE, UrlFilter.TYPE_BLACK);
		for(UrlFilter urlFilter : blackList){
			count = NewsDao.countNewsByWebsiteAndPositionAndUrl(
					PEOPLE_WEBSITE, urlFilter.getFilterType(), urlFilter.getFilterUrlPart());
			System.out.println("需要删除数目:" + count + ",url:" + urlFilter.getFilterUrlPart());
			blackNewsCount += count;
		}
		System.out.println("==========>根据黑名单查询需要删除的总数量:" + blackNewsCount);
		/**
		 * 根据黑名单删除新闻
		 */
		for(UrlFilter urlFilter : blackList){
			System.out.println("根据黑名单删除新闻，website=" + PEOPLE_WEBSITE + 
					", position=" + urlFilter.getFilterType() + 
					", url=" + urlFilter.getFilterUrlPart());
			NewsDao.deleteNewsByBlackListWithWebsiteAndPositionAndUrl(
					PEOPLE_WEBSITE, urlFilter.getFilterType(), urlFilter.getFilterUrlPart());
		}
		/**
		 * 删除后新闻总数
		 */
		count = NewsDao.countAllNews();
		System.out.println("==========>删除后新闻总数：" + count);
	}
	
	/**
	 * 入口方法
	 * @param params
	 * @throws Exception
	 */
	public static void main(String[] params) throws Exception{
		TestDeleteBlackListPeople deleteBlackListPeople = new TestDeleteBlackListPeople();
		deleteBlackListPeople.process();
	}
}
