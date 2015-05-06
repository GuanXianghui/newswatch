package com.newswatch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.newswatch.dao.NewsDao;
import com.newswatch.entities.News;
import com.newswatch.utils.DateUtils;
import com.newswatch.utils.HttpClientUtils;

/**
 * 测试抓取人民网新闻数据
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
 * @author Gxx
 * @version 1.0, 2015年5月4日
 * @since newswatch
 *
 */
public class TestGrabPeople {
	public List<String> urlList = new ArrayList<String>();
	public static final String PEOPLE_WEBSITE = "人民网";
	public static final String PEOPLE_URL = "http://www.people.com.cn";
	
	/**
	 * 处理方法
	 */
	public void process(String url) throws Exception {
		News news = NewsDao.getNewsByUrl(url);
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]开始！");
		String content = StringUtils.EMPTY;
		try {
			content = HttpClientUtils.getWebContentByGet(url, "GBK");
		} catch(Exception e){
			System.out.println("获取异常发生！");
		}
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]结束！");
		if(StringUtils.isBlank(content)){
			System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]失败！");
			news.setState(News.STATE_GRAB_URL_FAIL);
			news.setTimes(news.getTimes() + 1);
			news.setUpdateDate(DateUtils.getCurrentDate());
			news.setUpdateTime(DateUtils.getCurrentTime());
			NewsDao.updateNewsByUrl(news);
			return;
		}
		List<String> tempList = new ArrayList<String>();
		try{
			analyseContent(tempList, content);
		} catch (Exception e){
			System.out.println(DateUtils.getCurrentGBKDateTime() + ":解析URL:[" + url + "]内容失败！");
			news.setState(News.STATE_GRAB_URL_FAIL);
			news.setTimes(news.getTimes() + 1);
			news.setUpdateDate(DateUtils.getCurrentDate());
			news.setUpdateTime(DateUtils.getCurrentTime());
			NewsDao.updateNewsByUrl(news);
			return;
		}
		
		news.setState(News.STATE_GRAB_URL_SUCCESS);
		news.setTimes(news.getTimes() + 1);
		news.setUpdateDate(DateUtils.getCurrentDate());
		news.setUpdateTime(DateUtils.getCurrentTime());
		NewsDao.updateNewsByUrl(news);
		
		for(String tempUrl : tempList){
			news = new News(PEOPLE_WEBSITE, tempUrl, News.STATE_INIT, 0, StringUtils.EMPTY, 
					StringUtils.EMPTY, DateUtils.getCurrentDate(), DateUtils.getCurrentTime(), 
					StringUtils.EMPTY, StringUtils.EMPTY);
			NewsDao.insertNews(news);
		}
	}
	
	/**
	 * 解析网页内容
	 * @param list
	 * @param content
	 */
	public void analyseContent(List<String> tempList, String content) throws Exception {
		String INDEX_STR = " href=";
		int index = content.indexOf(INDEX_STR);
		if(index > -1){
			content = content.substring(index + INDEX_STR.length());
			content = content.trim();
			if(content.startsWith("'")){
				content = content.substring(1);
				index = content.indexOf("'");
				if(index > -1){
					String url = content.substring(0, index);
					addUrl(tempList, url);
					content = content.substring(index + 1);
				}
			} else if(content.startsWith("\"")){
				content = content.substring(1);
				index = content.indexOf("\"");
				if(index > -1){
					String url = content.substring(0, index);
					addUrl(tempList, url);
					content = content.substring(index + 1);
				}
			} else if(content.startsWith("\\\'")){
				content = content.substring(2);
				index = content.indexOf("\\\'");
				if(index > -1){
					String url = content.substring(0, index);
					addUrl(tempList, url);
					content = content.substring(index + 1);
				}
			} else if(content.startsWith("\\\"")){
				content = content.substring(2);
				index = content.indexOf("\\\"");
				if(index > -1){
					String url = content.substring(0, index);
					addUrl(tempList, url);
					content = content.substring(index + 1);
				}
			} else {
				index = content.indexOf(" ");
				int index2 = content.indexOf(">");
				if(index > index2){
					index = index2;
				}
				if(index > -1){
					String url = content.substring(0, index);
					addUrl(tempList, url);
					content = content.substring(index + 1);
				}
			}
			analyseContent(tempList, content);
		}
	}
	
	/**
	 * 添加URL
	 * @param url
	 */
	public void addUrl(List<String> list, String url) throws Exception {
		url = StringUtils.trim(url);
		if(url.indexOf("people.com.cn") == -1){
			return;
		}
		if(url.endsWith(".do") || url.indexOf(".do?") > -1){
			return;
		}
		if(url.indexOf("mailto:") > -1){
			return;
		}
		if(url.indexOf("bbs1.people.com.cn") > -1){
			return;
		}
		if(url.indexOf("blog.people.com.cn") > -1){
			return;
		}
		if(url.indexOf("chinapic.people.com.cn") > -1){
			return;
		}
		if(url.indexOf("ads.people.com.cn") > -1){
			return;
		}
		url = url.replaceAll("'", "\"");
		if(urlList.indexOf(url) > -1){
			return;
		}
		list.add(url);
		urlList.add(url);
	}
	
	/**
	 * 第一次初始化新增首页地址
	 */
	public void firstInit() throws Exception {
		News news = new News(PEOPLE_WEBSITE, PEOPLE_URL, News.STATE_INIT, 0, StringUtils.EMPTY, 
				StringUtils.EMPTY, DateUtils.getCurrentDate(), DateUtils.getCurrentTime(), 
				StringUtils.EMPTY, StringUtils.EMPTY);
		NewsDao.insertNews(news);
	}
	
	/**
	 * 初始化urlList
	 * @throws Exception
	 */
	public void initUrlList() throws Exception {
		this.urlList = NewsDao.queryAllUrl();
		System.out.println("库中所有的URL大小:" + urlList.size());
	}
	
	/**
	 * main方法
	 * @param params
	 */
	public static void main(String[] params) throws Exception {
		TestGrabPeople testGrabPeople = new TestGrabPeople();
		//testGrabPeople.firstInit();
		testGrabPeople.initUrlList();
		News news = NewsDao.getNextGrabNews();
		while(news != null){
			testGrabPeople.process(news.getUrl());
			news = NewsDao.getNextGrabNews();
		}
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":全部采集结束！");
	}
}
