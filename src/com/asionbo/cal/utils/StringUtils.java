package com.asionbo.cal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;

/**
 * ========================================================================
 * 
 * author: asionbo
 * 
 * time: 2017-1-8 下午5:23:30
 * 
 * 拆分字符串为数组工具类
 * ========================================================================
 */
public class StringUtils {

	/**
	 * 根据*拆分
	 * @param et_Data
	 */
	public static List<Double> splitString(Context context,String et_Data) {
		List<Double> topList = new ArrayList<Double>();
		String[] et = et_Data.split("\\*");
		for (int i = 0; i < et.length; i++) {
			try {
				double parseData = Double.parseDouble(et[i]);
				System.out.println("拆分后："+parseData);
				topList.add(parseData);
			} catch (Exception e) {//如果不是double类型，处理异常
//				System.out.println(et[i]+"不是double类型");
				Toast.makeText(context, et[i]+"数据格式错误", Toast.LENGTH_LONG).show();
				break;
			}
		}
		System.out.println("-------------");
		return topList;
	}
	
	
	/**
	 * 对指定字符串进行，去首尾空格、制表符等，然后根据正则表达式替换为指定元素，并去除重复的、首尾的该元素，得到处理后的字符串
	 * @param data 待处理字符串
	 * @param replace 匹配的正则表达式（替换）
	 * @param regex	匹配的正则表达式（去重）
	 * @param target 替换成为的字符
	 * @return	处理后的字符串
	 */
	public static String beforeDealWithString(String data,String replace,String regex,String target){
		String str = data.trim();
		System.out.println("替换前："+str);
		
		String after = StringUtils.replaceStr(str, replace, target);
		System.out.println("替换后:"+after);
		
		String repStr = after.replaceAll(regex, target);
		System.out.println("去重："+repStr);
		
		String middle = repStr;
		
		if(middle.startsWith(target)){
			String str1 = middle.substring(1);
			System.out.println("去头:"+str1);
			middle = str1;
		}
		if(middle.endsWith(target)){
			String str2 = middle.substring(0, middle.length() - 1);
			System.out.println("去尾:"+str2);
			middle = str2;
		}
		
		System.out.println("最终："+middle);
		return middle;
	}
	
	/**
	 * 对字符串，按照相应正则表达式，其中匹配元素被替换为指定元素
	 * @param str	字符串
	 * @param regex	正则表达式
	 * @param target	替换为的指定元素
	 * @return	替换后的字符串
	 */
	public static String replaceStr(String str,String regex,String target){
//		Pattern p = Pattern.compile("[^0-9.]");
//		Matcher m = p.matcher(str);
//		return m.replaceAll("*").trim();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.replaceAll(target).trim();
	}
	
}
