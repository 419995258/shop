package com.pb.xc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	/**
	 * 日期格式为{@value},例如:"200408";
	 */
	public static final String DATE_STYLE1 = "yyyyMM";

	/**
	 * 日期格式为{@value},例如:"20040823";
	 */
	public static final String DATE_STYLE2 = "yyyyMMdd";

	/**
	 * 日期格式为"yyyy年MM月dd日 星期X",例如:"2006年05月26日 星期五";
	 */
	public static final String DATE_STYLE3 = "yyyy年MM月dd日 E";

	/**
	 * 日期格式为{@value},例如:"2006-05-26";
	 */
	public static final String DATE_STYLE4 = "yyyy-MM-dd";

	/**
	 * 日期格式为{@value},例如:"2006-05-26 17:11:01";
	 */
	public static final String DATE_STYLE5 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式为{@value},例如:"2006.05.26";
	 */
	public static final String DATE_STYLE6 = "yyyy.MM.dd";


	/**
	 * 所属期起日;
	 */
	public static final String SSSQ_Q = "qq";

	/**
	 * 所属期之日;
	 */
	public static final String SSSQ_Z = "qz";
	
	/**
	 * 日期格式为{@value},例如:"20060526171101";
	 */
	public static final String DATE_STYLE7 = "yyyyMMddHH24miss";
	
	public static final String DATE_STYLE8 = "yyyyMMdd HH:mm:ss";

	public static final String DATE_STYLE9 = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_STYLE10 = "yyyyMMdd HH:mm:ss";

	public static final String DATE_STYLE11 = "yyyy年MM月dd日";
	
	public static final String DATE_STYLE12 = "yyyy年MM月";
	/**
	 * 格式：{@value} ，例如：2016-06
	 */
	public static final String DATE_STYLE13 = "yyyy-MM";

	public static final String DAY = "DAY";

	public static final String MONTH = "MONTH";

	public static final String YEAR = "YEAR";

	public static final String DATE_STYLE14 = "dd";
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * @Title: getCurrentTimeByDb
	 * @Description: 此方法用于获得数据库的当前时间，dateStyle为时间字符串格式
	 * @param @param datePattern
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 * @author baixy
	 * @date Nov 29, 2010 2:19:38 PM
	 */
