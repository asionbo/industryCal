package com.asionbo.cal.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ========================================================================
 * 
 * author: Asionbo
 * 
 * time: 2016-12-14 ����8:02:14
 * 
 * sharedPreferences�ķ�װ������
 * ========================================================================
 */
public class MPrefUtils {

	public static String NAME = "config";

	public static boolean getBoolean(Context context, String key,
			boolean defaultValue) {

		SharedPreferences sp = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		return sp.getBoolean(key, defaultValue);
	}

	public static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);

		sp.edit().putBoolean(key, value).commit();
	}
	
	public static String getString(Context context,String key,String defValue){
		SharedPreferences sp = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}
	
	public static void setString(Context context,String key ,String value){
		SharedPreferences sp = context.getSharedPreferences(NAME,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}
}
