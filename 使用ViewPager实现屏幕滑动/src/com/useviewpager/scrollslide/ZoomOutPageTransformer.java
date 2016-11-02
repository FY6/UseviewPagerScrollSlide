package com.useviewpager.scrollslide;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * 当在相邻界面滑动时，这个Page Transformer使页面收缩并褪色。
 * 
 * 当页面越靠近中心，它将渐渐还原到正常大小并且图像渐入
 * 
 * @author wfy
 * 
 */
public class ZoomOutPageTransformer implements PageTransformer {

	private static final float MIN_SCALE = 0.85f;
	private static final float MIN_ALPHA = 0.5f;

	@Override
	public void transformPage(View page, float position) {
		int pageWidth = page.getWidth();
		int pageHeight = page.getHeight();
		// System.out.println(page + "****" + position);// 移出页面的百分比[-2,2]

		if (position < -1) {// [负无穷,-1),屏幕已经在屏幕的左边，已经隐藏
			page.setAlpha(0f);
		} else if (position <= 1) {// [-1,1]

			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;

			if (position < 0) {
				page.setTranslationX(horzMargin - vertMargin / 2);
			} else {
				page.setTranslationX(-horzMargin + vertMargin / 2);
			}

			page.setScaleX(scaleFactor);
			page.setScaleY(scaleFactor);

			page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
					/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));

		} else {// (1,正无穷],这时候page已经在屏幕的右边，隐藏
			page.setAlpha(0f);
		}
	}
}
