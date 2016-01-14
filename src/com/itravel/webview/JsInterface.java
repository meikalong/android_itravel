package com.itravel.webview;

import android.webkit.JavascriptInterface;

import com.itravel.fragments.FragmentMine;
import com.itravel.util.ClassManagerUtil;

public class JsInterface {

	@JavascriptInterface
	public void setMineRefresh() {
		FragmentMine fragmentMine = ClassManagerUtil.newInstance(FragmentMine.class);
		fragmentMine.setIsload(true);
	}
}
