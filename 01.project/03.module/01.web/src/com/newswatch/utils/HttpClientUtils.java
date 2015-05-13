/*
 * @(#)HttpClientUtils.java 1.0 2014-5-21下午5:06:53
 *
 * 德邦证券股份有限公司
 * Copyright (c) 2012-2014 Tebon, Inc. All rights reserved.
 */
package com.newswatch.utils;

/**
 * <dl>
 * <dt><b>Title:</b></dt>
 * <dd>
 * none</dd>
 * <dt><b>Description:</b></dt>
 * <dd>
 * <p>
 * none</dd>
 * </dl>
 * 
 * @author tanks
 * @version 1.0, 2014-5-21
 * @since sirius-commons
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtils {

	private static final Log logger = LogFactory.getLog(HttpClientUtils.class);

    /**
     * UTF-8
     */
    public static final String ENCODE_UTF8 = "UTF-8";

    /**
     * UTF-8
     */
    public static final String ENCODE_UTF16 = "UTF-16";

    /**
     * UTF-8
     */
    public static final String ENCODE_GB2312 = "GB2312";

    /**
     * GBK
     */
    public static final String ENCODE_GBK = "GBK";

    /**
     * GBK
     */
    public static final String ENCODE_ISO = "ISO-8859-1";

	/**
	 * 获得网页中的所有HTML内容
	 * 
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String getWebContentByGet(String url, String charset) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		StringBuilder sb = new StringBuilder();
		try {
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
			client.getHttpConnectionManager().getParams().setSoTimeout(10000); 
			getMethod.setRequestHeader("Connection", "close");
			getMethod.getParams().setBooleanParameter(
					"http.protocol.expect-continue", false);
			// 状态码
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				// 获得HTML文本
				BufferedReader bf = new BufferedReader(new InputStreamReader(
						getMethod.getResponseBodyAsStream(), charset));
				String line = null;
				while ((line = bf.readLine()) != null) {
					sb.append(line);
				}
				bf.close();
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		} finally {
			if (getMethod != null)
				getMethod.releaseConnection();
			if (client != null) {
				client = null;
			}
		}
		return sb.toString();
	}

	public static String getWebContentByGet(String url) throws Exception {
		return getWebContentByGet(url, "utf-8");
	}

	/**
	 * 获得网页中的所有HTML内容
	 * 
	 * @param url
	 * @param mapData
	 *            :传递的参数
	 * @param charset
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getWebContentByPost(String url,
			Map<String, String> mapData, String charset) {
		HttpClient client = new HttpClient();
		InputStream is = null;
		PostMethod postMethod = new PostMethod(url);
		StringBuilder sb = new StringBuilder();
		// 填入各个表单域的值
		NameValuePair[] data = new NameValuePair[mapData.size()];
		Set set = mapData.entrySet();
		Iterator iterator = set.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			data[i] = new NameValuePair((String) entry.getKey(),
					entry.getValue() == null ? StringUtils.EMPTY
							: (String) entry.getValue());
			i++;
		}
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		BufferedReader bf = null;
		InputStreamReader isReader = null;
		try {
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				// 获得HTML文本
				is = postMethod.getResponseBodyAsStream();
				isReader = new InputStreamReader(is, charset);
				bf = new BufferedReader(isReader);
				String line = null;
				while ((line = bf.readLine()) != null) {
					sb.append(line);
				}
				bf.close();
			}
		} catch (HttpException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					logger.error("关闭流错误", e);
				}
			}
			if (bf != null) {
				try {
					bf.close();
				} catch (Exception ex) {
					logger.error("关闭流句柄错误", ex);
				}
			}
			if (isReader != null) {
				try {
					isReader.close();
				} catch (Exception ex) {
					logger.error("关闭流句柄错误", ex);
				}
			}
			if (postMethod != null)
				postMethod.releaseConnection();
			if (client != null) {
				client = null;
			}
		}
		return sb.toString();
	}
	
	public static String postMap(String url, Map<String, String> map, String requestEncode, String responseEncode) throws Exception
    {
        PostMethod method = new PostMethod(url);
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext())
        {
            String key = (String)iterator.next();
            String value = map.get(key);
            if(StringUtils.isNotBlank(value))
            {
            	method.addParameter(key, value); 
            }
        }
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,requestEncode);
        return connect(method, responseEncode);
    }
	
	/**
     * 与服务器通讯
     * @param method
     * @param responseEncode
     * @return
     * @throws Exception
     */
    public static String connect(PostMethod method, String responseEncode) throws Exception
    {
        InputStream is = null;
        BufferedReader br = null;
        try
        {
            HttpClient client = new HttpClient();
            int returnCode = client.executeMethod(method);

            if (HttpURLConnection.HTTP_OK != returnCode)
            {
                logger.error("与服务器交互发生异常,returnCode=" + returnCode);
            }
            is = method.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), responseEncode));
            StringBuffer response = new StringBuffer();
            String readLine;
            while (((readLine = br.readLine()) != null))
            {
                response.append(readLine);
            }
            //logger.info("response.toString=====>" + response.toString());
            return response.toString();
        } catch (Exception e)
        {
            logger.error("与服务器交互发生异常~", e);
            throw new RuntimeException("与服务器交互发生异常~", e);
        } finally
        {
            try
            {
                if (null != method)
                {
                    method.releaseConnection();
                }
                if (null != is)
                {
                    is.close();
                }
                if (null != br)
                {
                    br.close();
                }
            } catch (Exception e)
            {
                logger.error("与服务器交互发生异常~", e);
            }
        }
    }

	public static void main(String[] str) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("applyrecordno", "20");
		map.put("channel", "3");
		map.put("format", "json");
		map.put("function", "valuavgr/valuquery");
		map.put("merid", "HEXUN");
		map.put("pageno", "1");
		map.put("sessionkey", "d131ee05ed364ee58bc2c3153fc5c59d");
		map.put("signmode", "md5");
		// map.put("signmsg", "ea98c3a60842276c2591fa0c42b6cd18");
		map.put("signmsg", "f3b78bc5a7414263695e9465c100eccc");
		map.put("state", "ALL");
		map.put("timestamp", "20131030142244");
		map.put("version", "V1.00");
		String content = HttpClientUtils.getWebContentByPost(
				//"http://172.24.16.233:7003/fundapi/restful/valuavgr/valuquery",
				"http://116.236.252.101:20153/FundTxInteract/FSIRequestReceiver",
				map, "UTF-8");
		System.out.println(content);
	}
}
