package com.itravel.fragments;

import android.webkit.WebView;

import com.itravel.R;
import com.itravel.util.Global;
import com.itravel.webview.MyWebViewClient;

public class FragmentMine extends MyFragment {

	@Override
	public void onStart() {
		super.onStart();

		if (!isload) {
			return;
		}

		webView = (WebView) getView().findViewById(R.id.webView);
		if (Global.STATE) {
			webView.loadUrl(Global.getServletName("travel/mine"));
		} else {
			webView.loadUrl("file:///android_asset/mobileItravel/page/travel/mine/index.html");
		}
		webView.setWebViewClient(new MyWebViewClient(getActivity(), webView));

		isload = false;
	}

}
