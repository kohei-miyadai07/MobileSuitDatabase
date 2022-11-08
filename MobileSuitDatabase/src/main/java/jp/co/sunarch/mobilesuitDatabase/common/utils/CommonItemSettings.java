package jp.co.sunarch.mobilesuitDatabase.common.utils;

public class CommonItemSettings {
	
	public static String contentsRequired(String content) {
		return (content == "" || content == null) ? "0" : content;
	}

}
