/*
 * @(#)AssertUtils.java 1.0 2014-7-4上午11:21:01
 *
 * 德邦证券股份有限公司
 * Copyright (c) 2012-2014 Tebon, Inc. All rights reserved.
 */
package com.newswatch.utils;

/**
 * <dl>
 *    <dt><b>Title:Assert帮助类</b></dt>
 *    <dd>
 *    	none
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
public class AssertUtils {
	
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}
	
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void hasText(String text, String message) {
		if (!hasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}
	
	public static void notEmpty(Object[] array, String message) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}
}
