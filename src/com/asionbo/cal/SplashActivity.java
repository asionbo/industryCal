package com.asionbo.cal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.asionbo.cal.R;
import com.asionbo.cal.utils.MPrefUtils;

public class SplashActivity extends Activity {

	private LinearLayout splash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		splash = (LinearLayout) findViewById(R.id.splash);

		// 动画
		setAnimation();
	}

	/**
	 * 设置动画
	 */
	private void setAnimation() {

		// 动画的集合
		AnimationSet set = new AnimationSet(false);

		// 旋转
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		// 缩放
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		// 渐变
		AlphaAnimation alpha = new AlphaAnimation(0.5f, 1f);

		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alpha);

		set.setDuration(1000);
		set.setFillAfter(true);

		// 动画结束，跳转至引导页
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			// 动画结束
			@Override
			public void onAnimationEnd(Animation animation) {
				jumpGuide();// 跳转至引导页
			}
		});

		splash.startAnimation(set);
	}

	/**
	 * 跳转引导页
	 */
	protected void jumpGuide() {

		boolean isGuideShowed = MPrefUtils.getBoolean(this, "is_guide_showed",
				false);
		if (isGuideShowed) {
			// 显示过，直接跳转主页面
			startActivity(new Intent(this, MainActivity.class));
			finish();
		} else {
			// 没显示过，跳转引导页
			startActivity(new Intent(this, GuideActivity.class));
			finish();
		}
	}
}

