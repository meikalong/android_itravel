package com.itravel.fragments;

import android.annotation.SuppressLint;
import android.webkit.WebView;

import com.itravel.R;
import com.itravel.util.Global;
import com.itravel.webview.MyWebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class FragmentFind extends MyFragment {

	@Override
	public void onStart() {
		super.onStart();

		if (!isload) {
			return;
		}

		webView = (WebView) getView().findViewById(R.id.webView);
		if (Global.STATE) {
			webView.loadUrl(Global.getServletName("travel/find"));
		} else {
			webView.loadUrl("file:///android_asset/mobileItravel/page/travel/find/index.html");
		}
		webView.setWebViewClient(new MyWebViewClient(getActivity(), webView));

		isload = false;
	}

}
