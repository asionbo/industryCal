package com.asionbo.cal;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.asionbo.cal.fragment.ContentFragment;
import com.asionbo.cal.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {

    private static final String LEFT_MENU_FRAGMENT = "left_menu_fragment";
    private static final String CONTENT_FRAGMENT = "content_fragment";
    private long exitTime = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.activity_main_slide);//设置侧边栏空白布局
        
        SlidingMenu slidingMenu = getSlidingMenu();//获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        int widthPixels = getResources().getDisplayMetrics().widthPixels;//获取当前屏幕的px宽度
        slidingMenu.setBehindOffset(widthPixels*3/5);//预留3/5屏幕宽度
        
        initFragment();
    }

    /**
     * 初始化fragment，将数据填充给布局文件
     */
	private void initFragment() {
		
		FragmentManager fragmentManager = getSupportFragmentManager();//获取fragment管理者
		FragmentTransaction transaction = fragmentManager.beginTransaction();//开启事务
		
		transaction.replace(R.id.fl_slide, new LeftMenuFragment(), LEFT_MENU_FRAGMENT);
		transaction.replace(R.id.fl_content, new ContentFragment(), CONTENT_FRAGMENT);
		
		transaction.commit();
	}

	/**
	 * 获取ContentFragment
	 */
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm.findFragmentByTag(CONTENT_FRAGMENT);
		return fragment;
	}

	/**
	 * 获取LeftMenuFragment
	 */
	public LeftMenuFragment getLeftMenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(LEFT_MENU_FRAGMENT);
		return fragment;
	}
	
	/**
	 * 按两次返回键退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			if((System.currentTimeMillis() - exitTime) > 2000){
				//间隔大于2s,提示再按一次
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			}else{
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 获取状态栏高度
	 * @return
	 */
	public int getStatusBarHeight() {
	    int result = -1;
	    int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
	    if (resourceId > 0) {
	        result = this.getResources().getDimensionPixelSize(resourceId);
	    }
	    return result;
	} 

}
