package jp.co.sunarch.mobilesuitDatabase.common.utils;

import java.math.BigDecimal;

public class CommonItemSettings {
	
	public static String contentsRequired(String content) {
		return (content.equals("") || content == null) ? "0" : content;
	}

	public static String convertToString(String content) {
		return (content.equals("") || content == null) ? null : content;
	}

	public static BigDecimal convertToBigDecimal(String content) {
		return (content.equals("") || content == null) ? null : new BigDecimal(content);
	}

	public static Long convertToLong(String content) {
		return (content.equals("") || content == null) ? null : Long.parseLong(content);
	}

	public static String convertBigDecimalToString(BigDecimal content) {
		return content == null ? null : content.toPlainString();
	}

	public static String convertLongToString(Long content) {
		return content == null ? null : String.valueOf(content);
	}

	public static String convertIntegerToString(Integer content) {
		return content == null ? null : String.valueOf(content);
	}

}
