package com.asionbo.cal.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.asionbo.cal.domain.EaringResultData;

public class MathUtils {

	/**
	 * 将double型数据格式化为String型"#0.00"
	 */
	public static String formatData(double data) {
		DecimalFormat myFormat = new DecimalFormat("#0.0000");
		String format = myFormat.format(data);
		return format;
	}

	/**
	 * 计算i值
	 * 
	 * @param isMM2	波高是否是毫米单位
	 * @param isMM1	谷距是否是毫米单位
	 * @param eth	波高
	 * @param etl	谷距
	 * @return i值
	 */
	public static double calFlatness(Context context, String etH, String etL,
			boolean isMM1, boolean isMM2) {

		double eth = Double.parseDouble(etH);
		double etl = Double.parseDouble(etL);
		if (isMM1==false) {//cm
			eth = eth * 10;
		}
		if(isMM2 == false){
			etl = etl * 10;
		}
		
		System.out.println("计算时,eth:" + eth + "----etl:" + etl);

		double result = eth / etl * Math.PI / 2;
		System.out.println("没平方：" + result);
		if (result > 0) {
			double sqrt = result * result * 100;
			 System.out.println("结果："+sqrt);
			return sqrt;
		}
		Toast.makeText(context, "数据有误，请重试", Toast.LENGTH_SHORT).show();
		return 0;
	}

	/**
	 * 计算制耳率
	 * @param bottomList 制耳谷高集合
	 * @param topList    制耳峰高集合
	 * 
	 * @return 返回包含总值、平均值、制耳率的集合
	 */
	public static List<EaringResultData> calEaring(List<Double> topList, List<Double> bottomList) {
		List<EaringResultData> earingData = new ArrayList<EaringResultData>();
		double topTotal = 0;//制耳峰高总值
		double bottomTotal = 0;//制耳谷高总值
		for (int i = 0; i < topList.size(); i++) {
			topTotal += topList.get(i);
//			System.out.println("i："+i+"+++++++++++zhi:"+topList.get(i));
			 
		}
		double topAverage = topTotal/topList.size() ;//计算峰高平均值
		
		System.out.println("总："+topTotal+"----平均值："+topAverage);
		
		for (int i = 0; i < bottomList.size(); i++) {
			bottomTotal += bottomList.get(i); 
		}
		double bottomAverage = bottomTotal/bottomList.size() ;//计算谷高平均值
		
		System.out.println("谷总："+bottomTotal+"----平均值："+bottomAverage);
		
		double earing = 200*(topAverage - bottomAverage)/(topAverage + bottomAverage);
		System.out.println("制耳率："+earing+"%");
		
		earingData.add(new EaringResultData(topTotal, topAverage,
				bottomTotal, bottomAverage, earing));
		
		return earingData;
	}

}
