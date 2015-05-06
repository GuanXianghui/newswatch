/*
 * @(#)CommonConstants.java 1.0 2009-12-28下午01:10:51
 *
 * 德邦证券股份有限公司
 * Copyright (c) 1996-2012 Tebon, Inc. All rights reserved.
 */
package com.newswatch.utils;

import java.util.Locale;

/**
 * 
* <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	通用常量
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>存放一些常用的字符串常量
 *    </dd>
 * </dl>
 *
 * @author tanks
 * @version 1.0, 2014-7-4
 * @since sirius-commons
 *
 */
public interface IConstants {
	
	//-------------------------------【GENERAL CONSTANTS】-----------------------//	
	public static final String DEFAULT_STORED_PROCEDURE_RESULT = "RESULT";	
	public static final String ACCEPTED_SUCCESS = "S";	
	public static final String ACCEPTED_FAILED = "F";	

	//-----------------------------【APPLICATION CONSTANTS】-----------------------//
	public static final String 	ENUM_TRANSLATE_CONVERTER = "core.web.struts2.converter.EnumTranslateConverter";
	public static final String 	ENUM_ARRAY_CONVERTER = "core.web.struts2.converter.EnumArrayConverter";	
		
	//-------------------------------【SESSION CONSTANTS】-----------------------//	
	public static final String USER_TOKEN_KEY = "USER_TOKEN_KEY";

	
	//------------------------------【ATTRIBUTE CONSTANTS】-----------------------//
	public static final String AUTHCODE_ATTR = "j_authcode";
	public static final String POST_URL= "POST_URL";	
	public static final String POST_PARAMS_MAP= "POST_PARAMS_MAP";
	public static final String FORWARD_URL= "FORWARD_URL";
	public static final String REDIRECT_URL= "REDIRECT_URL";

	public static final String DOWNLOAD_DESTEFILE = "destFile";
	public static final String DOWNLOAD_SOURCEFILE = "srcFile";
	public static final String IS_RESOURCE = "isRes";
	
	public static final String COMMON_POST_URL = "/common/post.jsp";
	
	//-----------------------------【PROPERTIES FILES KEYS】 -------------------------//
	public static final String LOG_OUT_DIR_KEY = "log.out.dir";
	
	//------------------------------【WEB/XML GENERAL】 ---------------------------//
	public static String CONTENT_TYPE_HTML = "text/html";
	public static String CONTENT_TYPE_XML = "text/xml";
	public static String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	
	//-------------------------------【SYMBOL】-----------------------//
	public static final String BLANK = " ";
	public static final String SPACE = " ";
	public static final String BANG = "!";
	public static final String QUESTION_MARK = "?";	
	public static final String COMMA = ",";			
	public static final String POINT = ".";
	public static final String COLON = ":";
	public static final String SEMICOLON = ";";
	public static final String QUOTE = "'";
	public static final String SINGLE_QUOTE="\'";
	public static final String DOUBLE_QUOTE="\"";
	public static final String STAR = "*";
	public static final String PLUS = "+";
	public static final String DASH = "-";
	public static final String EQUAL = "=";
	public static final String SLASH = "/";
	public static final String BACK_SLASH = "\\";
	public static final String PIPE = "|";
	public static final String UNDERLINE = "_";
	public static final String DOLOR = "$";
	public static final String AT = "@";
	public static final String CROSS_HATCH = "#";
	public static final String PERCENT = "%";
	public static final String AND = "&";
	public static final String CIRCUMFLEX = "^";	
	public static final String TILDE = "~";
	public static final String LEFT_BRACE = "{";
	public static final String RIGHT_BRACE = "}";
	public static final String LEFT_BRACKET = "[";
	public static final String RIGHT_BRACKET = "]";
	public static final String LEFT_ANGLE_BRACKET = "<";
	public static final String RIGHT_ANGLE_BRACKET = ">";	
	public static final String LEFT_PARENTHESES = "(";
	public static final String RIGHT_PARENTHESES = ")";
	
	public static final String LINE_CHANGE_SYMBOL = "\n";
	public static final String ENTER_SYMBOL = 	"\r";
	
	
	//-----------------------------【***FIX】-----------------------//	
	public static final String CLASSPATH_PREFIX = "classpath:";		
	public static final String HBMFILES_SUFFIX =  ".hbm.xml";
	public static final String PROPERTIES_SUFFIX = ".properties";
	public static final String CLASS_SUFFIX = ".class";	
	public static final String TXT_SUFFIX = ".txt";
	
	//-----------------------------【LOCALE】-----------------------//
	public static final String[] ALL_LOCALES_STRING = {
		Locale.ENGLISH.toString(),
		Locale.FRENCH.toString(),
		Locale.GERMAN.toString(),
		Locale.ITALIAN.toString(),
		Locale.JAPANESE.toString(),
		Locale.KOREAN.toString(),
		Locale.CHINESE.toString(),
		Locale.SIMPLIFIED_CHINESE.toString(),
		Locale.TRADITIONAL_CHINESE.toString(),
		Locale.FRANCE.toString(),
		Locale.GERMANY.toString(),
		Locale.ITALY.toString(),
		Locale.JAPAN.toString(),
		Locale.KOREA.toString(),
		Locale.CHINA.toString(),
		Locale.PRC.toString(),
		Locale.TAIWAN.toString(),
		Locale.UK.toString(),
		Locale.US.toString(),
		Locale.CANADA.toString(),
		Locale.CANADA_FRENCH.toString()

	}; 
	public static final String DEFAUL_CHARSET = "UTF-8";
	public static final String CHINESE_CHARSET = "GBK";
	public static final String ISO_CHARSET = "ISO-8859-1";
	
	public static final String EMAIL_MD5_CODE="HEXUN";
	
	public static final String ACCO_ADD_BANK = "ACCOADDBANK";
	
	public static final String NEW_MAIL = "NEWMAIL";

	
	public static final String DATE_FORMATER_STYLE = "yyyyMMdd";//日期格式化样式
	public static final String DATETIME_FORMATER_STYLE = "HHmmss";//日期格式化样式
	
	//配置中心服务地址Key
	public static final String CONFIG_CENTER_SERVER_URL_KEY = "config.center.server.url";
	
	/** 本地缓存配置实用的名称**/
	public static final String DEFAULT_SYSTEM_CACHE_NAME = "systemCache";
	public static final String MANUAL_OPERATE_CACHE_NAME = "manualOperateCacheName";
	
	/** Ajax请求头部使用的常量**/
	public static final String XML_HTTP_REQUEST_IDENTIFICATION = "XMLHttpRequest";
	public static final String XML_HTTP_REQUEST_HEAD = "X-Requested-With";
	public static final String XML_HTTP_JSON_CONTENT_TYPE = "application/json";
	
	/**配置中心广播消息服务处理类实例Bean ID**/
	public static final String CONFIG_CENTER_MESSAGE_HANDLER_BEAN_ID = "configMessageHandler";
	
}
