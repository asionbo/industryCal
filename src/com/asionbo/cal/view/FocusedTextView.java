package com.asionbo.cal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;
/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-1-8 下午4:09:39
 * 
 * 获取焦点的TextView
 * ========================================================================
 */
public class FocusedTextView extends TextView{

	public FocusedTextView(Context context) {
		super(context);
	}

	public FocusedTextView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public FocusedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	//强制获取焦点
	@Override
	@ExportedProperty(category = "focus")
	public boolean isFocused() {
		return true;
	}

}
