package com.itravel.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.cgotravel.R;
import com.itravel.fragments.FragmentFind;
import com.itravel.fragments.FragmentIndex;
import com.itravel.fragments.FragmentMine;
import com.itravel.fragments.FragmentSort;
import com.itravel.util.ClassManagerUtil;

public class WelcomeActivity extends Activity {
	private ImageView welcomeImg = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);

		AlphaAnimation anima = new AlphaAnimation(1.0f, 1.0f);
		anima.setDuration(3000);// 设置动画显示时间
		anima.setAnimationListener(new AnimationImpl());
		welcomeImg.startAnimation(anima);

		newInstance();
	}

	private void newInstance() {
		ClassManagerUtil.newInstance(FragmentIndex.class);
		ClassManagerUtil.newInstance(FragmentSort.class);
		ClassManagerUtil.newInstance(FragmentFind.class);
		ClassManagerUtil.newInstance(FragmentMine.class);
	}

	private class AnimationImpl implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			welcomeImg.setBackgroundResource(R.drawable.welcome);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			skip(); // 动画结束后跳转到别的页面
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	private void skip() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}
