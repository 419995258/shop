package com.pb.xc.util;

import java.text.NumberFormat;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;


public class StringContenUtil {
	/**
	 * 状态-未发布
	 */
	public static final String STATUS_TASK_0 = "0";	//未发布
	/**
	 * 状态-待接受
	 */
	public static final String STATUS_TASK_1 = "1";	//待接受
	/**
	 * 状态-进行中
	 */
	public static final String STATUS_TASK_2 = "2";	//进行中
	/**
	 * 状态-已完成
	 */
	public static final String STATUS_TASK_3 = "3";	//已完成
	/**
	 * 状态-未完成
	 */
	public static final String STATUS_TASK_4 = "4";	//未完成
	
	/**
	 * 发布的求助任务
	 */
	public static final String STATUS_TASK_RELATION_1 = "1";	//发布的求助任务
	
	/**
	 * 接受的任务
	 */
	public static final String STATUS_TASK_RELATION_2 = "2";	//接受的任务
	
	/**
	 * 用户状态-冻结
	 */
	public static final String STATUS_USER_0 = "0";	//冻结
	
	/**
	 * 用户状态-正常
	 */
	public static final String STATUS_USER_1 = "1";	//正常
	
	/**
	 * 投诉类型-1:违法信息
	 */
	public static final String TYPE_COMPLAINT_1 = "1";	// 1:违法信息
	
	/**
	 * 投诉类型-2:虚假信息
	 */
	public static final String TYPE_COMPLAINT_2 = "2";	// 2:虚假信息
	
	/**
	 * 投诉类型-3:恶意评价
	 */
	public static final String TYPE_COMPLAINT_3 = "3";	// 3:恶意评价 
	
	/**
	 * 投诉类型-4:其他
	 */
	public static final String TYPE_COMPLAINT_4 = "4";	// 4:其他
	
	/**
	 * 投诉状态-1:审核中
	 */
	public static final String STATUS_COMPLAINT_1 = "1";	// 1:审核中
	
	/**
	 * 投诉状态-2:已审核
	 */
	public static final String STATUS_COMPLAINT_2 = "2";	// 2：已审核
	
	/**
	 * 投诉状态-3:驳回
	 */
	public static final String STATUS_COMPLAINT_3 = "3";	// 3:驳回
	
//	操作失败	lo1
//	请输入账号	lo2
//	请输入密码	lo3
//	用户名或密码错误	lo4
//	手机号已经注册过	lo5
//	发表评论失败	cm1
//	确认完成任务失败	ti1
//	删除任务失败	ti2
//	修改任务失败	ti3
//	任务还未发布,请刷新任务列表后重新接单	tis0
//	任务已经进行中,请选择其他任务挑战	tis2
//	任务已完成,请选择其他任务挑战		tis3
//	接单路上遇挫折,请刷新任务列表后重新接单		ti4
//	Email账号不匹配,请确认	lo6
//	原始密码错误,请确认	um1
//	两次密码不一致,请确认	um2
	public static final String REGIST_FAIL = "注册失败";
	public static final String REGIST_SUCESS = "注册成功";
	public static final String OPERTION_FAIL_LO1 = "操作失败";
	public static final String REGIST_NO_MOBILE_OR_EMAIL_LO2 = "请输入手机号或者邮箱";
	public static final String REGIST_NO_PASSWORD_LO3 = "请输入密码";
	public static final String USER_OR_PASSWORD_ERROR_LO4 = "用户名或密码错误";
	public static final String REGIST_MOBILE_EXIST_LO5 = "手机号已经注册";
	public static final String USER_NO_LOGINNAME_LO6 = "请输入用户名";
	public static final String REGIST_EMAIL_EXIST = "邮箱已经注册";
	public static final String SAVE_FAIL = "保存异常";
	
	public static final String NEW_PASSWORD_NOT_MATCH_UM1 = "新密码不能与旧密码不一致";
	public static final String PASSWORD_DUPLICATE_UM2 = "新密码与旧密码重复";
	public static final String PASSWORD_NOT_MATCH_UM3 = "密码与错误";
	public static final String NO_NEW_PASSWORD_UM4 = "请设置新密码";
	public static final String NO_CONFIRM_PASSWORD_UM5 = "请设置确认密码";
	
	public static final String EMAIL_NOT_EXIST = "邮箱地址不存在";
	public static final String SEND_EMAIL_FAIL = "发送邮件失败";
	public static final String HEADFOLDER = "headPic/";
	
	//图片保存路径
	public static String UPLOADFOLDER = "C:/temp/";
	public static String PNG_HEADER = "data:image/png;base64,";
	//随机密码长度
	public static int RANDOM_PASSWORD_LENGTH = 6;
	
	/**
	 * 
	* @Title: isEmpty
	* @Description: 判断某字符串是否为空或长度为0或由空白符(whitespace)构成
	* @param @param content
	* @param @return
	* @param @throws Exception    设定文件
	* @return boolean    返回类型	true:为空	false:不为空
	* @throws
	 */
	public static boolean isEmpty(String content) throws Exception {
		return StringUtils.isBlank(content);
	}
	
	/**
	 * 
	* @Title: isNoEmpty
	* @Description: 判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
	* @param @param content 字符串对象
	* @param @return
	* @param @throws Exception    设定文件
	* @return boolean    返回类型	true:不为空 false:为空
	* @throws
	 */
	public static boolean isNoEmpty(String content) throws Exception {
		return StringUtils.isNotBlank(content);
	}
	/***
	 * 
	 * @param value
	 * @param digit
	 * @return String
	 * @throws Exception
	 */
	public static String getSpecifiedNumber(int value,int digit) throws Exception {
		NumberFormat nf=NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		nf.setMaximumIntegerDigits(digit);
		nf.setMinimumIntegerDigits(digit);
		return nf.format(value);
	}
	
	/**
	 * 
	 * @Title: getUuid 
	 * @Description: 获得随机生成的32位uuid 
	 * @throws Exception 设定文件 
	 * String 返回类型 
	 * @throws
	 */
	public static String getUuid() throws Exception {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String convToNull(String str) throws Exception {
		String temp = str;
		
		if (str == null || str.equals("")) {
			temp = null;
		}
		
		return temp;
	}
	
	public static String convToSpace(String str) throws Exception {
		String temp = str;
		
		if (str == null) {
			temp = "";
		}
		
		return temp;
	}
}
