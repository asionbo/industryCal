package com.asionbo.cal.fragment;

import java.util.ArrayList;

import android.view.View;
import android.widget.FrameLayout;

import com.asionbo.cal.R;
import com.asionbo.cal.pager.BasePager;
import com.asionbo.cal.pager.impl.AboutCalPager;
import com.asionbo.cal.pager.impl.EaringCalPager;
import com.asionbo.cal.pager.impl.FlatnessCalPager;
import com.asionbo.cal.pager.impl.SuggestCalPager;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-1-3 下午6:07:28
 * 
 * 主页面fragment
 * ========================================================================
 */
public class ContentFragment extends BaseFragment {

	private FrameLayout flContent;

	private ArrayList<BasePager> PagerList;//basepager的集合
	@Override
	public View initView() {

		View view = View.inflate(mActivity, R.layout.content_fragment, null);//此处应该是抽取出来的共性布局base_pager
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);
		return view;
	}

	@Override
	public void initData() {
		super.initData();
		PagerList = new ArrayList<BasePager>();
		
		PagerList.add(new FlatnessCalPager(mActivity));
		PagerList.add(new EaringCalPager(mActivity));
		PagerList.add(new AboutCalPager(mActivity));
		PagerList.add(new SuggestCalPager(mActivity));
		
		setCurrentLeftPager(0);//设置默认页面
	}

	/**
	 * 设置显示侧边栏页面
	 */
	public void setCurrentLeftPager(int position) {
		
		BasePager pager = PagerList.get(position);
		View pagerView = pager.mRootView;
		flContent.removeAllViews();
		flContent.addView(pagerView);
	}
	
}
