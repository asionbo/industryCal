package com.asionbo.cal.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.asionbo.cal.MainActivity;
import com.asionbo.cal.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class LeftMenuFragment extends BaseFragment {

	private ListView listView;
	private String[] leftMenuLists;//侧边栏显示条目数据
	private LeftViewAdapter adapter;
	private int currentPos = 0;//当前默认页面

	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.left_menu_fragment, null);
		
		listView = (ListView) view.findViewById(R.id.list_view);
		
		leftMenuLists = new String[]{"平整度计算","制耳率计算","关于","公式反馈"};
		
		adapter = new LeftViewAdapter();
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				setCurrentPager(position);
				currentPos = position ;
				adapter.notifyDataSetChanged();
				toggleSlidingMenu();
			}
			
		});
		return view;
	}
	
	/**
	 * 切换侧边栏状态
	 */
	public void toggleSlidingMenu() {
		MainActivity mainUI = (MainActivity)mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();//改变状态
	}

	/**
	 * 选择加载页面
	 * @param position
	 */
	protected void setCurrentPager(int position) {
		MainActivity activity = (MainActivity) mActivity;
		ContentFragment contentFragment = activity.getContentFragment();
		
		contentFragment.setCurrentLeftPager(position);
	}

	class LeftViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return leftMenuLists.length;
		}

		@Override
		public Object getItem(int position) {
			return leftMenuLists[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			convertView = View.inflate(mActivity, R.layout.left_menu_item, null);
			TextView tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
			
			tvLeft.setText(leftMenuLists[position]);
			
			if(currentPos == position){
				tvLeft.setEnabled(true);
			}else{
				tvLeft.setEnabled(false);
			}
			
			return convertView;
		}
		
	}

}
