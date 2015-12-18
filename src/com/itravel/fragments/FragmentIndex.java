package com.itravel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

	@Override
	public void onStart() {
		super.onStart();
		webView = (WebView) getView().findViewById(R.id.webView);
		webView.loadUrl("file:///android_asset/index/index.html");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});

		webView.getSettings().setJavaScriptEnabled(true);
	}

}
