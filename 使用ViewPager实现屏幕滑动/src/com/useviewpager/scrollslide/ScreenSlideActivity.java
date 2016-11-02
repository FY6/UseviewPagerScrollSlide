package com.useviewpager.scrollslide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

public class ScreenSlideActivity extends FragmentActivity implements
		ViewPager.OnPageChangeListener {

	// ҳ�������
	private static final int NUM_PAGES = 5;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);

		// ʵ����һ��ViewPager��һ��PagerAdapter
		mPager = (ViewPager) findViewById(R.id.pager);
		text = (TextView) findViewById(R.id.text);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setPageTransformer(true, new DepthPageTransformer());
		mPager.setOnPageChangeListener(this);
	}

	/**
	 * �����ذ�ť�����±�Ϊ�������Fragmentջ�л��ˡ�
	 * 
	 * FragmentStatePagerAdapter�ڲ��Ѿ�ʵ�ֶ�ÿһ��Fragment���й���
	 * 
	 * ����������ʵ��FragmentStatePagerAdapter����Ҫ��FragmentManager���ݸ�FragmentManager
	 * 
	 * ����û��Ѿ��ڵ�һ��ҳ���ˣ�����Activity�Ļ���ջ��backstack���л��ˡ�
	 * 
	 */
	@Override
	public void onBackPressed() {
		// �����ǰ�ǵ�һ��ҳ�棬��ô�Ͳ���Ҫ���ط��ذ�ť��ִ��Ĭ����Ϊ
		if (mPager.getCurrentItem() == 0) {
			super.onBackPressed();
		} else {// ����ÿһ�ζ��������Fragmentջ�л���
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		/**
		 * ʵ��getItem()��������ScreenSlidePageFragmentʵ����Ϊ�µ�ҳ��������
		 * 
		 */
		@Override
		public Fragment getItem(int position) {
			ScreenSlidePageFragment pageFragment = new ScreenSlidePageFragment();
			return pageFragment;
		}

		/**
		 * ���� Adapter��Ҫ����ҳ�������
		 */
		@Override
		public int getCount() {
			return NUM_PAGES;
		}

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		text.setText("Step:" + (position + 1));
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}
}
