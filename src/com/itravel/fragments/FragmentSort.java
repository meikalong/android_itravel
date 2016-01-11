package com.itravel.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.itravel.R;
import com.itravel.util.Global;
import com.itravel.webview.MyWebChromeClient;
import com.itravel.webview.MyWebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class FragmentSort extends Fragment {
	private WebView webView;
	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sort, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (flag) {
			return;
		}

		webView = (WebView) getView().findViewById(R.id.webView);
		if (Global.STATE) {
			webView.loadUrl(Global.getServletName("travel/sort"));
		} else {
			Toast.makeText(getActivity(),
					"ע��:����Ŀ����ʾģʽ�£����ֹ��ܲ�������ʹ�ã������ſṦ����ʹ�ÿ���ģʽ", Toast.LENGTH_LONG)
					.show();
			webView.loadUrl("file:///android_asset/mobileItravel/page/travel/sort/index.html");
		}
		webView.setWebViewClient(new MyWebViewClient(getActivity()));
		webView.setWebChromeClient(new MyWebChromeClient(getActivity(), false));
		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setCacheMode(WebSettings.LOAD_NO_CACHE);

		flag = true;
	}
}
