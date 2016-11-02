package com.useviewpager.scrollslide;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * �������ڽ��滬��ʱ�����Page Transformerʹҳ����������ɫ��
 * 
 * ��ҳ��Խ�������ģ�����������ԭ��������С����ͼ����
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
		// System.out.println(page + "****" + position);// �Ƴ�ҳ��İٷֱ�[-2,2]

		if (position < -1) {// [������,-1),��Ļ�Ѿ�����Ļ����ߣ��Ѿ�����
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

		} else {// (1,������],��ʱ��page�Ѿ�����Ļ���ұߣ�����
			page.setAlpha(0f);
		}
	}
}
