package com.pb.xc.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;

import jxl.Cell;

public class ObjectUtil {
	/**
	 * 
	* @Title: collectionIsEmpty
	* @Description: 判断集合对象是否为空或长度为0
	* @param @param collection
	* @param @throws Exception    设定文件
	* @return boolean    返回类型
	 */
	public static boolean collectionIsEmpty(Collection collection) throws Exception {
		boolean result = true;
		
		if (collection != null && !collection.isEmpty()) {
			result = false;
		}
		
		return result;
	}

	/**
	 * 
	* @Title: arrayIsEmpty
	* @Description: 判断数组是否为空
	* @param @param os
	* @param @throws Exception    设定文件
	* @return boolean    返回类型
	 */
	public static boolean arrayIsEmpty(Object[] os) throws Exception {
		boolean result = true;
		
		if (os != null && os.length > 0) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 
	* @Title: consCellValue
	* @Description: 将Excel单元格内容转换为字符串
	* @param @param cell
	* @param @throws Exception
	* @return String
	 */
	public static String consCellValue(Cell cell) throws Exception {
		return cell.getContents().trim();
	}
	
	/**
	 * 
	 * @Title: convToString 
	 * @Description: 将Long类型转换为Stirng 
	 * @param l
	 * @throws Exception 设定文件 
	 * String 返回类型 
	 * @throws
	 */
	public static String convToString(Long l) throws Exception {
		return String.valueOf(l);
	}
	
	/**
	 * 
	 * @Title: convToString 
	 * @Description: 将Integer类型转换为Stirng
	 * @param i
	 * @throws Exception 设定文件 
	 * String 返回类型 
	 * @throws
	 */
	public static String convToString(Integer i) throws Exception {
		return String.valueOf(i);
	}
	
	/**
	 * 
	 * @Title: convToInteger 
	 * @Description: 将String类型转换为Integer 
	 * @param s
	 * @throws Exception 设定文件 
	 * Integer 返回类型 
	 * @throws
	 */
	public static Integer convToInteger(String s) throws Exception {
		return Integer.valueOf(s);
	}
	
	/**
	 * 
	 * @Title: convToString 
	 * @Description: 将Double类型转换为Stirng 
	 * @param d
	 * @throws Exception 设定文件 
	 * String 返回类型 
	 * @throws
	 */
	public static String convToString(Double d) throws Exception {
		return String.valueOf(d);
	}
	
	/**
	 * 
	 * @Title: convToDouble 
	 * @Description: 将Stirng类型转换为Double 
	 * @param s
	 * @throws Exception 设定文件 
	 * Double 返回类型 
	 * @throws
	 */
	public static Double convToDouble(String s) throws Exception {
		return Double.valueOf(s);
	}
	
	public static String doubleConvString(Double s) throws Exception {
		if (s != null) {
			return String.valueOf(s);
		}
		
		return "";
	}
	
	/**
	 * 四舍五入保留两位小数
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public static Double doubleFormat(double d) throws Exception {
		DecimalFormat df = new DecimalFormat("#0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return Double.valueOf(df.format(d));
	}
	
	public static Double doubleFormat(String d) throws Exception {
		if (StringContentUtil.isNoEmpty(d)) {
			double temp = Double.parseDouble(d);
			DecimalFormat df = new DecimalFormat("#0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			return Double.valueOf(df.format(temp));
		}
		
		return 0.0;
	}
	
	public static String doubleFormatToString(String d) throws Exception {
		if (StringContentUtil.isNoEmpty(d)) {
			double temp = Double.parseDouble(d);
			DecimalFormat df = new DecimalFormat("#0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			
			String tempstr = df.format(temp);
			
			if (!tempstr.equals("0.00")) {
				return tempstr;
			}
		}
		
		return "";
	}
	
	public static Double doubleFormat(String d, String format) throws Exception {
		if (StringContentUtil.isNoEmpty(d)) {
			double temp = Double.parseDouble(d);
			DecimalFormat df = new DecimalFormat("#" + format);
			df.setRoundingMode(RoundingMode.HALF_UP);
			return Double.valueOf(df.format(temp));
		}
		
		return 0.0;
	}
	
	public static Double doubleFormatNoXs(String d) throws Exception {
		if (StringContentUtil.isNoEmpty(d)) {
			double temp = Double.parseDouble(d);
			DecimalFormat df = new DecimalFormat();
			df.setRoundingMode(RoundingMode.HALF_UP);
			return Double.valueOf(df.format(temp));
		}
		
		return 0.0;
	}
	
	public static boolean isNotEmpty(Integer inte) throws Exception {
		boolean result = false;
		
		if (inte != null && inte > 0) {
			result = true;
		}
		
		return result;
	}
}
