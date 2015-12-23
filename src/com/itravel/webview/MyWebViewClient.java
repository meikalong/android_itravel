package com.itravel.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.itravel.dialog.LoadingDialog;

public class MyWebViewClient extends WebViewClient {
	private Context context;

	// 提示信息
	private LoadingDialog dialog;
	Toast toast;

	public MyWebViewClient(Context context) {
		this.context = context;
	}

	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return super.shouldOverrideUrlLoading(view, url);
	}

	// 开始加载网页时要做的工作
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		dialog = new LoadingDialog(context);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		toast = Toast.makeText(context, "loading.......", Toast.LENGTH_LONG);
		toast.show();
	}

	// 加载完成时要做的工作
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		dialog.cancel();
		toast.cancel();
		toast = Toast.makeText(context, "finish.......", Toast.LENGTH_LONG);
		toast.show();
	}

	// 加载错误时要做的工作
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		Toast.makeText(context, errorCode + "/" + description,
				Toast.LENGTH_LONG).show();
	}

	// 拦截请求，加载本地静态文件，例如加载本地js文件或者css文件，加快页面加载速度
	// @Override
	// public WebResourceResponse shouldInterceptRequest(WebView view,
	// String url) {
	// WebResourceResponse response = null;
	// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	// response = super.shouldInterceptRequest(view, url);
	// if (url.contains("icon.png")) {
	// try {
	// response = new WebResourceResponse("image/png",
	// "UTF-8", getResources().getAssets().open(
	// "icon.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// return response;
	// }

}
