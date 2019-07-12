/**
 * @author shiming
 * @date 2015-5-21
 * @version 1.0
 */

package cn.itcast.bookStore.utils;

import java.util.UUID;

public class StrUtils {
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 判断一个字符串是否为空
	 * @param str 需要判断的字符串
	 * @return  true 字符串为空或者null， false 字符串不为null且不为空
	 */
	public static boolean isEmpty(String str) {
		
		if (null != str && !str.isEmpty()) {
			return false;
		}
		
		return true;
	}
}
