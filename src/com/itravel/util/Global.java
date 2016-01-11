package com.itravel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Global {
	public static boolean STATE;// true为开发者模式，false为演示模式
	public static String IP;
	private static String servletName = null;
	private static final int timeOut = 3000; // 超时应该在3钞以上

	public static boolean isIp(String IP) {// 判断是否是一个IP
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		// return b;
		return true;
	}

	public static boolean isPing(String ip) {
		boolean status = false;
		if (ip != null) {
			try {
				status = InetAddress.getByName(ip).isReachable(timeOut);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public String Ping(String str) {
		String resault = "";
		Process p;
		try {
			// ping -c 3 -w 100 中 ，-c 是指ping的次数 3是指ping 3次 ，-w 100
			// 以秒为单位指定超时间隔，是指超时时间为100秒
			p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + str);
			int status = p.waitFor();
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			System.out.println("Return ============" + buffer.toString());
			if (status == 0) {
				resault = "success";
			} else {
				resault = "faild";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return resault;
	}

	private static String trimSpaces(String IP) {// 去掉IP字符串前后所有的空格
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

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
