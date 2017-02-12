package com.asionbo.cal;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.asionbo.cal.utils.BitmapUtils;
import com.asionbo.cal.utils.DensityUtils;
import com.asionbo.cal.utils.MPrefUtils;

public class GuideActivity extends Activity {

	private ViewPager vpGuide;
	private List<ImageView> mImageViewList;
	private LinearLayout llPointGroup;
	private int width;// 圆点间距离
	private View viewPointRed;
	private Button btGuide;
	private int[] imageIds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
	}

	/**
	 * 初始化界面
	 */
	@SuppressLint("NewApi")
	private void initView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);

		mImageViewList = new ArrayList<ImageView>();

		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
		viewPointRed = findViewById(R.id.view_point_red);
		btGuide = (Button) findViewById(R.id.bt_guide);

		// 设置跳转至主页面
		btGuide.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MPrefUtils.setBoolean(GuideActivity.this, "is_guide_showed",
						true);
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
				finish();
			}
		});

		imageIds = new int[] { R.drawable.guide1, R.drawable.guide2,
				R.drawable.guide3 };
		// 设置引导页背景
		for (int i = 0; i < imageIds.length; i++) {
			ImageView image = new ImageView(this);
//			image.setBackgroundResource(imageIds[i]);// 添加背景
			Bitmap bm = BitmapUtils.readBitmap(GuideActivity.this, imageIds[i]);
			image.setImageBitmap(bm);
			mImageViewList.add(image);
		}

		// 设置引导页圆点
		for (int i = 0; i < imageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);
			// 获取一个子子部件并设置圆点大小
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10));

			// 设置圆点间隔
			if (i > 0) {
				params.leftMargin = DensityUtils.dp2px(this, 10);
			}
			point.setLayoutParams(params);
			llPointGroup.addView(point);// 将圆点加载给线性布局
		}
		vpGuide.setAdapter(new GuideAdapter());

		vpGuide.setOnPageChangeListener(new GuidePageChangeListener());

		// 获取视图树
		llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					// 当layout结束后，回调此方法
					@Override
					public void onGlobalLayout() {
						llPointGroup.getViewTreeObserver()
								.removeOnGlobalLayoutListener(this);// 执行一次监听
						// 此时layout已经完成
						// 获取两个小圆点之间的距离
						width = llPointGroup.getChildAt(1).getLeft()
								- llPointGroup.getChildAt(0).getLeft();
						System.out.println("宽度：" + width);
					}
				});
	}

	/**
	 * ViewPager适配器
	 * 
	 * @author Asionbo
	 * 
	 */
	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);// 从容器中删除
		}
	}

	/**
	 * 监听页面改变类
	 * 
	 * @author Asionbo
	 * 
	 */
	class GuidePageChangeListener implements OnPageChangeListener {

		/**
		 * 随着滑动这些参数会改变 position 页面角标 positionOffset 滑动距离占页面的百分比
		 * positionOffsetPixels 滑动的距离
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// 获取红点移动的距离
			float deltaX = width * positionOffset + position * width;
			// 获取小红点布局参数，并强转为它的父类布局
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewPointRed
					.getLayoutParams();
			// 通过改变红点margin来移动它
			layoutParams.leftMargin = (int) deltaX;

			// 重新设置布局
			viewPointRed.setLayoutParams(layoutParams);
		}

		/**
		 * 某个页面被选中
		 */
		@Override
		public void onPageSelected(int position) {
			if (position == imageIds.length - 1) {
				btGuide.setVisibility(View.VISIBLE);
			} else {
				btGuide.setVisibility(View.INVISIBLE);
			}
		}

		/**
		 * 滑动状态发生变化
		 */
		@Override
		public void onPageScrollStateChanged(int state) {

		}
	}

}

