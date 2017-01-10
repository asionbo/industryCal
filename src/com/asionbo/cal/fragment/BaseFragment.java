package com.asionbo.cal.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-1-3 下午5:35:54
 * 
 * fragment基类
 * ========================================================================
 */
public abstract class BaseFragment extends Fragment {

	public Activity mActivity;
	
	/**
	 * 创建
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();//此时activity已经有了，但是还没有画完
	}
	
	/**
	 * 创建布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initView();
	}
	
	/**
	 * 依附的activity创建
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	
	/**
	 * 子类必须实现初始化布局的方法
	 */
	public abstract View initView();
	
	/**
	 * 初始化数据
	 */
	public void initData(){
		
	}
}
