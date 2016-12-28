package com.pb.xc.util;

import org.springframework.context.ApplicationContext;

/**
 * <p>
 * 系统常量
 * </p>
 * 
 * @author sywd 2016-1-26
 * 
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_PROJECTID = "sessionProjectId";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_CUSTOMER_USER = "customerUserSessionKey";
	public static final String SESSION_ADMIN_USER = "adminUserSessionKey";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList"; // 当前菜单
	public static final String SESSION_allmenuList = "allmenuList"; // 全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";
	public static final String SESSION_USERROL = "USERROL"; // 用户对象
	public static final String SESSION_USERNAME = "USERNAME"; // 用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do"; // 登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt"; // 系统名称路径
	public static final String PAGE = "admin/config/PAGE.txt"; // 分页条数配置路径
	public static final String FWATERM = "admin/config/FWATERM.txt"; // 文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt"; // 图片水印配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/"; // 图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/"; // 文件上传路径

	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化

}
