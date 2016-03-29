package com.oneapm.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	public static final Log logger = LogFactory.getLog(DateUtil.class);
	public static final String HYPHEN_DATE = "yyyy-MM-dd";
	public static final String DATE = "yyyyMMdd";
	public static final String COLON_TIME = "HH:mm:ss";
	public static final String SEPARATOR_COLON = ":";
	public static final String HYPHEN_DATE_COLON_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME="yyyyMMddHHmmss";
	
	public static Date parseToDateLenient(String source, String pattern) 
			throws ParseException{
		Date date = DateUtil.parseToDateCore(source, pattern, true);
		return date;
	}
	
	/**
	 * 将String转成date类型
	 * @param source �?��转换的日期字符串
	 * @param pattern 转换格式
	 * @param isLenient  指定日期/时间解析是否不严格�?
	 * @return 转换后的日期
	 * @throws ParseException
	 */
	public static Date parseToDateCore(String source, String pattern, Boolean isLenient)
			throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(isLenient);
		Date date = dateFormat.parse(source);
		return date;
	}
	
	/**
	 * 将日期类型转换成String类型
	 * @param source  �?��转换的日�?
	 * @param pattern 转换格式
	 * @return 转换后的日期字符�?
	 */
	public static String transformToString(Date source, String pattern){
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateStr = dateFormat.format(source);
		return dateStr;
	}
	
	/**
	 * �?Date 对象表示的毫秒数
	 * @param source 日期类型的字符串
	 * @return 
	 * @throws ParseException
	 */
	public static String getTimeMillisStr(String source) throws ParseException{
		Date sourceDate = DateUtil.parseToDateCore(source, HYPHEN_DATE_COLON_TIME,false);
		Long millis = sourceDate.getTime();
		String millisStr = millis.toString();
		return millisStr;
	}
	
	/**
	 * 验证字符串是否可以转换成日期格式
	 * @param source 字符�?
	 * @param pattern 转换格式
	 * @return
	 */
	public static Boolean isInFormat(String source, String pattern){
		try {
			DateUtil.parseToDateLenient(source, pattern);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public static String getTimeStr(Calendar calendar){
		StringBuilder timeNow = new StringBuilder();
		timeNow.append(calendar.get(Calendar.HOUR_OF_DAY));
		timeNow.append(SEPARATOR_COLON);
		timeNow.append(calendar.get(Calendar.MINUTE));
		timeNow.append(SEPARATOR_COLON);
		timeNow.append(calendar.get(Calendar.SECOND));
		
		return timeNow.toString();
	}
	
	public static Boolean isInPeriod(String target, String startTimeStr, String endTimeStr){
		Long startTimeInMillis = DateUtil.parseToDate(startTimeStr, DateUtil.COLON_TIME).getTime();
		
		Long endTimeInMillis = DateUtil.parseToDate(endTimeStr, DateUtil.COLON_TIME).getTime();
		Long targetTimeInMillis = DateUtil.parseToDate(target, DateUtil.COLON_TIME).getTime();
		if(targetTimeInMillis >= startTimeInMillis
				&& targetTimeInMillis <= endTimeInMillis){
			return true;
		}else{
			return false;
		}
	}
	
	public static Date parseToDate(String source, String pattern){
		Date date = null;
		try {
			date = DateUtil.parseToDateCore(source, pattern, true);
		} catch (ParseException e) {
			logger.info("", e);
		}
		return date;
	}
	
	public static Long getInterval(String startTimeStr, String endTimeStr){
		Date endTime = DateUtil.parseToDate(endTimeStr, DateUtil.COLON_TIME);
		Date startTime = DateUtil.parseToDate(startTimeStr, DateUtil.COLON_TIME);
		Long interval = endTime.getTime() - startTime.getTime();
		
		return interval;
	}
	
	public static Long getSameTimeOfNextDay(String target){
		Long targetTimeInMillis = DateUtil.parseToDate(target, DateUtil.COLON_TIME).getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(targetTimeInMillis);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		Long time = calendar.getTimeInMillis();
		return time;
	}
	
	public static Long getInterval(String startTimeStr, Long endTimeInMillis){
		Date startTime = DateUtil.parseToDate(startTimeStr, DateUtil.COLON_TIME);
		Long interval = endTimeInMillis - startTime.getTime();
		
		return interval;
	}
	
	/**
	 * 将日期类型转化成字符串类�?
	 * @param date 日期
	 * @return 日期字符�?
	 */
	public static String getDateTimeStr(Date date){
		DateFormat df=new SimpleDateFormat(DATE_TIME);
		String dateStr=df.format(date);
		return dateStr;
	}
}
