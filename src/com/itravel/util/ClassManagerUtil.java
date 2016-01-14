package com.itravel.util;

import java.util.HashMap;
import java.util.Map;

public class ClassManagerUtil {

	private static Map<String, Object> map = new HashMap<String, Object>(20);

	@SuppressWarnings("unchecked")
	public synchronized static <T> T newInstance(Class<?> clz) {
		String className = clz.getName();
		Object obj = map.get(className);
		if (obj == null) {
			try {
				obj = clz.newInstance();
				map.put(className, obj);
				return (T) obj;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return (T) obj;
	}

}
