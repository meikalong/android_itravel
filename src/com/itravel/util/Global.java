package com.itravel.util;

public class Global {
	public static boolean STATE;// true为开发者模式，false为演示模式
	public static String IP;
	public static String urlContent = "static/mobile/";
	private static String servletName = null;

	/**
	 * 最后返回：http://192.168.0.100/itravel/
	 * 
	 * @return
	 */
	public static String getServletName() {
		if (servletName == null) {
			servletName = "http://" + IP + "/itravel/";
		}
		return servletName;
	}

	/**
	 * 最后返回：192.168.0.100:8080/itravel/路径
	 * 
	 * @param url
	 *            请求路径 例如：order/second <br>
	 *            最前面不要有斜杠
	 * @return
	 */
	public static String getServletName(String url) {
		return getServletName() + url;
	}

}
