/*
 * @(#)DateUtils.java 1.0 2009-12-28下午02:50:38
 *
 * 德邦证券股份有限公司
 * Copyright (c) 1996-2012 Tebon, Inc. All rights reserved.
 */
package com.newswatch.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	时间工具类
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author tanks
 * @version 1.0, 2014-7-4
 * @since sirius-commons
 *
 */
public class DateUtils {
	
	public static final Pattern DATE_PATTERN_1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}$");
	public static final Pattern DATE_PATTERN_2 = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
	public static final Pattern TIME_PATTERN_1 = Pattern.compile("\\d{2}:\\d{2}:\\d{2}$");
	public static final Pattern TIME_PATTERN_2 = Pattern.compile("\\d{2}:\\d{2}$");
	
	public static final Map<String,String> DIGITAL_TO_CHINESES = new HashMap<String,String>();

	static {
		DIGITAL_TO_CHINESES.put("0", "零");
		DIGITAL_TO_CHINESES.put("1", "一");
		DIGITAL_TO_CHINESES.put("2", "二");
		DIGITAL_TO_CHINESES.put("3", "三");
		DIGITAL_TO_CHINESES.put("4", "四");
		DIGITAL_TO_CHINESES.put("5", "五");
		DIGITAL_TO_CHINESES.put("6", "六");
		DIGITAL_TO_CHINESES.put("7", "七");
		DIGITAL_TO_CHINESES.put("8", "八");
		DIGITAL_TO_CHINESES.put("9", "九");
	}
	
	//============================0.获取当前时间====================================
	    
	/**
	 * 获取当前日期类型时间
	 */
	public static Date getNow(){
		return new Date();
	}
	
	
	/**
	 * 获取当前时间戳
	 */
	public static long getNowTimestamp(){
		return getNow().getTime();		
	}
	
	/**
	 * 获取当前日期 yyyyMMdd
	 */
	public static String getCurrentDate(){
		return toMailDateDtPartString(getNow());		
	}
	
	/**
	 * 获取当期时间HHmmss
	 * @return
	 */
	public static String getCurrentTime(){
		return toMailTimeTmPartString(getNow());
	}
	
	/**
	 * 获取当期时间Yyyy年MM月dd日HH:mm
	 * @return
	 */
	public static String getCurrentYyyyMmDdHm(){
		return toMailDtm(getNow());
	}
	
	/**
	 * 获取当期时间MM月dd日HH:mm
	 * @return
	 */
	public static String getCurrentMmDdHm(){
		return toMailDtmPart(getNow());
	}
	
	/**
	 * 获取当期时间yyyyMMddHHmmss
	 * @return
	 */
	public static String getCurrentDateTime(){
		return toMailDateString(getNow());
	}


	public static String getCurrentGBKDateTime(){
		return DateUtils.toLongDateGBKString(DateUtils.getNow());
	}
	
	//============================1.Date2String=====================================
	
	/**
	 * 将一个日期型转换为指定格式字串
	 * @param aDate
	 * @param formatStr
	 * @return
	 */
    public static final String toFormatDateString(Date aDate, String formatStr) {
    	if (aDate == null) return StringUtils.EMPTY;
    	AssertUtils.hasText(formatStr);
        return new SimpleDateFormat(formatStr).format(aDate);

    }

    /**
     * 将一个日期型转换为'yyyy-MM-dd'格式字串 
     * @param aDate
     * @return
     */
    public static final String toShortDateString(Date aDate) {
        return toFormatDateString(aDate,SHORT_DATE_FORMAT);
    }
    
	/**
	 * 将一个日期型转换为'yyyyMMdd'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDateDtPartString(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATE_DT_PART_FORMAT);
    }

	/**
	 * 将一个日期型转换为'HHmmss'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailTimeTmPartString(Date aDate) {
        return toFormatDateString(aDate,MAIL_TIME_TM_PART_FORMAT);
    }
    
    
	/**
	 * 将一个日期型转换为'yyyyMMddHHmmss'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDateString(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATE_FORMAT);
    }
    
    /**
	 * 将一个日期型转换为MM月dd日HH:mm格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDtmPart(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATA_DTM_PART_FORMAT);
    }
    
    /**
	 * 将一个日期型转换为yyyy年MM月dd日HH:mm格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toMailDtm(Date aDate) {
        return toFormatDateString(aDate,MAIL_DATA_DTM_FORMAT);
    }
    
    
    /**
     * 
     */
    /**
	 * 将一个日期型转换为yyyy.MM.dd格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toPointDtmPart(Date aDate) {
        return toFormatDateString(aDate,POINT_DATA_DTM_PART_FORMAT);
    }
    
    
    /**
     * 将一个日期型转换为'yyyy-MM-dd HH:mm:ss'格式字串
     * @param aDate
     * @return
     */
    public static final String toLongDateString(Date aDate) {
        return toFormatDateString(aDate,LONG_DATE_FORMAT);
    }

	/**
	 * 将一个日期型转换为'HH:mm:ss'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toLongDateTmPartString(Date aDate) {
        return toFormatDateString(aDate,LONG_DATE_TM_PART_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日'格式字串
     * @param aDate
     * @return
     */
    public static final String toShortDateGBKString(Date aDate) {
        return toFormatDateString(aDate,SHORT_DATE_GBK_FORMAT);
    }
    
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分'格式字串
     * @param aDate
     * @return
     */
    public static final String toDateGBKString(Date aDate) {
        return toFormatDateString(aDate,DATE_GBK_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分ss秒'格式字串
     * @param aDate
     * @return
     */
    public static final String toLongDateGBKString(Date aDate) {
        return toFormatDateString(aDate,LONG_DATE_GBK_FORMAT);
    }
    
	/**
	 * 将一个日期型转换为'HH时mm分ss秒'格式字串
	 * @param aDate
	 * @return
	 */
    public static final String toLongDateTmPartGBKString(Date aDate) {
        return toFormatDateString(aDate,Long_DATE_TM_PART_GBK_FORMAT);
    }    

    /** 
     * 将一个日期型转换为'yyyy-MM-dd HH:mm:ss:SSS'格式字串
     * @param aDate
     * @return
     */
    public static final String toFullDateString (Date aDate){
    	return toFormatDateString(aDate,FULL_DATE_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyy年MM月dd日 HH时mm分ss秒SSS毫秒'格式字串
     * @param aDate
     * @return
     */
    public static final String toFullDateGBKString (Date aDate){
    	return toFormatDateString(aDate,FULL_DATE_GBK_FORMAT);
    }
    
    /**
     * 将一个日期型转换为'yyyyMMddHHmmssSSS'格式字串
     * @param aDate
     * @return
     */
    public static final String toFullDateCompactString (Date aDate){
    	return toFormatDateString(aDate,FULL_DATE_COMPACT_FORMAT);
    }
    
    /**
     * 将一个日期型转换为LDAP格式字串
     * @param aDate
     * @return
     */
    public static final String toLDAPDateString(Date aDate) {
        return toFormatDateString(aDate, LDAP_DATE_FORMAT);
    }
    
    
	//============================2.String2Date=====================================    
    
    /**
     * 将一个符合指定格式的字串解析成日期型
     * @param aDateStr
     * @param formatter
     * @return
     * @throws ParseException 
     */
    public static final Date parser(String aDateStr, String formatter) throws ParseException{
    	if (StringUtils.isBlank(aDateStr)) return null;
    	AssertUtils.hasText(formatter);
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.parse(aDateStr);

    }
    
    /**
     * 获取日期格式
     * @param date 日期字符串
     * @return 日期格式
     * @throws Exception 非法参数或不支持格式错误信息
     */
    public static String getDatePattern(String date) throws Exception{
    	if( date==null || "".equals(date) ){
    		throw new java.lang.IllegalArgumentException("非法日期参数，无法解析日期");
    	}
    	if( DATE_PATTERN_1.matcher(date).find() ){
			return "yyyy-MM-dd";
		}else if( DATE_PATTERN_2.matcher(date).find() ){
			return "yyyy-MM-dd HH:mm:ss";
		}else if( TIME_PATTERN_1.matcher(date).find()){
			return "HH:mm:ss";
		}else if( TIME_PATTERN_2.matcher(date).find()){
			return "HH:mm";
		}else{
			throw new Exception("未知日期格式，无法解析日期"); 
		}
    }
    
    /**
     * 解析日期字符串至日期类型内容,返回java.sql.Date日期类型数据内容
     * @param date 日期字符串
     * @param format 与日期字符串格式匹配的格式
     * @return 解析后返回的日期
     */
    public static java.sql.Date parseSqlDate(String date, String format) {
        try {
            SimpleDateFormat formatter;
            if (null == format)  throw new IllegalArgumentException("错误的日期格式");
            formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            java.util.Date utilDate = formatter.parse(date, pos);
            return new java.sql.Date( utilDate.getTime() );
        } catch (Exception e) {
            throw new IllegalArgumentException("错误的日期:" + date , e);
        }
    }

    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     * 
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,LONG_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     * 
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateDtPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,LONG_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss'格式的字串解析成日期型
     * 
     * @param aDateStr
     * @return
     */
    public static final Date parseLongDateTmPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,LONG_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyy-MM-dd'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseShortDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,SHORT_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyyMMddHHmmss'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,MAIL_DATE_FORMAT);
 
    }
    
    /**
     * 将一个符合'yyyyMMdd'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateDtPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,MAIL_DATE_DT_PART_FORMAT);
    }
    
    /**
     * 将一个符合'HHmmss'格式的字串解析成日期型
     * @param aDateStr
     * @return
     */
    public static final Date parseMailDateTmPartString(String aDateStr) throws ParseException{
    	return parser(aDateStr,MAIL_TIME_TM_PART_FORMAT);
    }
    
   
    /**
     * 将一个符合'yyyy-MM-dd HH:mm:ss:SSS'格式的字串解析成日期型
     * @param strDate
     * @return
     */
    public static final Date parseFullDateString(String aDateStr) throws ParseException{
    	return parser(aDateStr,FULL_DATE_FORMAT);
 
    }    
    
    /**
     * 将一个符合'yyyy-MM-dd'、'yyyy-MM-dd HH:mm:ss'或'EEE MMM dd HH:mm:ss zzz yyyy'格式的字串解析成日期型，
     * 如果为blank则返回空，如果不为blank又不符合格式则报错
     * @param aDateStr
     * @return
     */
    public static Date parseDateString(String aDateStr){
    	Date ret = null;
		if (StringUtils.isNotBlank(aDateStr)){
			try {
				if (DataValidator.isShortDateStr(aDateStr)){
					ret = DateUtils.parseShortDateString(aDateStr);
				} else if (DataValidator.isLongDateStr(aDateStr)){
					ret = DateUtils.parseLongDateString(aDateStr);
				} else if (DataValidator.isDateStrMatched(aDateStr, DateUtils.DEFAULT_DATE_FORMAT)){
					ret = DateUtils.parser(aDateStr, DateUtils.DEFAULT_DATE_FORMAT);
				} else {
					throw new IllegalArgumentException("date format mismatch");
				}
			} catch (ParseException e) {
				//不可能到这里
			}  					
		}
		return ret;
    }
    
    
    /**
     * 解析日期字符串至日期类型内容
     * @param date 日期字符串
     * @param format 与日期字符串格式匹配的格式
     * @return 解析后返回的日期
     */
    public static java.util.Date parseDate(String date, String format) {
        try {
            SimpleDateFormat formatter;
            if (null == format)
                throw new IllegalArgumentException("错误的日期格式");
            formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            return formatter.parse(date, pos);
        } catch (Exception e) {
            throw new IllegalArgumentException("错误的日期:" + date + " " + e);
        }
    }
    
    
    
  //============================3.String2String===================================== 
    
    
    
    /**
	 * 转换日期格式 yyyy-MM-dd => yyyyMMdd
	 * @param dt yyyy-MM-dd
	 * @return yyyyMMdd
	 */
	public static String transfer2ShortDate(String dt){
    	if(dt==null||dt.length()!=10){
    	    return dt;
    	}
		AssertUtils.notNull(dt);
		String[] tmp = StringUtils.split(dt,IConstants.DASH);
		AssertUtils.isTrue(tmp!=null&&tmp.length==3);		
		return tmp[0].concat(StringUtils.leftPad(tmp[1], 2, "0")).concat(StringUtils.leftPad(tmp[2], 2, "0"));
	}
	
	/**
	 * 转换日期格式 yyyyMMdd => yyyy-MM-dd
	 * @param dt yyyyMMdd
	 * @return yyyy-MM-dd
	 */
	public static String transfer2LongDateDtPart(String dt){
    	if(dt==null||dt.length()!=8){
    	    return dt;
    	}
		AssertUtils.notNull(dt);
		AssertUtils.isTrue(dt.length()==8);	
		return dt.substring(0, 4).concat(IConstants.DASH).concat(dt.substring(4, 6)).concat(IConstants.DASH).concat(dt.substring(6));
	}
    
	/**
	 * 转换日期格式 HHmmss => HH:mm:ss
	 * @param tm HHmmss
	 * @return HH:mm:ss
	 */
	public static String transfer2LongDateTmPart(String tm){
    	if(tm==null||tm.length()!=6){
    	    return tm;
    	}
		AssertUtils.notNull(tm);
		AssertUtils.isTrue(tm.length()==6);	
		return tm.substring(0, 2).concat(IConstants.COLON).concat(tm.substring(2, 4)).concat(IConstants.COLON).concat(tm.substring(4));
	}
	
	
	/**
	 * 转换日期格式 yyyyMMdd => yyyy年MM月dd日
	 * @param dt yyyyMMdd
	 * @return yyyy年MM月dd日
	 */
	public static String transfer2LongDateGbkDtPart(String dt){
    	if(dt==null||dt.length()!=8){
    	    return dt;
    	}		
		AssertUtils.notNull(dt);
		AssertUtils.isTrue(dt.length()==8);	
		return dt.substring(0, 4).concat("年").concat(dt.substring(4, 6)).concat("月").concat(dt.substring(6)).concat("日");
	}
	
	
	/**
	 * 转换日期格式HHmmss => HH时mm分ss秒
	 * @param tm HHmmss
	 * @return HH时mm分ss秒
	 */
	public static String transfer2LongDateGbkTmPart(String tm){
    	if(tm==null||tm.length()!=6){
    	    return tm;
    	}	
		AssertUtils.notNull(tm);
		AssertUtils.isTrue(tm.length()==6);	
		return tm.substring(0, 2).concat("时").concat(tm.substring(2, 4)).concat("分").concat(tm.substring(4)).concat("秒");
	}
    
    

    
	//============================4.时间加减=====================================    
    
    
    /**
     * 为一个日期加上指定年数
     * @param aDate
     * @param amount 年数
     * @return
     */
    public static final Date addYears(Date aDate, int amount) {
		return addTime(aDate,Calendar.YEAR,amount);    
    }

    /**
     * 为一个日期加上指定月数
     * @param aDate
     * @param amount 月数
     * @return
     */
	public static final Date addMonths(Date aDate, int amount) {
		return addTime(aDate,Calendar.MONTH,amount);  
	}	

    /**
     * 为一个日期加上指定天数
     * @param aDate
     * @param amount 天数
     * @return
     */
    public static final Date addDays(Date aDate, int amount) {
		return addTime(aDate,Calendar.DAY_OF_YEAR,amount);        
    }
    
    /**
     * 为一个日期加上指定天数
     * @param aDate yyyyMMdd格式字串
     * @param amount 天数
     * @return
     */
    public static final String addDays(String aDate, int amount) {
    	try {
    		return DateUtils.toMailDateDtPartString(addTime(DateUtils.parseMailDateDtPartString(aDate),Calendar.DAY_OF_YEAR,amount));  
    	} catch (ParseException e) {
    		throw new RuntimeException(e);
		} 
    }

    /**
     * 为一个日期加上指定小时数
     * @param aDate 
     * @param amount 小时数
     * @return
     */
	public static final Date addHours(Date aDate,int amount) {	
		return addTime(aDate,Calendar.HOUR,amount);

	}
	
	/**
	 * 为一个日期加上指定分钟数
	 * @param aDate
	 * @param amount 分钟数
	 * @return
	 */
	public static final Date addMinutes(Date aDate,int amount) {	
		return addTime(aDate,Calendar.MINUTE,amount);
	}
	
	/**
	 * 为一个日期加上指定秒数 
	 * @param aDate
	 * @param amount 秒数
	 * @return
	 */
	public static final Date addSeconds(Date aDate,int amount) {	
		return addTime(aDate,Calendar.SECOND,amount);

	}

	private static final Date addTime(Date aDate,int timeType,int amount){
		if (aDate == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();		
		cal.setTime(aDate);
		cal.add(timeType, amount);
		return cal.getTime();
	}
	
	
	
	//======================================5.时间国际化=================================
	
	/**
	 * 得到当前时间的UTC时间
	 * @return
	 */
	public static final String getUTCTime(){
		return getSpecifiedZoneTime(Calendar.getInstance().getTime(), TimeZone.getTimeZone("GMT+0"));
	}
	
	/**
	 * 得到指定时间的UTC时间
	 * @param aDate 时间戳
	 * @return yyyy-MM-dd HH:mm:ss 格式
	 */
	public static final String getUTCTime(Date aDate){
		return getSpecifiedZoneTime(aDate, TimeZone.getTimeZone("GMT+0"));
	}
	
	/**
	 * 得到当前时间的指定时区的时间
	 * @param tz
	 * @return
	 */
	public static final String getSpecifiedZoneTime(TimeZone tz){	
    	return getSpecifiedZoneTime(Calendar.getInstance().getTime(),tz);

	}
	
	/**
	 * 得到指定时间的指定时区的时间
	 * 
	 * @param aDate 时间戳,Date是一个瞬间的long型距离历年的位移偏量，
	 * 在不同的指定的Locale/TimeZone的jvm中，它toString成不同的显示值，
	 * 所以没有必要为它再指定一个TimeZone变量表示获取它时的jvm的TimeZone
	 * 
	 * @param tz 要转换成timezone
	 * 
	 * @return yyyy-MM-dd HH:mm:ss 格式
	 */
	public static final String getSpecifiedZoneTime(Date aDate, TimeZone tz){
		if (aDate == null) return StringUtils.EMPTY;
    	AssertUtils.notNull(tz);
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT);
		sdf.setTimeZone(tz);
		return sdf.format(aDate);
	}
	

	
	//==================================6. miscellaneous==========================
	
	/**
	 * 计算两个日期之间相差的月数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final int getDifferenceMonths(Date startDate, Date endDate){
		AssertUtils.notNull(startDate);
		AssertUtils.notNull(endDate);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		
		return Math.abs((startCal.get(Calendar.YEAR)-endCal.get(Calendar.YEAR))*12
				+(startCal.get(Calendar.MONTH)-endCal.get(Calendar.MONTH)));			
    }
	
	/**
     * 计算两个日期之间相差的月数
     * @param startDateStr   yyyy-mm-dd
     * @param endDateStr  yyyy-mm-dd
     * @return
     */
	public static final int getDifferenceMonths(String startDateStr, String endDateStr){
		DataValidator.checkShortDateStr(startDateStr);
		DataValidator.checkShortDateStr(endDateStr);
		try {
			return getDifferenceMonths(parseShortDateString(startDateStr),parseShortDateString(endDateStr));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}		
    }
    
	/**
	 * 计算两个日期之间相差的天数
	 * @param startDateStr yyyy-mm-dd
	 * @param endDateStr yyyy-mm-dd
	 * @return
	 */
	public static final int getDifferenceDays(String startDateStr,String endDateStr){
		return new Long(getDifferenceMillis(startDateStr,endDateStr)/(NANO_ONE_DAY)).intValue();
	}  
	
	/**
	 * 计算两个日期之间相差的天数
	 * @param startDateStr yyyymmdd
	 * @param endDateStr yyyymmdd
	 * @return
	 */
	public static final int getDifferenceDays2(String startDateStr,String endDateStr){
		return new Long(getDifferenceMillis(startDateStr,endDateStr,MAIL_DATE_DT_PART_FORMAT)/(NANO_ONE_DAY)).intValue();
	}  	
	
	/**
	 * 计算两个日期之间相差的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final int getDifferenceDays(Date startDate,Date endDate){
		return new Long(getDifferenceMillis(startDate,endDate)/(NANO_ONE_DAY)).intValue();
		
	}  
    
	/**
	 * 计算两个日期之间相差的的毫秒数
	 * @param startDateStr yyyy-mm-dd
	 * @param endDateStr yyyy-mm-dd
	 * @return
	 * @throws ParseException
	 */
    public static final long getDifferenceMillis(String startDateStr, String endDateStr){
		return getDifferenceMillis(startDateStr,endDateStr,SHORT_DATE_FORMAT);
    }
    
	/**
	 * 计算两个日期之间相差的的毫秒数
	 * @param startDateStr yyyyMMddHHmmss
	 * @param endDateStr yyyyMMddHHmmss
	 * @return
	 * @throws ParseException
	 */
    public static final long getDifferenceMillis2(String startDateStr, String endDateStr){
		return getDifferenceMillis(startDateStr,endDateStr,MAIL_DATE_FORMAT);
    }
    
    /**
     * 计算两个日期之间相差的的毫秒数
     * @param startDateStr
     * @param endDateStr
     * @param dateFormat
     * @return
     */
    public static final long getDifferenceMillis(String startDateStr, String endDateStr, String dateFormat){
		try {
			return getDifferenceMillis(parser(startDateStr,dateFormat),parser(endDateStr,dateFormat));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
    }    
    
    /**
     * 计算两个日期之间相差的的毫秒数
     * @param startDate
     * @param endDate
     * @return
     */
    public static final long getDifferenceMillis(Date startDate, Date endDate){
    	AssertUtils.notNull(startDate);
    	AssertUtils.notNull(endDate);
		return Math.abs(endDate.getTime()-startDate.getTime());
    }
    
    
    /**
	 * 检验 日期是否在指定区间内，如果格式错误，返回false
	 * 
	 * 如果maxDateStr或minDateStr为空则比较时变为正负无穷大，如果都为空，则返回false
	 * 
	 * @param aDate 
	 * @param minDateStr 必须是yyyy-MM-dd格式，时分秒为00:00:00
	 * @param maxDateStr 必须是yyyy-MM-dd格式，时分秒为00:00:00
	 * @return
	 */
	public static final boolean isDateBetween(Date aDate, String minDateStr, String maxDateStr){		
		AssertUtils.notNull(aDate);
		boolean ret = false;		
		try {
			Date dMaxDate = null;
			Date dMinDate = null;			
			dMaxDate = DateUtils.parseShortDateString(maxDateStr);
			dMinDate = DateUtils.parseShortDateString(minDateStr);
			switch ((dMaxDate!=null?5:3)+(dMinDate!=null?1:0)) {
			case 6:
				ret = aDate.before(dMaxDate)&&aDate.after(dMinDate);
				break;
			case 5:
				ret = aDate.before(dMaxDate);
				break;
			case 4:
				ret = aDate.after(dMinDate);
			}
		} catch (ParseException e) {
		}		
		return ret;
	}
	
	/**
	 * 检查字串是否为YYYYMMDD格式
	 * @param mailDateStr
	 * @return
	 */
	public static boolean isMailDateStr(String mailDateStr){
		boolean ret = true;
		try {
			parseMailDateDtPartString(mailDateStr);
		} catch (ParseException e) {
			ret = false;
		}
		return ret;
	}
	
	/**
	 * 检查字串是否为HHMISS格式
	 * @param mailTimeStr
	 * @return
	 */
	public static boolean isMailTimeStr(String mailTimeStr){
		boolean ret = true;
		try {
			parseMailDateTmPartString(mailTimeStr);
		} catch (ParseException e) {
			ret = false;
		}
		return ret;
	}
    
    /**
     * 计算某日期所在月份的天数
     * @param aDateStr yyyy-mm-dd
     * @return 
     */
    public static final int getDaysInMonth(String aDateStr){
    	DataValidator.checkShortDateStr(aDateStr);
		try {
			return getDaysInMonth(parseShortDateString(aDateStr));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
    
    /**
     * 计算某日期所在月份的天数
     * @param aDate
     * @return
     */
    public static final int getDaysInMonth(Date aDate){
    	AssertUtils.notNull(aDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
    

   public static final String getPrevDay(){
	   Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);    //得到前一天
		Date date = calendar.getTime();
		return toFormatDateString(date,MAIL_DATE_DT_PART_FORMAT);
   }
   
   /**
    * 获取当前日期 MM月dd日
    */
   public static String getCurrentDateMD(){
   	return toMailDateMDPartString(getNow());		
   }

   /**
    * 获取当前时间 HH时mm分
    */
   public static String getCurrentDateHM(){
   		return toMailDateHMPartString(getNow());		
   }

   /**
    * 将一个日期型转换为'MM月dd日'格式字串
    * @param aDate
    * @return
    */
   public static final String toMailDateMDPartString(Date aDate) {
	   return toFormatDateString(aDate,MAIL_DATE_MD_FORMAT);
   }

   /**
    * 将一个日期型转换为'HH时mm分'格式字串
    * @param aDate
    * @return
    */
   public static final String toMailDateHMPartString(Date aDate) {
	   return toFormatDateString(aDate,MAIL_DATE_HM_FORMAT);
   }
   
   /**
	 * 转换日期格式 HHmmss => HH:mm
	 * @param tm HHmmss
	 * @return HH:mm:ss
	 */
	public static String transfer2LongDateTmPart2(String tm){
   	if(tm==null||tm.length()!=6){
   	    return tm;
   	}
		AssertUtils.notNull(tm);
		AssertUtils.isTrue(tm.length()==6);	
		return tm.substring(0, 2).concat(IConstants.COLON).concat(tm.substring(2, 4));
	}

   public static final String MAIL_DATE_MD_FORMAT = "MM月dd日";
   public static final String MAIL_DATE_HM_FORMAT = "HH时mm分";
	
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SHORT_DATE_GBK_FORMAT = "yyyy年MM月dd日";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分";       
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";  
    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String MAIL_DATE_HHMM_FORMAT = "HH:mm";
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FULL_DATE_GBK_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
    public static final String FULL_DATE_COMPACT_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String LDAP_DATE_FORMAT = "yyyyMMddHHmm'Z'";
    public static final String US_LOCALE_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
    
    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";    
    public static final String MAIL_TIME_TM_PART_FORMAT = "HHmmss";  
    public static final String LONG_DATE_TM_PART_FORMAT = "HH:mm:ss";
    public static final String Long_DATE_TM_PART_GBK_FORMAT = "HH时mm分ss秒";
    public static final String MAIL_DATA_DTM_FORMAT="yyyy年MM月dd日HH:mm";
    public static final String MAIL_DATA_DTM_PART_FORMAT="MM月dd日HH:mm";
    public static final String POINT_DATA_DTM_PART_FORMAT="yyyy.MM.dd";
    
    public static final String DEFAULT_DATE_FORMAT = US_LOCALE_DATE_FORMAT;

    public static long NANO_ONE_SECOND = 1000;
    public static long NANO_ONE_MINUTE = 60*NANO_ONE_SECOND;
    public static long NANO_ONE_HOUR = 60*NANO_ONE_MINUTE;
    public static long NANO_ONE_DAY = 24*NANO_ONE_HOUR;
    

    private DateUtils(){}
}
