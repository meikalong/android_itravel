package com.itravel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Global {
	public static boolean STATE;// trueΪ������ģʽ��falseΪ��ʾģʽ
	public static String IP;
	private static String servletName = null;
	private static final int timeOut = 3000; // ��ʱӦ����3������

	public static boolean isIp(String IP) {// �ж��Ƿ���һ��IP
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
			// ping -c 3 -w 100 �� ��-c ��ָping�Ĵ��� 3��ָping 3�� ��-w 100
			// ����Ϊ��λָ����ʱ�������ָ��ʱʱ��Ϊ100��
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

	private static String trimSpaces(String IP) {// ȥ��IP�ַ���ǰ�����еĿո�
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	/**
	 * ��󷵻أ�http://192.168.0.100/itravel/
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
	 * ��󷵻أ�192.168.0.100:8080/itravel/·��
	 * 
	 * @param url
	 *            ����·�� ���磺order/second <br>
	 *            ��ǰ�治Ҫ��б��
	 * @return
	 */
	public static String getServletName(String url) {
		return getServletName() + url;
	}

}
