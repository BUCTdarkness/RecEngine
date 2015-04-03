package com.DH_Recommend.util;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串工具类
 * 
 * @author ruijie
 * @date 2013-11-21
 * @version V1.0
 */
public class StringUtil {

	/**
	 * md5加密
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		try {
			StringBuffer buffer = new StringBuffer();
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(src.getBytes());

			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };
			for (byte b : bytes) {
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[(b) & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Integer Str2Int(String s) {
		Integer i = null;
		if (ValidateUtil.isValid(s)) {
			try {
				i = Integer.parseInt(s);
			} catch (Exception e) {

			}
		}
		return i;
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	public static Timestamp LongToStamp(String mili) {
		return new Timestamp(Long.parseLong(mili));
	}

	public static String StampToStr(Timestamp ts) {
		return Long.toString(ts.getTime());
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期转星座
	 * 
	 * @param date
	 * @return str
	 */
	public static String getConstellation(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);

		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
		Integer[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
		Integer num = month * 2 - (day < arr[month - 1] ? 2 : 0);
		return s.substring(num, num + 2);
	}
}
