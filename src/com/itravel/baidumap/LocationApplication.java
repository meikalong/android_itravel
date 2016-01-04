package com.itravel.baidumap;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class LocationApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(getApplicationContext());
	}
}
