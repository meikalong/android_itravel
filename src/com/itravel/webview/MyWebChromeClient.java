package com.itravel.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.itravel.R;

public class MyWebChromeClient extends WebChromeClient {

	private Activity activity;
	private boolean ifTitle;

	public MyWebChromeClient(Activity activity) {
		myWebChromeClient(activity, true);
	}

	public MyWebChromeClient(Activity activity, boolean ifTitle) {
		myWebChromeClient(activity, ifTitle);
	}

	private void myWebChromeClient(Activity activity, boolean ifTitle) {
		this.activity = activity;
		this.ifTitle = ifTitle;
	}

	@Override
	public void onReceivedTitle(WebView view, String title) {
		if (!ifTitle) {
			return;
		}
		super.onReceivedTitle(view, title);
		TextView tv = (TextView) activity.findViewById(R.id.title);
		tv.setText(title);
	}

	@Override
	public boolean onJsAlert(WebView view, String url, String message,
			JsResult result) {
		// 这里什么都不要做，也不要接管控制
		return false; // 注意这里一定要返回false，不然js中的代码没有办法往下运行
	}

	@Override
	public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
		AlertDialog.Builder b2 = new AlertDialog.Builder(activity)
				.setTitle("第" + consoleMessage.lineNumber() + "行")
				.setMessage(consoleMessage.message())
				.setPositiveButton("ok", new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		b2.setCancelable(false);
		b2.create();
		b2.show();
		return true;
	}

}
