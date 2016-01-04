package com.itravel.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.itravel.R;
import com.itravel.fragments.FragmentFind;
import com.itravel.fragments.FragmentIndex;
import com.itravel.fragments.FragmentMine;
import com.itravel.fragments.FragmentSort;

public class MainActivity extends FragmentActivity implements OnClickListener {
	// ����Fragmentҳ��
	private FragmentIndex fragmentIndex = new FragmentIndex();
	private FragmentSort fragmentSort = new FragmentSort();
	private FragmentFind fragmentFind = new FragmentFind();
	private FragmentMine fragmentMine = new FragmentMine();
	private Fragment currentFragment = null;
	// ���岼�ֶ���
	private FrameLayout indexFl, sortFl, findFl, mineFl;

	// ����ͼƬ�������
	private ImageView indexIv, sortIv, findIv, mineIv;

	// ����һ������������ʶ�Ƿ��˳�
	private static boolean isExit = false;

	// �ٶȵ�ͼ
	private BaiduMap baiduMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		baiduMap = new BaiduMap(getApplicationContext());

		initView();

		initData();

		clickIndexBtn();
	}

	@Override
	protected void onStart() {
		super.onStart();
		baiduMap.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		baiduMap.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * ����¼�
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_index:
			clickIndexBtn();
			break;
		case R.id.layout_sort:
			clickSortBtn();
			break;
		case R.id.layout_find:
			clickFindBtn();
			break;
		case R.id.layout_mine:
			clickMineBtn();
			break;
		}
	}

	/**
	 * ��ʼ�����
	 */
	private void initView() {
		// ʵ�������ֶ���
		indexFl = (FrameLayout) findViewById(R.id.layout_index);
		sortFl = (FrameLayout) findViewById(R.id.layout_sort);
		findFl = (FrameLayout) findViewById(R.id.layout_find);
		mineFl = (FrameLayout) findViewById(R.id.layout_mine);

		// ʵ����ͼƬ�������
		indexIv = (ImageView) findViewById(R.id.image_index);
		sortIv = (ImageView) findViewById(R.id.image_sort);
		findIv = (ImageView) findViewById(R.id.image_find);
		mineIv = (ImageView) findViewById(R.id.image_mine);

	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		// �����ֶ������ü���
		indexFl.setOnClickListener(this);
		sortFl.setOnClickListener(this);
		findFl.setOnClickListener(this);
		mineFl.setOnClickListener(this);

	}

	/**
	 * ����ˡ���ҳ����ť
	 */
	private void clickIndexBtn() {
		switchFragment(fragmentIndex);

		changeSelected();
		indexFl.setSelected(true);
		indexIv.setSelected(true);
	}

	/**
	 * ����ˡ����ࡱ��ť
	 */
	private void clickSortBtn() {
		switchFragment(fragmentSort);

		changeSelected();
		sortFl.setSelected(true);
		sortIv.setSelected(true);

	}

	/**
	 * ����ˡ����֡���ť
	 */
	private void clickFindBtn() {
		switchFragment(fragmentFind);

		changeSelected();
		findFl.setSelected(true);
		findIv.setSelected(true);
	}

	/**
	 * ����ˡ��ҵġ���ť
	 */
	private void clickMineBtn() {
		switchFragment(fragmentMine);

		changeSelected();
		mineFl.setSelected(true);
		mineIv.setSelected(true);
	}

	private void changeSelected() {
		indexFl.setSelected(false);
		indexIv.setSelected(false);

		sortFl.setSelected(false);
		sortIv.setSelected(false);

		findFl.setSelected(false);
		findIv.setSelected(false);

		mineFl.setSelected(false);
		mineIv.setSelected(false);
	}

	/**
	 * �л�fragment
	 * 
	 * @param to
	 */
	private void switchFragment(android.support.v4.app.Fragment to) {
		if (currentFragment == to) {
			return;
		}
		// �õ�Fragment���������
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// �滻��ǰ��ҳ��
		if (currentFragment != null) {
			fragmentTransaction.hide(currentFragment);
		}
		if (to.isAdded()) {
			fragmentTransaction.show(to);
		} else {
			fragmentTransaction.add(R.id.frame_content, to);
		}
		// ��������ύ
		fragmentTransaction.commit();
		currentFragment = to;
	}

	/* �˳����� */

	private static Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	private void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�������",
					Toast.LENGTH_SHORT).show();
			// ����handler�ӳٷ��͸���״̬��Ϣ
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

}
