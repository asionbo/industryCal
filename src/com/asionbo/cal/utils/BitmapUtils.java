package com.asionbo.cal.utils;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-2-12 下午11:52:20
 * 
 * 节省内存的方式读取本地图片
 * ========================================================================
 */
public class BitmapUtils {

	public static Bitmap readBitmap(Context context,int resId){
		Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		
		//获取图片资源
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
}
