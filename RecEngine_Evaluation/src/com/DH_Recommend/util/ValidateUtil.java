package com.DH_Recommend.util;

import java.util.Collection;


/**
 * 校验工具类
 * 
 * @author ruijie
 * @date 2013-11-21
 * @version V1.0
 */
public class ValidateUtil {
	/**
	 * 判断string是否有效
	 */
	public static boolean isValid(String str) {
		if (str == null || "".equals(str.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 判断集合的有效性
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection col) {
		if (col == null || col.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断数组有效性
	 */
	public static boolean isValid(Object[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断对象有效性
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isValid(Integer i) {
		if (i == null) {
			return false;
		}
		return true;
	}

	/**
	 * 判断对象有效性
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isValid(Long i) {
		if (i == null) {
			return false;
		}
		return true;
	}

	/**
	 * 判断对象有效性
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isValid(Object obj) {
		if (obj == null) {
			return false;
		}
		return true;
	}


	public static boolean isNum(String str) {
		if (ValidateUtil.isValid(str)) {
			for (int i = str.length(); --i >= 0;) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}

	public static boolean isDel(Integer del) {
		boolean bb = ValidateUtil.isValid(del);
		if (bb) {
			if (del > 0) {
				return true;
			}
		}
		return false;
	}
}
