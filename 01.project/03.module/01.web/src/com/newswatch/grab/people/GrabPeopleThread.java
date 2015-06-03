package com.newswatch.grab.people;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.News;

/**
 * 启动线程处理
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
public class GrabPeopleThread implements Runnable {
	
	GrabPeople testGrabPeople;
	
	/**
	 * 构造函数
	 * @param testGrabPeople
	 */
	public GrabPeopleThread(GrabPeople testGrabPeople) {
		this.testGrabPeople = testGrabPeople;
	}

	@Override
	public void run(){
		try{
			News news = NewsDao.getNextGrabNews(testGrabPeople.processingUrlList);
			while(news != null){
				/**
				 * 不在处理中则抓取地址
				 */
				if(!testGrabPeople.checkProcessing(news.getUrl())){
					testGrabPeople.grabUrl(news);
					testGrabPeople.removeProcessing(news.getUrl());;
				}
				news = NewsDao.getNextGrabNews(testGrabPeople.processingUrlList);
			}
		} catch (Exception e){
			System.out.println("异常发生=================");
			e.printStackTrace();
		}
	}
}