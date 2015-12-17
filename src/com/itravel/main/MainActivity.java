package com.itravel.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.itravel.R;
import com.itravel.fragments.FragmentFind;
import com.itravel.fragments.FragmentIndex;
import com.itravel.fragments.FragmentMine;
import com.itravel.fragments.FragmentSort;

public class MainActivity extends FragmentActivity implements OnClickListener {
	// 定义Fragment页面
	private FragmentIndex fragmentIndex;
	private FragmentSort fragmentSort;
	private FragmentFind fragmentFind;
	private FragmentMine fragmentMine;
	// 定义布局对象
	private FrameLayout indexFl, sortFl, findFl, mineFl;

	// 定义图片组件对象
	private ImageView indexIv, sortIv, findIv, mineIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		initView();

		initData();

		// 初始化默认为选中点击了“动态”按钮
		clickAtBtn();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		// 实例化布局对象
		indexFl = (FrameLayout) findViewById(R.id.layout_index);
		sortFl = (FrameLayout) findViewById(R.id.layout_sort);
		findFl = (FrameLayout) findViewById(R.id.layout_find);
		mineFl = (FrameLayout) findViewById(R.id.layout_mine);

		// 实例化图片组件对象
		indexIv = (ImageView) findViewById(R.id.image_index);
		sortIv = (ImageView) findViewById(R.id.image_sort);
		findIv = (ImageView) findViewById(R.id.image_find);
		mineIv = (ImageView) findViewById(R.id.image_mine);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 给布局对象设置监听
		indexFl.setOnClickListener(this);
		sortFl.setOnClickListener(this);
		findFl.setOnClickListener(this);
		mineFl.setOnClickListener(this);

	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 点击首页按钮
		case R.id.layout_index:
			clickAtBtn();
			break;
		// 点击分类按钮
		case R.id.layout_sort:
			clickAuthBtn();
			break;
		// 点击发现按钮
		case R.id.layout_find:
			clickSpaceBtn();
			break;
		// 点击我的按钮
		case R.id.layout_mine:
			clickMoreBtn();
			break;
		}
	}

	/**
	 * 点击了“动态”按钮
	 */
	private void clickAtBtn() {
		// 实例化Fragment页面
		fragmentIndex = new FragmentIndex();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fragmentIndex);
		// 事务管理提交
		fragmentTransaction.commit();
		// 改变选中状态
		indexFl.setSelected(true);
		indexIv.setSelected(true);

		sortFl.setSelected(false);
		sortIv.setSelected(false);

		findFl.setSelected(false);
		findIv.setSelected(false);

		mineFl.setSelected(false);
		mineIv.setSelected(false);
	}

	/**
	 * 点击了“与我相关”按钮
	 */
	private void clickAuthBtn() {
		// 实例化Fragment页面
		fragmentSort = new FragmentSort();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fragmentSort);
		// 事务管理提交
		fragmentTransaction.commit();

		indexFl.setSelected(false);
		indexIv.setSelected(false);

		sortFl.setSelected(true);
		sortIv.setSelected(true);

		findFl.setSelected(false);
		findIv.setSelected(false);

		mineFl.setSelected(false);
		mineIv.setSelected(false);
	}

	/**
	 * 点击了“我的空间”按钮
	 */
	private void clickSpaceBtn() {
		// 实例化Fragment页面
		fragmentFind = new FragmentFind();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fragmentFind);
		// 事务管理提交
		fragmentTransaction.commit();

		indexFl.setSelected(false);
		indexIv.setSelected(false);

		sortFl.setSelected(false);
		sortIv.setSelected(false);

		findFl.setSelected(true);
		findIv.setSelected(true);

		mineFl.setSelected(false);
		mineIv.setSelected(false);
	}

	/**
	 * 点击了“更多”按钮
	 */
	private void clickMoreBtn() {
		// 实例化Fragment页面
		fragmentMine = new FragmentMine();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fragmentMine);
		// 事务管理提交
		fragmentTransaction.commit();

		indexFl.setSelected(false);
		indexIv.setSelected(false);

		sortFl.setSelected(false);
		sortIv.setSelected(false);

		findFl.setSelected(false);
		findIv.setSelected(false);

		mineFl.setSelected(true);
		mineIv.setSelected(true);
	}

}
