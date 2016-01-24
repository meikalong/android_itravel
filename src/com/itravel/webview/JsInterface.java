package com.itravel.webview;

import android.webkit.JavascriptInterface;

import com.itravel.fragments.FragmentMine;
import com.itravel.util.ClassManagerUtil;

public class JsInterface {
	private PageReload pageReload;

	@JavascriptInterface
	public void pageReload() {
		if (pageReload != null) {
			pageReload.pageReload();
		}
	}

	@JavascriptInterface
	public void setMineRefresh() {
		FragmentMine fragmentMine = ClassManagerUtil.newInstance(FragmentMine.class);
		fragmentMine.setIsload(true);
	}

	public interface PageReload {
		void pageReload();
	}

	public void setPageReloadClickListener(PageReload pageReload) {
		this.pageReload = pageReload;

	}

}