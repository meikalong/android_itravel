package com.itravel.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		initView();

		initData();

		clickIndexBtn();
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

}
