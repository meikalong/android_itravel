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
	// ����Fragmentҳ��
	private FragmentIndex fragmentIndex;
	private FragmentSort fragmentSort;
	private FragmentFind fragmentFind;
	private FragmentMine fragmentMine;
	// ���岼�ֶ���
	private FrameLayout indexFl, sortFl, findFl, mineFl;

	// ����ͼƬ�������
	private ImageView indexIv, sortIv, findIv, mineIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		initView();

		initData();

		// ��ʼ��Ĭ��Ϊѡ�е���ˡ���̬����ť
		clickAtBtn();
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
		// �����ҳ��ť
		case R.id.layout_index:
			clickAtBtn();
			break;
		// ������ఴť
		case R.id.layout_sort:
			clickAuthBtn();
			break;
		// ������ְ�ť
		case R.id.layout_find:
			clickSpaceBtn();
			break;
		// ����ҵİ�ť
		case R.id.layout_mine:
			clickMoreBtn();
			break;
		}
	}

	/**
	 * ����ˡ���̬����ť
	 */
	private void clickAtBtn() {
		// ʵ����Fragmentҳ��
		fragmentIndex = new FragmentIndex();
		// �õ�Fragment���������
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// �滻��ǰ��ҳ��
		fragmentTransaction.replace(R.id.frame_content, fragmentIndex);
		// ��������ύ
		fragmentTransaction.commit();
		// �ı�ѡ��״̬
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
	 * ����ˡ�������ء���ť
	 */
	private void clickAuthBtn() {
		// ʵ����Fragmentҳ��
		fragmentSort = new FragmentSort();
		// �õ�Fragment���������
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// �滻��ǰ��ҳ��
		fragmentTransaction.replace(R.id.frame_content, fragmentSort);
		// ��������ύ
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
	 * ����ˡ��ҵĿռ䡱��ť
	 */
	private void clickSpaceBtn() {
		// ʵ����Fragmentҳ��
		fragmentFind = new FragmentFind();
		// �õ�Fragment���������
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// �滻��ǰ��ҳ��
		fragmentTransaction.replace(R.id.frame_content, fragmentFind);
		// ��������ύ
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
	 * ����ˡ����ࡱ��ť
	 */
	private void clickMoreBtn() {
		// ʵ����Fragmentҳ��
		fragmentMine = new FragmentMine();
		// �õ�Fragment���������
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// �滻��ǰ��ҳ��
		fragmentTransaction.replace(R.id.frame_content, fragmentMine);
		// ��������ύ
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
