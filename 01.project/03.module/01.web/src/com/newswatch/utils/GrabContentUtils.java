package com.newswatch.utils;

import org.apache.commons.lang.StringUtils;

import com.newswatch.entities.FetchPlan;
import com.newswatch.entities.GrabContentResult;
import com.newswatch.interfaces.GrabContentInterface;

/**
 * 抓取内容工具类
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
 * @version 1.0, 2015年5月27日
 * @since newswatch
 *
 */
public class GrabContentUtils implements GrabContentInterface {
	/**
	 * 抓取内容
	 * @param fetchPlan
	 * @param url
	 * @return
	 */
	public static GrabContentResult grabContent(FetchPlan fetchPlan, String url){
		GrabContentResult grabContentResult = new GrabContentResult();
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]开始！");
		String content = StringUtils.EMPTY;
		try {
			content = HttpClientUtils.getWebContentByGet(url, "GBK");
			if(StringUtils.isBlank(content)){
				throw new Exception("URL返回内容为空！");
			}
		} catch(Exception e){
			System.out.println("获取URL异常发生！");
			grabContentResult.setIsSuccess(false);
			grabContentResult.setMessage("获取URL异常发生！");
			return grabContentResult;
		}
		System.out.println(content);
		int count = 0;
		while(count < fetchPlan.getOrderIndex().length()){
			int orderIndex = Integer.parseInt(fetchPlan.getOrderIndex().substring(count, count+1));
			String value = StringUtils.EMPTY;
			String pre = StringUtils.EMPTY;
			String post = StringUtils.EMPTY;
			if(ORDER_INDEX_TITLE == orderIndex){
				pre = fetchPlan.getTitlePre();
				post = fetchPlan.getTitlePost();
			} else if(ORDER_INDEX_TIME == orderIndex){
				pre = fetchPlan.getTimePre();
				post = fetchPlan.getTimePost();
			} else if(ORDER_INDEX_SOURCE == orderIndex){
				pre = fetchPlan.getSourcePre();
				post = fetchPlan.getSourcePost();
			} else if(ORDER_INDEX_AUTHOR == orderIndex){
				pre = fetchPlan.getAuthorPre();
				post = fetchPlan.getAuthorPost();
			} else if(ORDER_INDEX_CONTENT == orderIndex){
				pre = fetchPlan.getContentPre();
				post = fetchPlan.getContentPost();
			}
			if(StringUtils.isNotBlank(pre)
					&& content.indexOf(pre) > -1){
				content = content.substring(content.indexOf(pre)
						+ pre.length());
			}
			if(StringUtils.isNotBlank(post)
					&& content.indexOf(post) > -1){
				value = content.substring(0, content.indexOf(post));
				content = content.substring(content.indexOf(post)
						+ post.length());
			} else {
				value = content;
			}
			System.out.println(value);
			if(ORDER_INDEX_TITLE == orderIndex){
				grabContentResult.setTitle(value);
			} else if(ORDER_INDEX_TIME == orderIndex){
				setTime(grabContentResult, value);
			} else if(ORDER_INDEX_SOURCE == orderIndex){
				grabContentResult.setSource(value);
			} else if(ORDER_INDEX_AUTHOR == orderIndex){
				grabContentResult.setAuthor(value);
			} else if(ORDER_INDEX_CONTENT == orderIndex){
				grabContentResult.setContent(value);
			}
			++count;
		}
		if(StringUtils.isBlank(grabContentResult.getTitle()) || StringUtils.isBlank(grabContentResult.getContent())){
			grabContentResult.setIsSuccess(false);
			grabContentResult.setMessage("抓取内容失败！");
		} else {
			grabContentResult.setIsSuccess(true);
			grabContentResult.setMessage("抓取内容成功！");
		}
		System.out.println(DateUtils.getCurrentGBKDateTime() + ":获取URL:[" + url + "]结束！");
		return grabContentResult;
	}
	
	/**
	 * 设置日期和时间
	 * @param grabContentResult
	 * @param value
	 */
	public static void setTime(GrabContentResult grabContentResult, String value){
		grabContentResult.setDate(value);
		grabContentResult.setTime(value);
	}
}
