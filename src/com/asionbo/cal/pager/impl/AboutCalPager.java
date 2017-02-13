package com.asionbo.cal.pager.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.asionbo.cal.MainActivity;
import com.asionbo.cal.R;
import com.asionbo.cal.domain.AboutListData;
import com.asionbo.cal.fragment.LeftMenuFragment;
import com.asionbo.cal.pager.BasePager;

/**
 * 关于页面
 * @author asionbo
 *
 */
public class AboutCalPager extends BasePager{

	private TextView tvVersion;
	private ListView lvAbout;
	private String versionName;//版本号
	private ArrayList<AboutListData> aboutDataList;
	private AboutListAdapter adapter;
	private ImageButton ibBack;
	

	public AboutCalPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_about, null);//加载关于页面布局文件
		
		tvVersion = (TextView) view.findViewById(R.id.tv_version);
		lvAbout = (ListView) view.findViewById(R.id.lv_about);
		ibBack = (ImageButton) view.findViewById(R.id.ib_back);
		
		ibBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainUI = (MainActivity) mActivity;
				LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
				leftMenuFragment.toggleSlidingMenu();
			}
		});
		
		aboutDataList = new ArrayList<AboutListData>();
		aboutDataList.add(new AboutListData(R.drawable.ic_account_box_black_36dp, "作者：ASIONBO"));//作者
		aboutDataList.add(new AboutListData(R.drawable.ic_mail_black_36dp, "联系我QQ：1505851022"));//QQ
		aboutDataList.add(new AboutListData(R.drawable.github_b, "源码：点击查看"));//github
		aboutDataList.add(new AboutListData(R.drawable.ic_device_hub_black_36dp, "分享给朋友们~_~"));//分享
		
		tvVersion.setText(getVersionName());//设置程序版本号
		
		adapter = new AboutListAdapter();
		
		lvAbout.setAdapter(adapter);
		
		//点击条目跳转
		lvAbout.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0://作者详情
					Intent myGit = new Intent(Intent.ACTION_VIEW,
							Uri.parse("https://github.com/asionbo/"));
					mActivity.startActivity(myGit);
					break;
				case 1://邮箱联系
					Intent email=new Intent(Intent.ACTION_SEND); 
					email.setType("text/plain");
					email.putExtra(Intent.EXTRA_EMAIL, new String[]{"asionbo@foxmail.com"});
					email.putExtra(Intent.EXTRA_SUBJECT, "您的想法或建议");
					email.putExtra(Intent.EXTRA_TEXT, "软件的完善，离不开您的需求、建议、意见等");
					mActivity.startActivity(Intent.createChooser(email, "Select email application.")); 
					break;
				case 2://获取源码
					Intent intent = new Intent(Intent.ACTION_VIEW,
							Uri.parse("https://github.com/asionbo/industryCal.git"));
					mActivity.startActivity(intent);
					break;
				case 3://分享
					Intent shareIntent = new Intent();  
					shareIntent.setAction(Intent.ACTION_SEND);  
					shareIntent.putExtra(Intent.EXTRA_TEXT, "给你分享一个铝加工用的专业计算器");//注意：这里只是分享文本内容  
					shareIntent.setType("text/plain");  
			        mActivity.startActivity(shareIntent);
					break;
				default:
					break;
				}
			}
		});
		return view;
	}

	/**
	 * 获取程序版本号
	 * @return
	 */
	private String getVersionName(){
		
		PackageManager pm = mActivity.getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(mActivity.getPackageName(), 0);
			versionName = packageInfo.versionName;
			System.out.println("版本号"+versionName);
			
			return versionName;
		} catch (NameNotFoundException e) {
			// 没有找到包名异常
			e.printStackTrace();
		}
		return "";
		
	}
	
	class AboutListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return aboutDataList.size();
		}

		@Override
		public AboutListData getItem(int position) {
			return aboutDataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			convertView = View.inflate(mActivity, R.layout.pager_about_item, null);
			
			ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			TextView tvDec = (TextView) convertView.findViewById(R.id.tv_dec);
			
			AboutListData item = aboutDataList.get(position);
			
			ivIcon.setImageResource(item.icon);
			tvDec.setText(item.text);
			
			return convertView;
		}
	}
	
}
