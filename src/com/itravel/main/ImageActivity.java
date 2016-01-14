package com.itravel.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.itravel.R;
import com.itravel.fragments.FragmentFind;
import com.itravel.fragments.FragmentIndex;
import com.itravel.fragments.FragmentMine;
import com.itravel.fragments.FragmentSort;
import com.itravel.util.ClassManagerUtil;
import com.itravel.util.Global;

public class ImageActivity extends Activity implements OnClickListener {
	private ImageView welcomeImg = null;

	private EditText et;
	private Button yanshi;
	private Button kaifa;

	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_activity);

		et = (EditText) findViewById(R.id.ip);
		kaifa = (Button) findViewById(R.id.kaifa);
		yanshi = (Button) findViewById(R.id.yanshi);
		welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);

		AlphaAnimation anima = new AlphaAnimation(1.0f, 1.0f);
		anima.setDuration(3000);// 设置动画显示时间
		anima.setAnimationListener(new AnimationImpl());
		welcomeImg.startAnimation(anima);

		kaifa.setOnClickListener(this);
		yanshi.setOnClickListener(this);

		newInstance();
	}

	private void newInstance() {
		ClassManagerUtil.newInstance(FragmentIndex.class);
		ClassManagerUtil.newInstance(FragmentSort.class);
		ClassManagerUtil.newInstance(FragmentFind.class);
		ClassManagerUtil.newInstance(FragmentMine.class);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.yanshi:
			Global.STATE = false;
			skip();
			break;
		case R.id.kaifa:
			linkIP();
			break;
		}
	}

	private class AnimationImpl implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			welcomeImg.setBackgroundResource(R.drawable.welcome);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// skip(); // 动画结束后跳转到别的页面
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	private void linkIP() {
		final String ip = et.getText().toString();
		if (ip != null && ip.length() > 4) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					Global.STATE = true;
					Global.IP = ip;
					skip();
				}
			});
			t.start();
		} else {
			showToast("请输入正确的地址");
		}
	}

	private void skip() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	private void showToast(String content) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(this, content, Toast.LENGTH_LONG);
		toast.show();
	}

}
