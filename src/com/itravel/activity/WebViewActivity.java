package com.itravel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import com.itravel.R;
import com.itravel.webview.MyWebViewClient;

public class WebViewActivity extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_webview);
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();

		webView = (WebView) findViewById(R.id.webView);

		webView.setWebViewClient(new MyWebViewClient(this, true));
		webView.loadUrl(intent.getStringExtra("url"));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
