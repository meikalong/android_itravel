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
		welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);
		AlphaAnimation anima = new AlphaAnimation(1.0f, 1.0f);
		anima.setDuration(3000);// 设置动画显示时间
		welcomeImg.startAnimation(anima);
		anima.setAnimationListener(new AnimationImpl());
		et = (EditText) findViewById(R.id.ip);
		kaifa = (Button) findViewById(R.id.kaifa);
		yanshi = (Button) findViewById(R.id.yanshi);
		kaifa.setOnClickListener(this);
		yanshi.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.yanshi:
			Global.STATE = false;
			skip();
			break;
		case R.id.kaifa:
			checkIP();
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

	private void checkIP() {
		final String ip = et.getText().toString();
		if (ip != null && ip.length() > 4) {
			if (Global.isIp(ip)) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						Global.STATE = true;
						Global.IP = ip;
						skip();
					}
				});
				t.start();
			}
		} else {
			showToast("IP地址不正确或者Ping不通");
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
