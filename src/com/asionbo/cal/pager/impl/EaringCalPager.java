package com.asionbo.cal.pager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asionbo.cal.MainActivity;
import com.asionbo.cal.R;
import com.asionbo.cal.domain.EaringResultData;
import com.asionbo.cal.fragment.LeftMenuFragment;
import com.asionbo.cal.pager.BasePager;
import com.asionbo.cal.utils.MathUtils;
import com.asionbo.cal.utils.StringUtils;

/**
 * 制耳率计算页面
 * 
 * @author asionbo
 * 
 */
public class EaringCalPager extends BasePager {

	private EditText et_t;// 制耳峰高
	private EditText et_b;// 制耳谷高
	private Button btn_ear;// 计算
	private Button btn_clear;// 清空
	private ListView lv_result;// 显示结果
	private List<EaringResultData> earingData;
	private List<String> list;
	private TextView tv_time;
	private TextView tv_back;

	public EaringCalPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_earing, null);
		et_t = (EditText) view.findViewById(R.id.et_t);
		et_b = (EditText) view.findViewById(R.id.et_b);
		btn_ear = (Button) view.findViewById(R.id.btn_earing);
		btn_clear = (Button) view.findViewById(R.id.btn_clear);
		tv_time = (TextView) view.findViewById(R.id.tv_time);
		lv_result = (ListView) view.findViewById(R.id.lv_result);
		tv_back = (TextView) view.findViewById(R.id.tv_back);
		
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainUI = (MainActivity) mActivity;
				LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
				leftMenuFragment.toggleSlidingMenu();
			}
		});
		
		btn_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				et_t.setText("");
				et_b.setText("");
			}
		});

		btn_ear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				measureEaring();
			}
		});

		return view;
	}

	/**
	 * 计算制耳率
	 */
	protected void measureEaring() {
		// 拿到字符串数据
		String et_T = et_t.getText().toString().trim();
		String et_B = et_b.getText().toString().trim();

		if (TextUtils.isEmpty(et_T) || TextUtils.isEmpty(et_B)) {
			Toast.makeText(mActivity, "数据不能为空", Toast.LENGTH_SHORT).show();
			return;
		}

		// 做分段前处理为用*隔开的double数据
		String b_etT = StringUtils.beforeDealWithString(et_T, "[^0-9.]",
				"[*]+", "*");
		String b_etB = StringUtils.beforeDealWithString(et_B, "[^0-9.]",
				"[*]+", "*");

		// 将字符串拆分成Double类型集合
		List<Double> topList = StringUtils.splitString(mActivity,b_etT);
		List<Double> bottomList = StringUtils.splitString(mActivity,b_etB);
		
//		System.out.println("集合------------------："+topList);

		earingData = MathUtils.calEaring(topList, bottomList);

		if (earingData != null) {
			list = new ArrayList<String>();
			list.add("输入的峰高数据："+topList);
			list.add("总峰高：" + earingData.get(0).getTotalTop() + " mm");
			list.add("平均峰高：" + earingData.get(0).getAverTop() + " mm");
			list.add("输入的谷高数据："+bottomList);
			list.add("总谷高：" + earingData.get(0).getTotalBottom() + " mm");
			list.add("平均谷高：" + earingData.get(0).getAverBottom() + " mm");
			list.add("制耳率："
					+ MathUtils.formatData(earingData.get(0).getEaring()) + "%");
		}

		if (list != null) {
			lv_result.setAdapter(new ResultDataAdapter());
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"此条数据出生于yyyy-MM-dd HH:mm:ss.SSS");
		String time = sdf.format(System.currentTimeMillis());
		tv_time.setText(time);
	}

	class ResultDataAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public String getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(mActivity, R.layout.pager_earing_item,
					null);
			TextView tvResult = (TextView) convertView
					.findViewById(R.id.tv_result);

			String item = list.get(position);
			tvResult.setText(item);
			return convertView;
		}
	}
}
