package com.asionbo.cal.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class ScaleImageUtils {

	public static void scaleImage(final Activity activity,final View view,Bitmap bitmap){
		//获取屏幕高度
		Point point = new Point();
		activity.getWindow().getWindowManager().getDefaultDisplay().getSize(point);
		
		//使用图片的缩放比例计算将要放大的图片高度
		int bitmapScaleHeight = Math.round(bitmap.getHeight()*point.x*1.0f / bitmap.getWidth());
		
		//以屏幕的宽度为基准，如果图片的宽度比屏幕宽，则等比缩小，如果窄，则放大
		final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, point.x, bitmapScaleHeight, false);
		
		view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				//防止图片重复创建，避免申请不必要的存储空间
				if(scaledBitmap.isRecycled())
					return true;
				
				//当ui绘制完毕，我们对图片进行处理
				int viewHeight = view.getMeasuredHeight();
				
				//计算将要裁剪的图片的顶部及底部的偏移量
				int offset = (scaledBitmap.getHeight() - viewHeight)/2;
				
				//对图片以中心进行裁剪
				Bitmap finallyBitmap = Bitmap.createBitmap(scaledBitmap,0,offset,scaledBitmap.getWidth(),
						scaledBitmap.getHeight() - offset * 2);
				
				if(!finallyBitmap.equals(scaledBitmap)){
					scaledBitmap.recycle();//不是原图，就回收
					System.gc();
				}
				
				//设置图片显示
				view.setBackground(new BitmapDrawable(activity.getResources(), finallyBitmap));
				
				return true;
			}
		});
	}
}
