package com.itravel.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.itravel.R;
import com.itravel.activity.WebViewActivity;
import com.itravel.dialog.LoadingDialog;

public class MyWebViewClient extends WebViewClient {
	private Context context;

	// ��ʾ��Ϣ
	private LoadingDialog dialog;
	// Toast toast;

	// �Ƿ���ʾ�ڵ�ǰҳ
	private boolean currentActivity;

	public MyWebViewClient(Context context) {
		this.context = context;
		this.currentActivity = false;
	}

	public MyWebViewClient(Context context, boolean currentActivity) {
		this.context = context;
		this.currentActivity = currentActivity;
	}

	public boolean shouldOverrideUrlLoading(WebView view, String url) {

		if (url.contains(".html") && !currentActivity) {
			Intent intent = new Intent();
			// ��һ����ȡ�������Ӧ�ó����Context����������������Ӧ��
			// �ڶ���������Ҫ��ת��ҳ���ȫ·��
			intent.setClassName(context, WebViewActivity.class.getName());
			// Bundle������Я�����ݣ���������Map�����ڴ��key-value��ֵ����ʽ��ֵ
			Bundle b = new Bundle();
			b.putString("url", url);
			// �˴�ʹ��putExtras�����ܷ�����Ӧ��ʹ��getExtra
			intent.putExtras(b);
			context.startActivity(intent);
			Activity activity = (Activity) context;
			activity.overridePendingTransition(R.anim.myslide_in_right,
					R.anim.myslide_out_left);
		} else {
			view.loadUrl(url);
		}
		return true;
	}

	// ��ʼ������ҳʱҪ���Ĺ���
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		System.out.println("onPageStarted>>>>>>>>>>>>>>>url:>>>" + url);
		dialog = new LoadingDialog(context);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		// toast = Toast.makeText(context, "loading.......", Toast.LENGTH_LONG);
		// toast.show();
	}

	// �������ʱҪ���Ĺ���
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		dialog.cancel();
		System.out.println("onPageFinished>>>>>>>>>>>>>>>url:>>>" + url);
		// toast.cancel();
		// toast = Toast.makeText(context, "finish.......", Toast.LENGTH_LONG);
		// toast.show();
	}

	// ���ش���ʱҪ���Ĺ���
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		dialog.cancel();
		// toast.cancel();
		Toast.makeText(context, errorCode + "/" + description,
				Toast.LENGTH_LONG).show();
	}

	// �������󣬼��ر��ؾ�̬�ļ���������ر���js�ļ�����css�ļ����ӿ�ҳ������ٶ�
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
