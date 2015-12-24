package com.itravel.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.itravel.R;
import com.itravel.webview.MyWebViewClient;

public class FragmentIndex extends Fragment {

	private WebView webView;
	// 防止因为生命周期导致页面重复加载
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_index, container, false);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onStart() {
		super.onStart();
		if (flag) {
			return;
		}
		webView = (WebView) getView().findViewById(R.id.webView);
		webView.loadUrl("file:///android_asset/mobileItravel/page/travel/index/index.html");
		webView.setWebViewClient(new MyWebViewClient(getActivity()));

		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);

		flag = true;

	}

	@Override
	public void onPause() {
		System.out.println("index>>>>>>>>>>>>>>>onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		System.out.println("index>>>>>>>>>>>>>>>onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		System.out.println("index>>>>>>>>>>>>>>>onDestroyView");
		super.onDestroyView();
	}

}
