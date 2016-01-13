package com.itravel.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.itravel.R;
import com.itravel.activity.WebViewActivity;
import com.itravel.dialog.LoadingDialog;

public class MyWebViewClient extends WebViewClient {
	private Activity activity;

	// 提示信息
	private LoadingDialog dialog;

	// 是否显示在当前页
	private boolean currentActivity;
	private boolean ifDialog;

	public MyWebViewClient(Activity activity, WebView webView) {
		myWebViewClient(activity, false, true, webView);
	}

	public MyWebViewClient(Activity activity, boolean currentActivity,
			WebView webView) {
		myWebViewClient(activity, currentActivity, true, webView);
	}

	public MyWebViewClient(boolean ifDialog, Activity activity, WebView webView) {
		myWebViewClient(activity, false, ifDialog, webView);
	}

	/**
	 * @param activity
	 * @param currentActivity
	 *            是否在当前activity中显示
	 * @param ifDialog
	 *            是否显示对话框
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	private void myWebViewClient(Activity activity, boolean currentActivity,
			boolean ifDialog, WebView webView) {
		this.activity = activity;
		this.currentActivity = currentActivity;
		this.ifDialog = ifDialog;
		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
		setting.setSaveFormData(false);
		setting.setSavePassword(false);
	}

	public boolean shouldOverrideUrlLoading(WebView webView, String url) {

		if (url.contains("iframepage")) {// iframepage里面的页面是iframe引用的页面
			return false;
		}

		if (!currentActivity) {
			Intent intent = new Intent();
			// 第一参数取的是这个应用程序的activity，生命周期是整个应用
			// 第二个参数是要跳转的页面的全路径
			intent.setClassName(activity, WebViewActivity.class.getName());
			// Bundle类用作携带数据，它类似于Map，用于存放key-value名值对形式的值
			Bundle b = new Bundle();
			b.putString("url", url);
			// 此处使用putExtras，接受方就响应的使用getExtra
			intent.putExtras(b);
			activity.startActivity(intent);
			activity.overridePendingTransition(R.anim.myslide_in_right,
					R.anim.myslide_out_left);
		} else {
			webView.loadUrl(url);
		}
		return true;
	}

	// 开始加载网页时要做的工作
	public void onPageStarted(WebView webView, String url, Bitmap favicon) {
		System.out.println("onPageStarted>>>>>>>>>>>>>>>url:>>>" + url);
		showDialog(ifDialog);
	}

	// 加载完成时要做的工作
	public void onPageFinished(WebView view, String url) {
		System.out.println("onPageFinished>>>>>>>>>>>>>>>url:>>>" + url);
		cancleDialog();
	}

	// 加载错误时要做的工作
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		cancleDialog();
		Toast.makeText(activity, errorCode + "：" + description,
				Toast.LENGTH_LONG).show();
	}

	private void showDialog(boolean ifDialog) {
		if (ifDialog) {
			dialog = new LoadingDialog(activity);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();

		}
	}

	private void cancleDialog() {
		if (dialog != null) {
			dialog.cancel();
		}
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
