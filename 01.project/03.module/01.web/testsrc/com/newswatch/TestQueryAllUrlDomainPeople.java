package com.newswatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.newswatch.dao.NewsDao;

/**
 * 查询所有域名前缀
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
public class TestQueryAllUrlDomainPeople {
	/**
	 * 处理方法
	 */
	public void process() throws Exception {
		List<String> urlList = NewsDao.queryAllUrl();
		List<String> domainList = new ArrayList<String>();
		for(String url : urlList){
			String httpPref = "http://";
			if(url.startsWith(httpPref)){
				url = url.substring(httpPref.length());
				int index = url.indexOf("/");
				if(index > 0){
					url = url.substring(0, index);
				}
				url = httpPref + url;
				if(!domainList.contains(url)){
					domainList.add(url);
				}
			}
			String httpsPref = "https://";
			if(url.startsWith(httpsPref)){
				url = url.substring(httpsPref.length());
				int index = url.indexOf("/");
				if(index > 0){
					url = url.substring(0, index);
				}
				url = httpsPref + url;
				if(!domainList.contains(url)){
					domainList.add(url);
				}
			}
			int index = url.indexOf("/");
			if(index > 0){
				url = url.substring(0, index);
			}
			if(!domainList.contains(httpPref + url)){
				domainList.add(httpPref + url);
			}
		}
		Collections.sort(domainList);
		System.out.println(domainList.size());
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
		TestQueryAllUrlDomainPeople queryAllUrlDomainPeople = new TestQueryAllUrlDomainPeople();
		queryAllUrlDomainPeople.process();
	}
}
