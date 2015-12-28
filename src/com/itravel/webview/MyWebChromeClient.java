package com.itravel.webview;

import android.app.Activity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.itravel.R;

public class MyWebChromeClient extends WebChromeClient {

	private Activity activity;

	public MyWebChromeClient(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onReceivedTitle(WebView view, String title) {
		super.onReceivedTitle(view, title);
		TextView tv = (TextView) activity.findViewById(R.id.title);
		tv.setText(title);
	}
}
