package com.newswatch.grab.people;

import org.apache.commons.lang.StringUtils;

import com.newswatch.utils.DateUtils;
import com.newswatch.utils.HttpClientUtils;

public class TestGrabSingleContentPepole {

	String url = "http://51fayan.people.com.cn/n/2015/0504/c383185-26941683.html";
	
	/**
	 * 处理方法
	 */
	public void process() throws Exception {
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]开始！");
		String content = StringUtils.EMPTY;
		try {
			content = HttpClientUtils.getWebContentByGet(url, "GBK");
		} catch(Exception e){
			System.out.println("获取异常发生！");
		}
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]结束！");
		
		String channel = StringUtils.EMPTY;
		String indexStr = "<!--面包屑-->";
		int index = content.indexOf(indexStr);
		if(index > -1){
			content = content.substring(index + indexStr.length());
			indexStr = "<!--正文-->";
			index = content.indexOf(indexStr);
			channel = content.substring(0, index);
			System.out.println(channel);
			System.out.println("<br>");
		}
		
		String title = StringUtils.EMPTY;
		indexStr = "<h1 id=\"p_title\">";
		index = content.indexOf(indexStr);
		if(index > -1){
			content = content.substring(index + indexStr.length());
			indexStr = "</h1>";
			index = content.indexOf(indexStr);
			title = content.substring(0, index);
			System.out.println(title);
			System.out.println("<br>");
		}
		
		String time = StringUtils.EMPTY;
		indexStr = "<span id=\"p_publishtime\">";
		index = content.indexOf(indexStr);
		if(index > -1){
			content = content.substring(index + indexStr.length());
			indexStr = "</span>";
			index = content.indexOf(indexStr);
			time = content.substring(0, index);
			System.out.println(time);
			System.out.println("<br>");
		}
		
		String textContent = StringUtils.EMPTY;
		indexStr = "<div id=\"p_content\">";
		index = content.indexOf(indexStr);
		if(index > -1){
			content = content.substring(index + indexStr.length());
			indexStr = "<div class=\"edit\" id=\"p_editor\">";
			index = content.indexOf(indexStr);
			textContent = content.substring(0, index);
			System.out.println(textContent);
			System.out.println("<br>");
		}
	}
	
	/**
	 * main方法
	 * @param params
	 */
	public static void main(String[] params) throws Exception {
		new TestGrabSingleContentPepole().process();
	}
}
