package com.asionbo.cal.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.asionbo.cal.R;

public class BasePager{

	public Activity mActivity;
	public View mRootView;
	private View view;
	public TextView tvTitle;
	public FrameLayout flContent;
	public BasePager(Activity activity){
		mActivity = activity;
		
		mRootView = initView();
	}
	
	
	public View initView() {
		view = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);
		
		return view;
	}
	
	public void initData(){
		
	}
	
}