/*	public static String getCurrentTimeByDb(String dateStyle) throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		Connection conn = DriverManager.getConnection(PropertiesUtil.getProperty("TIME_DB_URL"), 
				PropertiesUtil.getProperty("TIME_DB_USERNAME"), PropertiesUtil.getProperty("TIME_DB_PWD"));
		Statement stmt = null;
		ResultSet rs = null;
		String currentDate = "";
		try {
			stmt = conn.createStatement();
			String sql = "SELECT TO_CHAR(SYSDATE, '" + dateStyle + "') as a FROM dual";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				currentDate = rs.getString("a");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
 		
		return currentDate;
		
	}*/
	
	
	/*public static int getCurrentDay() throws Exception{
		
		return Integer.parseInt(DateUtil.getCurrentTimeByDb("dd")); 
		
	}*/
	
	
	/**
	 * 
	 * @Title: getCurrentYear
	 * @Description: 此方法用于获得当前年
	 * @param  
	 * @return int 
	 * @throws
	 * @author baixy
	 * @date Jun 3, 2010 4:02:52 PM
	 */
	/*public static int getCurrentYear() throws Exception{
		
		return Integer.parseInt(DateUtil.getCurrentTimeByDb("yyyy")); 
		
	}*/

	/**
	 * 
	 * @Title: getCurrentMonth
	 * @Description: 此方法用于获得当前月份 
	 * 	 * @param  
	 * @return int 
	 * @throws
	 * @author baixy
	 * @date Jun 3, 2010 4:43:03 PM
	 */
	/*public static int getCurrentMonth() throws Exception{
		
		return Integer.parseInt(DateUtil.getCurrentTimeByDb("mm")); 
		
	}*/
	
	
	/**
	 * 
	 * @Title: getCurrentTime
	 * @Description: 此方法用于获得当时时间
	 * @param  
	 * @return String 
	 * @throws
	 * @author baixy
	 * @date Jun 13, 2010 11:40:45 AM
	 */
	public static String getCurrentTime() {
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_STYLE5);
		 return dateFormat.format(new Date());
	}
	
	public static Date getCurrentDate(String style) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.CHINA);
		return sdf.parse(getCurrentTime());
	}
	
	/**
	 * 获取当前日期字符串;<br>
	 * 
	 * @param style
	 *            日期样式;目前预定义的样式有:<br>
	 *            {@link #DATE_STYLE1}:日期格式为"yyyyMM";<br>
	 *            {@link #DATE_STYLE2}:日期格式为"yyyyMMdd";<br>
	 *            {@link #DATE_STYLE3}:日期格式为"yyyy年MM月dd日 星期X";<br>
	 *            {@link #DATE_STYLE4}:日期格式为"yyyy-MM-dd";<br>
	 *            {@link #DATE_STYLE5}:日期格式为"yyyy-MM-dd HH:mm:ss";<br>
	 * @return String
	 * @author C.Chen
	 */
	public static String getDateStr(String style) {
		return getDateStr(style, new Date());
	}

	/**
	 * 获取指定日期的字符串日期;<br>
	 * 
	 * @param style
	 *            日期样式;目前预定义的样式有:<br>
	 *            {@link #DATE_STYLE1}:日期格式为"yyyyMM";<br>
	 *            {@link #DATE_STYLE2}:日期格式为"yyyyMMdd";<br>
	 *            {@link #DATE_STYLE3}:日期格式为"yyyy年MM月dd日 星期X";<br>
	 *            {@link #DATE_STYLE4}:日期格式为"yyyy-MM-dd";<br>
	 *            {@link #DATE_STYLE5}:日期格式为"yyyy-MM-dd HH:mm:ss";<br>
	 * @param date
	 *            指定的日期;
	 * @return String
	 * @author C.Chen
	 */
	public static String getDateStr(String style, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间的上月的年、月;<br>
	 * 例如：当前日期为“20070313”，返回日期为“200702”
	 * 
	 * @return String 返回日期格式为"yyyyMM",例如:"200408";
	 * @author C.Chen
	 */
	public static String getLastMonth() {
		StringBuffer buff = new StringBuffer(10);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		// 为某年的一月份时
		if (month == 0) {
			year = year - 1;
			month = 12;
		}
		buff.append(year);
		buff.append((month < 10) ? "0" + month : "" + month);

		return buff.toString();
	}

	/**
	 * 解析指定日期字符串,获得日期类型的对象;
	 * 
	 * @param style
	 *            日期样式;目前预定义的样式有:<br>
	 *            {@link #DATE_STYLE1}:日期格式为"yyyyMM";<br>
	 *            {@link #DATE_STYLE2}:日期格式为"yyyyMMdd";<br>
	 *            {@link #DATE_STYLE3}:日期格式为"yyyy年MM月dd日 星期X";<br>
	 *            {@link #DATE_STYLE4}:日期格式为"yyyy-MM-dd";<br>
	 *            {@link #DATE_STYLE5}:日期格式为"yyyy-MM-dd HH:mm:ss";<br>
	 * @param dateStr
	 *            需要转换的日期字符串;
	 * @return Date
	 * @author C.Chen
	 */
	public static Date parseDate(String style, String dateStr) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.CHINA);
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 计算出某年某月的天数;
	 * 
	 * @param yyyy
	 *            指定的年份，格式为"yyyy";
	 * @param mm
	 *            指定的月份，格式为"MM";
	 * @return int 指定月份的天数;
	 * @author C.Chen
	 */
	public static int getDaysOfMonth(String yyyy, String mm) {
		int year = Integer.parseInt(yyyy);
		int month = Integer.parseInt(mm);

		return getDaysOfMonth(year, month);
	}

	/**
	 * 计算出某年某月的天数;
	 * 
	 * @param year
	 *            指定的年份;
	 * @param month
	 *            指定的月份;
	 * @return int 指定月份的天数;
	 * @author C.Chen
	 */
	public static int getDaysOfMonth(int year, int month) {
		int monthCount = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			monthCount = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			monthCount = 30;
			break;
		case 2:
			if (((year % 4) == 0 && (year % 100) != 0) || (year % 400) == 0) {
				monthCount = 29;
			} else {
				monthCount = 28;
			}
			break;
		}

		return monthCount;
	}

	/**
	 * 获取指定日期之前（一定天数）的日期;
	 * 
	 * @param date
	 *            指定的日期;
	 * @param days
	 *            指定日期之前的天数;
	 * @return Date
	 * @author C.Chen
	 */
	public static Date getBeforeDate(Date date, int days) {
		String tempDate = getDateStr(DATE_STYLE2, date);

		int yyyy = Integer.parseInt(tempDate.substring(0, 4));
		int mm = Integer.parseInt(tempDate.substring(4, 6));
		int dd = Integer.parseInt(tempDate.substring(6, 8));

		dd = dd - days;
		while (dd <= 0) {
			if (mm == 1) {
				yyyy = yyyy - 1;
				mm = 12;
			} else {
				mm = mm - 1;
			}

			dd = dd + getDaysOfMonth(yyyy, mm);
		}

		StringBuffer buff = new StringBuffer(10);
		buff.append(yyyy);
		buff.append((String.valueOf(mm).length() == 1) ? "0" + mm : String.valueOf(mm));
		buff.append((String.valueOf(dd).length() == 1) ? "0" + dd : String.valueOf(dd));

		return parseDate(DATE_STYLE2, buff.toString());
	}

	/**
	 * 
	* @Title: getBeforeDate
	* @Description: 获取指定日期之前一定天数或月份或年份的日期，根据type判断是年或月或日
	* @param  date 日期
	* @param  type DAY MONTH月份 YEAR年份
	* @param  size 之前的天数 月数 年数 如1 2 3等
	* @param  dateTempalte
	* @return String
	 */
	public static String getBeforeDate(Date date, String type, int size, String dateTempalte) {
		Date temp = new Date();//当前日期
		
		if (date != null) {
			temp = date;
		}
			
		SimpleDateFormat sdf = new SimpleDateFormat(dateTempalte);//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		
		switch (type) {
		case "DAY":
			calendar.add(Calendar.DAY_OF_MONTH, -size);//月份减一
			break;
		case "MONTH":
			calendar.add(Calendar.MONTH, -size);//月份
			break;
		case "YEAR":
			calendar.add(Calendar.YEAR, -size);//年份
			break;
	
		default:
			break;
		}
		
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取指定日期之后（一定天数）的日期;
	 * @param dateStyle 日期格式
	 * @param date 日期
	 * @param days 天数
	 */
	public static Date getAfterDate(String dateStyle, Date date, int days) {
		String tempDate = getDateStr(dateStyle, date);

		int yyyy = Integer.parseInt(tempDate.substring(0, 4));
		int mm = Integer.parseInt(tempDate.substring(4, 6));
		int dd = Integer.parseInt(tempDate.substring(6, 8));

		dd = dd + days;
		while (dd > getDaysOfMonth(yyyy, mm)) {
			if (mm == 12) {
				yyyy = yyyy + 1;
				mm = 1;
			} else {
				mm = mm + 1;
			}

			if (mm == 1)
				dd = dd - getDaysOfMonth(yyyy - 1, 12);
			else
				dd = dd - getDaysOfMonth(yyyy, mm - 1);
		}

		StringBuffer buff = new StringBuffer(10);
		buff.append(yyyy);
		buff.append((String.valueOf(mm).length() == 1) ? "0" + mm : String.valueOf(mm));
		buff.append((String.valueOf(dd).length() == 1) ? "0" + dd : String.valueOf(dd));

		return parseDate(dateStyle, buff.toString());
	}

	/**
	 * 
	* @Title: getAfterDate
	* @Description: 获取指定日期之后一定天数或月份或年份的日期，根据type判断是年或月或日
	* @param @param date 日期
	* @param @param type DAY 天，MONTH 月， YEAR 年
	* @param @param size 向后增加的天数、月份数、年数、
	* @param @param dateTempalte 格式
	* @param @return    设定文件
	* @return String    返回类型
	 */
	public static String getAfterDate(Date date, String type, int size, String dateTempalte) {
		Date temp = new Date();//当前日期
		
		if (date != null) {
			temp = date;
		}
			
		SimpleDateFormat sdf = new SimpleDateFormat(dateTempalte);//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		
		switch (type) {
		case "DAY":
			calendar.add(Calendar.DAY_OF_MONTH, size);//月份减一
			break;
		case "MONTH":
			calendar.add(Calendar.MONTH, size);//月份
			break;
		case "YEAR":
			calendar.add(Calendar.YEAR, size);//年份
			break;
	
		default:
			break;
		}
		
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 将日期字符串由8位("yyyyMMdd")转换为10位("yyyy-MM-dd");
	 * 
	 * @param rq
	 *            8位的字符串日期;
	 * @return String 10位的字符串日期,如果转换错误将返回NULL;
	 */
	public static String getRq8To10(String rq) {
		String result = null;
		if (rq.length() == 10) {
			return rq;
		}
		if (rq.length() == 8) {
			result = rq.substring(0, 4) + "-" + rq.substring(4, 6) + "-" + rq.substring(6, 8);
		}
		return result;
	}

	/**
	 * 将日期字符串由10位("yyyy-MM-dd")转换为8位("yyyyMMdd");
	 * 
	 * @param rq
	 *            10位的字符串日期;
	 * @return String 8位的字符串日期,如果转换错误将返回NULL;
	 * @author C.Chen
	 */
	public static String getRq10To8(String rq) {
		String result = null;
		if (rq.length() == 8) {
			return rq;
		}
		if (rq.length() == 10) {
			result = rq.substring(0, 4) + rq.substring(5, 7) + rq.substring(8, 10);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getDayOfMonth
	* @Description: 获得当前月的天数
	* @param @return    设定文件
	* @return int    返回类型
	* @throws
	 */
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/**
	 * 
	* @Title: getDayOfMonth
	* @Description: 获得指定月的天数
	* @param @param dateFormateStr 日期格式
	* @param @param dateStr	日期字符串
	* @param @return
	* @param @throws ParseException    设定文件
	* @return int    返回类型
	 */
	public static int getDayOfMonth(String dateFormateStr, String dateStr) throws ParseException{
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormateStr);
		aCalendar.setTime(simpleDate.parse(dateStr));
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/**
	 * 根据日期取得星期几
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date){
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		} 
		return weeks[week_index];
	}
	
	public static long getCurrentTimestamp(Date date){
		long unixTimestamp = date.getTime()/1000;
		return unixTimestamp;
	}
	/**
	 *计算开始日期到结束日期一共经历多少个月份
	 * @param date1 <String> 较小的日期
	 * @param date2 <String> 较大的日期
	 * @param style 日期样式
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(String date1, String date2,String style)
			throws ParseException {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat(style);

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));

		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		if(year2 == year1){//如果相等的年份，直接用大月份-小月份即可
			result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH) + 1;

			return result;
		}else {
			//如果相差一年，则不需要加上12个相隔的月份
			//比如 2015年 和2013 年中间隔了一年，计算月份相差需要加上2014年的12个月
			int yearc = year2 - year1 - 1;
			//得到中间相差年的月数
			int monthPerYears = yearc * 12;
			//2015-11-28 ,2016-02-01
			//分别计算两个日期距离12月份和1月份相差的月数
			int month1 = 12 - c1.get(Calendar.MONTH) + 1;
			int month2 = c2.get(Calendar.MONTH);
			//得到最终相差的月份
			int monthc = month1 + month2 + monthPerYears;
			return monthc;

		}


	}

	public static void main(String[] args) throws  Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_STYLE13);
		Date d = sdf.parse("2016-01");
		String date = DateUtil.getBeforeDate(d,"MONTH",1, DateUtil.DATE_STYLE13);
		System.out.println("date = " + date);
	}
	
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}
}
