package com.chingtech.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

/**
 * 页面说明：时间辅助类
 * 
 * @author 师春雷
 * @time 2014-4-20
 * @version 1.1
 * 
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtil {

	private TimeUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	private static String nowTime;
	private static long lTime;

	/**
	 * 获取系统当前时间
	 * 
	 * yyyy-MM-dd 格式
	 * 
	 * @return
	 */
	public static String now_time() {
		Date time = new Date();// 获取当前时间
		nowTime = formater3.get().format(time);

		return nowTime;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * yyyy-MM-dd HH:mm:ss 格式
	 * 
	 * @return
	 */
	public static String nowTime() {
		Calendar cal = Calendar.getInstance();
		String curDate = formater2.get().format(cal.getTime());
		return curDate;
	}

	public static boolean isUpdate(String updateTime) {

		long nowtime = transferDateToLong(nowTime());
		long updatetime = transferDateToLong(updateTime);

		if ((nowtime - updatetime) > 24 * 60 * 60 * 1000) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 把日期转化成毫秒
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static long transferDateToLong(String date) {
		try {
			lTime = formater2.get().parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lTime;
	}

	/**
	 * 得到当前时间long型
	 * 
	 * @return
	 */
	public static long nowTimeLong() {
		lTime = System.currentTimeMillis();
		return lTime;
	}

	/**
	 * 将时间字符串转换成 yyyy/MM/dd HH:mm格式的时间
	 * 
	 * @param strTime
	 * @return
	 */
	public static String transferStringToDate(String strTime) {
		Date start_date = null;
		try {
			start_date = formater2.get().parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String dateTime = formater4.get().format(start_date);

		return dateTime;
	}

	/**
	 * 将服务器获得的时间字符串转化成标准的日期 时间格式
	 * 
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strTime
	 * @return
	 */
	public static String standardFormat(String strTime) {
		String standardTime = strTime.substring(0, 10) + " "
				+ strTime.substring(11, 19);
		return standardTime;
	}

	/**
	 * 将服务器获得的时间字符串转化成标准的日期格式
	 * 
	 * yyyy-MM-dd
	 * 
	 * @param strTime
	 * @return
	 */
	public static String standardFormatYMD(String strTime) {
		String standardTime = strTime.substring(0, 10);
		return standardTime;
	}

	/**
	 * 格式化年份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		return formater5.get().format(date);
	}

	/**
	 * 格式化年月日
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMD(Date date) {
		return formater.get().format(date);
	}

	/**
	 * 将当前日期加减days天数
	 */
	public static String dateAdd(int days) {
		// 日期处理模块 (将日期加上某些天或减去天数)返回字符串
		Calendar canlendar = Calendar.getInstance();
		canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		String nowDate = formater3.get().format(date);
		return nowDate;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 *            服务器返回时间字符串格式： 2015-08-31T19:33:02.000Z
	 *
	 * @return
	 */
	public static String friendlyTime(String sdate) {
		Date time = null;

		time = toDate(sdate);

		if (time == null) {
			return "Unknown";
		}

		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 今天日期
		String curDate = formater.get().format(cal.getTime());
		// 服务器返回日期
		String paramDate = formater.get().format(time);

		// 两个时间间隔（单位秒）
		long timeInterval = (cal.getTimeInMillis() - time.getTime()) / 1000;

		// 判断是否是同一天
		if (curDate.equals(paramDate)) {
			int hour = (int) (timeInterval / 3600);
			if (hour == 0)
				ftime = Math.max(timeInterval / 60, 1) + "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		int days = (int) (timeInterval / 86400);
		if (days == 0) {
			int hour = (int) (timeInterval / 3600);
			if (hour == 0)
				ftime = Math.max(timeInterval / 60, 1) + "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天 ";
		} else if (days > 2 && days < 31) {
			ftime = days + "天前";
		} else if (days >= 31 && days <= 2 * 31) {
			ftime = "一个月前";
		} else if (days > 2 * 31 && days <= 3 * 31) {
			ftime = "2个月前";
		} else if (days > 3 * 31 && days <= 4 * 31) {
			ftime = "3个月前";
		} else {
			ftime = formater.get().format(time);
		}
		return ftime;
	}

	/**
	 * 将服务器返回时间字符串格式转为日期类型
	 * 
	 * @param sdate
	 *            服务器返回时间字符串格式： 2015-08-31T19:33:02.000Z
	 * @return
	 */
	public static Date toDate(String sdate) {
		Date date = null;
		String subDate = standardFormat(sdate);
		long actual_time;
		try {
			// 将格林尼治时间转换成北京时间
			actual_time = formater2.get().parse(subDate).getTime() + 8 * 60
					* 60 * 1000;
		} catch (ParseException e) {
			actual_time = 0;
		}

		date = new Date(actual_time);

		return date;
	}

	/**
	 * 将服务器返回时间字符串格式转为日期类型
	 * 
	 * @param sdate
	 *            服务器返回时间字符串格式： 2015-08-31T19:33:02.000Z
	 * @return
	 */
	public static String toDateString(String sdate) {
		Date date = null;
		date = toDate(sdate);
		// 服务器返回的真实日期
		return formater3.get().format(date);
	}

	public final static ThreadLocal<SimpleDateFormat> formater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年MM月dd日");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> formater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> formater3 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> formater4 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> formater5 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年");
		}
	};
}
