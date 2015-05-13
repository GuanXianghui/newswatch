package com.newswatch.people;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.News;
import com.newswatch.utils.DateUtils;

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
public class TestGrabPeopleThread implements Runnable {
	@Override
	public void run(){
		try{
			TestGrabPeople testGrabPeople = new TestGrabPeople();
			//testGrabPeople.firstInit();
			testGrabPeople.initUrlList();
			testGrabPeople.initWhiteBlackList();
			News news = NewsDao.getNextGrabNews();
			while(news != null){
				testGrabPeople.process(news.getUrl());
				news = NewsDao.getNextGrabNews();
			}
			System.out.println(DateUtils.getCurrentGBKDateTime() + ":全部采集结束！");
		} catch (Exception e){
			System.out.println("异常发生=================");
			e.printStackTrace();
		}
	}
}