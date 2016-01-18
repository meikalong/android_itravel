package com.itravel.util;

public class SecurityUtil {
	public static String randomSalt() {
		return null;
	}

	public static String MD5(String content, String salt, String key) {
		content = content + salt + key;
		// DigestUtils.md5Hex(content);
		return content;
	}
}
