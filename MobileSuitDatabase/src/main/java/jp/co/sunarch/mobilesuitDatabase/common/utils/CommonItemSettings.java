package jp.co.sunarch.mobilesuitDatabase.common.utils;

import java.math.BigDecimal;

public class CommonItemSettings {
	
	public static String contentsRequired(String content) {
		return (content == "" || content == null) ? "0" : content;
	}

	public static String convertToString(String content) {
		return (content == "" || content == null) ? null : content;
	}

	public static BigDecimal convertToBigDecimal(String content) {
		return (content == "" || content == null) ? null : new BigDecimal(content);
	}

	public static Long convertToLong(String content) {
		return (content == "" || content == null) ? null : Long.parseLong(content);
	}

}
