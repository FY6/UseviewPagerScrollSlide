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

	// 页面的数量
	private static final int NUM_PAGES = 5;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);

		// 实例化一个ViewPager和一个PagerAdapter
		mPager = (ViewPager) findViewById(R.id.pager);
		text = (TextView) findViewById(R.id.text);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setPageTransformer(true, new DepthPageTransformer());
		mPager.setOnPageChangeListener(this);
	}

	/**
	 * 处理返回按钮，按下变为在虚拟的Fragment栈中回退。
	 * 
	 * FragmentStatePagerAdapter内部已经实现对每一个Fragment进行管理。
	 * 
	 * 所以我们在实现FragmentStatePagerAdapter是需要把FragmentManager传递给FragmentManager
	 * 
	 * 如果用户已经在第一个页面了，则在Activity的回退栈（backstack）中回退。
	 * 
	 */
	@Override
	public void onBackPressed() {
		// 如果当前是第一个页面，那么就不需要拦截返回按钮，执行默认行为
		if (mPager.getCurrentItem() == 0) {
			super.onBackPressed();
		} else {// 否则每一次都会虚拟的Fragment栈中回退
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		/**
		 * 实现getItem()方法来把ScreenSlidePageFragment实例作为新的页面填充进来
		 * 
		 */
		@Override
		public Fragment getItem(int position) {
			ScreenSlidePageFragment pageFragment = new ScreenSlidePageFragment();
			return pageFragment;
		}

		/**
		 * 返回 Adapter将要创建页面的总数
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
