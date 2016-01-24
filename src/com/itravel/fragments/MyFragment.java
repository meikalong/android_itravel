package com.itravel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.itravel.R;
import com.itravel.webview.JsInterface;

public abstract class MyFragment extends Fragment {
	protected WebView webView;
	protected boolean isload = true;
	protected JsInterface jsInterface = new JsInterface();

	public void setIsload(boolean isload) {
		this.isload = isload;
	}

	public JsInterface getJsInterface() {
		return jsInterface;
	}

	public void setJsInterface(JsInterface jsInterface) {
		this.jsInterface = jsInterface;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sort, container, false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		webView.destroy();
	}

}
