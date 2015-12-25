package com.itravel.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.itravel.R;
import com.itravel.util.WebViewUtil;
import com.itravel.webview.MyWebViewClient;

public class FragmentIndex extends Fragment {

	private WebView webView;
	// ��ֹ��Ϊ�������ڵ���ҳ���ظ�����
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return createRefresh(inflater, container);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onStart() {
		super.onStart();
		initWebView();
	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("index>>>>>>>>>>>>>>>onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("index>>>>>>>>>>>>>>>onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("index>>>>>>>>>>>>>>>onDestroyView");
	}

	/**
	 * ��������ˢ��
	 * 
	 * @param inflater
	 * @param container
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private View createRefresh(LayoutInflater inflater, ViewGroup container) {
		final SwipeRefreshLayout swipeLayout;
		View view = inflater.inflate(R.layout.fragment_index, container, false);

		swipeLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.swipe_container);
		swipeLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						// ����ˢ��ҳ��
						webView.loadUrl(webView.getUrl());
					}
				});
		swipeLayout.setColorScheme(R.color.holo_blue_bright,
				R.color.holo_green_light, R.color.holo_orange_light,
				R.color.holo_red_light);

		webView = (WebView) view.findViewById(R.id.webView);

		// ���ý�����
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					// ���ؽ�����
					swipeLayout.setRefreshing(false);
				} else {
					if (!swipeLayout.isRefreshing())
						swipeLayout.setRefreshing(true);
				}

				super.onProgressChanged(view, newProgress);
			}
		});

		return view;

	}

	/**
	 * ��ʼ��webView
	 */
	private void initWebView() {
		if (flag) {
			return;
		}
		webView = (WebView) getView().findViewById(R.id.webView);
		webView.setWebViewClient(new MyWebViewClient(false, getActivity()));
		webView.loadUrl("file:///android_asset/mobileItravel/page/travel/index/index.html");
		WebViewUtil.setJavaScriptEnabled(webView);
		flag = true;
	}

}
