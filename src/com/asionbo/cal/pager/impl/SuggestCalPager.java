package com.asionbo.cal.pager.impl;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asionbo.cal.MainActivity;
import com.asionbo.cal.R;
import com.asionbo.cal.fragment.LeftMenuFragment;
import com.asionbo.cal.pager.BasePager;

/**
 * 建议页面
 * @author asionbo
 *
 */
public class SuggestCalPager extends BasePager{

	private EditText et_suggest;
	private Button btn_send;
	private TextView tv_back;

	public SuggestCalPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_suggest, null);
		et_suggest = (EditText) view.findViewById(R.id.et_suggest);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		tv_back = (TextView) view.findViewById(R.id.tv_back);
		
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainUI = (MainActivity) mActivity;
				LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
				leftMenuFragment.toggleSlidingMenu();
			}
		});
		
		btn_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = et_suggest.getText().toString();
				Toast.makeText(mActivity, "您提交了："+text, Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}

}
