package com.newswatch.utils;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;

import com.newswatch.context.ApplicationContext;
/**
 * redis操作
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
public class RedisUtils {
	@SuppressWarnings("unchecked")
	private static RedisTemplate<String, Object> redisTemplate = 
			(RedisTemplate<String, Object>)ApplicationContext.getInstance().getContext().getBean("redisTemplate");
	/**
	 * set
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
		return Boolean.TRUE.booleanValue();
	}
	
	/**
	 * get
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * delete
	 * @param key
	 * @return
	 */
	public Object delete(String key) {
		Object object = redisTemplate.opsForValue().get(key);
		if( object!=null){
			redisTemplate.delete(key);
		}
		return object;
	}
	
	/**
	 * listLeftPush
	 * @param key
	 * @return
	 */
	public static long listLeftPush(String key, String url) {
		long size = redisTemplate.opsForList().leftPush(key, url);
		return size;
	}
	
	/**
	 * listRightPush
	 * @param key
	 * @return
	 */
	public static long listRightPush(String key, String url) {
		long size = redisTemplate.opsForList().rightPush(key, url);
		return size;
	}
	
	/**
	 * listRange
	 * @param key
	 * @return
	 */
	public static List<Object> listRange(String key, long start, long end) {
		List<Object> list = redisTemplate.opsForList().range(key, start, end);
		return list;
	}
	
	/**
	 * hashSetNx
	 * @param hash
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean hashSetNx(String hash, String key, String value) {
		return redisTemplate.opsForHash().putIfAbsent(hash, key, value);
	}
	
	/**
	 * hashSize
	 * @param hash
	 * @return
	 */
	public static long hashSize(String hash) {
		return redisTemplate.opsForHash().size(hash);
	}
	
	/**
	 * hashExist
	 * @param hash
	 * @param key
	 * @return
	 */
	public static boolean hashExist(String hash, String key) {
		return redisTemplate.opsForHash().hasKey(hash, key);
	}
	
	/**
	 * main方法
	 * @param params
	 */
	public static void main(String[] params){
//		//往list右边插入
//		long result = RedisUtils.listRightPush("urlList", "http://www.people.com.cn_____1");
//		System.out.println(result);
//		result = RedisUtils.listRightPush("urlList", "http://www.people.com.cn/32306/359302/359317/index.html_____2");
//		System.out.println(result);
//		result = RedisUtils.listRightPush("urlList", "http://t.people.com.cn_____3");
//		System.out.println(result);
//		result = RedisUtils.listRightPush("urlList", "http://t.people.com.cn_____4");
//		System.out.println(result);
//		//查询所有的值
//		for(Object object : RedisUtils.listRange("urlList", 0, -1)){
//			System.out.println(object);
//		}
//		RedisUtils.set("name", "关向辉");
//		RedisUtils.set("姓名", "林琼英");
//		System.out.println(RedisUtils.get("name"));
//		System.out.println(RedisUtils.get("姓名"));
//		System.out.println(RedisUtils.hashSetNx("UrlList", "http://www.baidu.com", "_"));
//		System.out.println(RedisUtils.hashSetNx("UrlList", "http://www.baidu.com", "_"));
//		System.out.println(RedisUtils.hashSetNx("UrlList", "http://www.baidu.com", "_"));
//		System.out.println(RedisUtils.hashSetNx("UrlList", "http://www.baidu.com", "_"));
		System.out.println(RedisUtils.hashSize("人民网_URL_LIST"));
		System.out.println(RedisUtils.hashExist("人民网_URL_LIST", "http://legal.people.com.cn/n/2015/0430/c188502-26931560.html"));
	}
}
