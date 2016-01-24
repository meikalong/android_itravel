package com.itravel.fragments;

import android.webkit.WebView;

import com.itravel.R;
import com.itravel.util.Global;
import com.itravel.webview.MyWebChromeClient;
import com.itravel.webview.MyWebViewClient;

public class FragmentSort extends MyFragment {

	@Override
	public void onStart() {
		super.onStart();

		if (!isload) {
			return;
		}

		webView = (WebView) getView().findViewById(R.id.webView);
		webView.loadUrl(Global.getServletUrl("/travel/sort"));
		webView.setWebViewClient(new MyWebViewClient(getActivity(), webView, jsInterface));
		webView.setWebChromeClient(new MyWebChromeClient(getActivity(), false));

		isload = false;
	}

}
