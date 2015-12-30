package com.itravel.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.itravel.R;
import com.itravel.util.Global;
import com.itravel.webview.MyWebViewClient;

public class FragmentMine extends Fragment {
	private WebView webView;
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_mine, container, false);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onStart() {
		super.onStart();
		if (flag) {
			return;
		}

		webView = (WebView) getView().findViewById(R.id.webView);
		if (Global.STATE) {
			webView.loadUrl(Global.getServletName("mine"));
		} else {
			webView.loadUrl("file:///android_asset/mobileItravel/page/travel/mine/index.html");
		}
		webView.setWebViewClient(new MyWebViewClient(getActivity()));
		webView.getSettings().setJavaScriptEnabled(true);

		flag = true;

	}

}
