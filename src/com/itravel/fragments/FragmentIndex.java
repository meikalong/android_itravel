package com.itravel.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.itravel.R;

public class FragmentIndex extends Fragment {

	private WebView webView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_index, container, false);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onStart() {
		super.onStart();
		webView = (WebView) getView().findViewById(R.id.webView);
		webView.loadUrl("file:///android_asset/mobileItravel/page/travel/index/index.html");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}

			// 拦截请求，加载本地静态文件，例如加载本地js文件或者css文件，加快页面加载速度
			// @Override
			// public WebResourceResponse shouldInterceptRequest(WebView view,
			// String url) {
			// WebResourceResponse response = null;
			// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// response = super.shouldInterceptRequest(view, url);
			// if (url.contains("icon.png")) {
			// try {
			// response = new WebResourceResponse("image/png",
			// "UTF-8", getResources().getAssets().open(
			// "icon.png"));
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
			// }
			// return response;
			// }

		});

		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);

	}
}
