package com.itravel.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.itravel.dialog.LoadingDialog;

public class MyWebViewClient extends WebViewClient {
	private Context context;

	// ��ʾ��Ϣ
	private LoadingDialog dialog;
	Toast toast;

	public MyWebViewClient(Context context) {
		this.context = context;
	}

	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return super.shouldOverrideUrlLoading(view, url);
	}

	// ��ʼ������ҳʱҪ���Ĺ���
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		dialog = new LoadingDialog(context);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		toast = Toast.makeText(context, "loading.......", Toast.LENGTH_LONG);
		toast.show();
	}

	// �������ʱҪ���Ĺ���
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		dialog.cancel();
		toast.cancel();
		toast = Toast.makeText(context, "finish.......", Toast.LENGTH_LONG);
		toast.show();
	}

	// ���ش���ʱҪ���Ĺ���
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
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
