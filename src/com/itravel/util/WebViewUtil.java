package com.itravel.util;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewUtil {
	public static void setJavaScriptEnabled(WebView webView) {
		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);
	}
}
