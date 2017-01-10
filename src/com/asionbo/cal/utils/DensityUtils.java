package com.asionbo.cal.utils;

import android.content.Context;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2016-12-29 ����9:41:23
 * 
 * dp��pxת��������
 * ========================================================================
 */
public class DensityUtils {

	/**
	 * pxת����dp����
	 * @param ctx	������
	 * @param px	Ҫת�������سߴ�ֵ
	 * @return		����dp
	 */
	public static float px2dp(Context ctx,int px){
		float density = ctx.getResources().getDisplayMetrics().density;//�õ���Ļ�ܶ�
		float dp = px/density;
		return dp;
	}
	
	/**
	 * dpת����px����
	 * @param ctx	�����Ķ���
	 * @param dp	Ҫת���ĳߴ�dp
	 * @return		����int��px
	 */
	public static int dp2px(Context ctx,float dp){
		float density = ctx.getResources().getDisplayMetrics().density;
		int px = (int) (dp*density + 0.5f);//����0.5f��Ϊ����������
		return px;
	}
}
