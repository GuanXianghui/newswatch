package com.newswatch.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring上下文
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
 * @version 1.0, 2015年6月3日
 * @since newswatch
 *
 */
public class ApplicationContext {
	private static ApplicationContext applicationContext = new ApplicationContext();
	private ClassPathXmlApplicationContext context;
	private ApplicationContext(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	public static ApplicationContext getInstance(){
		return applicationContext;
	}
	public ClassPathXmlApplicationContext getContext(){
		return context;
	}
}
