package com.asionbo.cal.pager.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asionbo.cal.MainActivity;
import com.asionbo.cal.R;
import com.asionbo.cal.fragment.LeftMenuFragment;
import com.asionbo.cal.pager.BasePager;
import com.asionbo.cal.utils.MathUtils;

/**
 * 平整度计算页面
 * 
 * @author asionbo
 * 
 */
public class FlatnessCalPager extends BasePager {

	private EditText et_h;// 波高
	private EditText et_l;// 谷距
	private Spinner sp_m1;// 第一个单位
	private Spinner sp_m2;// 第二个单位
	private Button btn_get;// 计算
	private Button btn_clear;//清空
	private TextView tv_result;// 显示结果
	private TextView tv_back;//切换侧边栏
	private String etH;
	private String etL;
	
	private boolean isMM1 = true;//默认选择mm
	private boolean isMM2 = true;

	public FlatnessCalPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_flatness, null);
		et_h = (EditText) view.findViewById(R.id.et_h);
		et_l = (EditText) view.findViewById(R.id.et_l);
		sp_m1 = (Spinner) view.findViewById(R.id.sp_m1);
		sp_m2 = (Spinner) view.findViewById(R.id.sp_m2);
		btn_get = (Button) view.findViewById(R.id.btn_get);
		btn_clear = (Button) view.findViewById(R.id.btn_clear);
		tv_back = (TextView) view.findViewById(R.id.tv_back);
		tv_result = (TextView) view.findViewById(R.id.tv_result);
		
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainUI = (MainActivity) mActivity;
				LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
				leftMenuFragment.toggleSlidingMenu();
			}
		});

		sp_m1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("position:" + position + "-----" + "id:"
						+ id);
				switch (position) {
				case 0:// mm,默认设置为mm，数据不作处理
					isMM1 = true;
					break;
				case 1:// cm
					isMM1 = false;
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				System.out.println("什么也没点");
			}

		});
		
		sp_m2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:// mm,默认设置为mm，数据不作处理
					isMM2 = true;
					break;
				case 1:// cm
					isMM2 = false;
					break;
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		

		btn_get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				measureI();
				// 如何在这里，点击后，隐藏输入法键盘
			}
		});
		
		btn_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et_h.setText("");
				et_l.setText("");
			}
		});

		return view;
	}

	/**
	 * 计算平整度i值
	 */
	protected void measureI() {
		// 拿到数据
		etH = et_h.getText().toString().trim();
		etL = et_l.getText().toString().trim();

		if (TextUtils.isEmpty(etH) || TextUtils.isEmpty(etL)) {
			Toast.makeText(mActivity, "数据不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		System.out.println("输入时：etH:" + etH + "----etL:" + etL);

		//计算
		double result = MathUtils.calFlatness(mActivity, etH, etL,isMM1,isMM2);

		String str = MathUtils.formatData(result);// 格式化为"0.0000"

		if (result <= 0) {
			tv_result.setText("波高小于等于零了，您板材放反了吧？");
		} else if (result < 5) {
			tv_result.setText("i值为："+str + "，很棒哦！");
		} else if (result > 5 && result < 30) {
			tv_result.setText("i值为："+str + "，还可以，一般般啊");
		} else if (result > 30) {
			tv_result.setText("i值为："+str + "，很强，不平度冲破天际");
		}
	}

}
