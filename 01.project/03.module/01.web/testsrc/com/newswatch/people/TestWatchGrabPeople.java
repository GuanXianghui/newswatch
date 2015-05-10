package com.newswatch.people;

import java.util.Date;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.News;
import com.newswatch.utils.DateUtils;
import com.newswatch.utils.EmailUtils;

/**
 * 监控是否卡住
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
public class TestWatchGrabPeople {
	
	/**
	 * 处理方法
	 */
	public void process() throws Exception{
		while(true){
			News news = NewsDao.getLastGrabUrlReturnNews();
			if(news == null){
				System.out.println("异常发生！");
				return;
			}
			Date date = DateUtils.parseMailDateString(news.getUpdateDate() + news.getUpdateTime());
			Date now = new Date();
			long gap = (now.getTime() - date.getTime())/1000;
			/**
			 * 程序停滞1分钟
			 */
			if(gap > 60){
				System.out.println("程序停滞1分钟，发送邮件提醒！");
				EmailUtils.sendEmail("抓取停止", "程序抓取于时间:[" + news.getUpdateDate() + " " + news.getUpdateTime() + "]停止！", "419066357@qq.com");
			}
			Thread.sleep(20000);
		}
	}
	
	/**
	 * 入口方法
	 * @param params
	 * @throws Exception
	 */
	public static void main(String[] params) throws Exception {
		TestWatchGrabPeople watchGrabPeople = new TestWatchGrabPeople();
		watchGrabPeople.process();
	}
}
