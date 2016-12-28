package com.pb.xc.controller;

import java.beans.PropertyDescriptor;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.pb.xc.controller.vo.UserVo;
import com.pb.xc.util.Const;
import com.pb.xc.util.JsonUtil;


/**
 * 
* <p>Title:BasicController </p>
* <p>Description: 基础控制层(用来抽离一些共性方法)</p>
* <p>Company: </p> 
* @author ycj
* @date 2016年7月19日 下午12:46:47
 */
public class BasicController {
	/*	@Resource
	LogBiz logBiz;*/
    
/*	*//**
	 * 得到PageData
	 *//*
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}*/
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象(经源码研究得出RequestContextHolder底层采用ThreadLocal实现，所以无需担心线程安全问题)
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * 从paramater中获取Bean对象，只支持一层转换，不支持非基础类型及其扩展类型之外的数据转换
	 * 
	 * @param clazz
	 * @return
	 */
	public final <T> T getParaToForm(Class<T> clazz) {
		return getParaToForm(getRequest(),clazz);
	}
	/**
	 * <p>从paramater中获取Bean对象，只支持一层转换，不支持非基础类型及其扩展类型之外的数据转换</p>
	 * @param request
	 * @param clazz
	 */
	public final <T> T getParaToForm(HttpServletRequest request, Class<T> clazz) {
		System.out.println(request.getParameterMap().toString());
		T bean = null;
		try {
			bean = clazz.newInstance();
			PropertyDescriptor[] pds = PropertyUtils
					.getPropertyDescriptors(clazz);
			for (PropertyDescriptor pd : pds) {
				// 判断pd是否可写，如果可以，则判断是否属于基础类型，如果是，则进行处理，如果不是，则跳过
				String name = pd.getName();
				String value = request.getParameter(name);

				if (StringUtils.isNotBlank(value)) {
					Class<?> colClass = pd.getPropertyType();
					if (colClass == char.class || colClass == Character.class) {
						PropertyUtils.setProperty(bean, name, value.charAt(0)); // 字符
					} else if (colClass == byte.class || colClass == Byte.class) {
						PropertyUtils.setProperty(bean, name,
								Byte.valueOf(value)); // 字节
					} else if (colClass == boolean.class
							|| colClass == Boolean.class) {
						PropertyUtils.setProperty(bean, name,
								Boolean.valueOf(value)); // 布尔
					} else if (colClass == int.class
							|| colClass == Integer.class) {
						PropertyUtils.setProperty(bean, name,
								Integer.valueOf(value)); // 整数
					} else if (colClass == long.class || colClass == Long.class) {
						PropertyUtils.setProperty(bean, name,
								Long.valueOf(value)); // 长整数
					} else if (colClass == float.class
							|| colClass == Float.class) {
						PropertyUtils.setProperty(bean, name,
								Float.valueOf(value)); // 单精度浮点
					} else if (colClass == double.class
							|| colClass == Double.class) {
						PropertyUtils.setProperty(bean, name,
								Double.valueOf(value)); // 双精度浮点
					} else if (colClass == String.class) {
						PropertyUtils.setProperty(bean, name, value); // 字符串
					} else if (colClass == Date.class) {
						// 日期
						try {
							PropertyUtils.setProperty(bean, name,
									parseDate(value));
						} catch (Exception e) {
						}
					} else if (colClass == java.sql.Date.class) {
						// 数据库日期
						try {
							PropertyUtils.setProperty(bean, name,
									new java.sql.Date(parseDate(value)
											.getTime()));
						} catch (Exception e) {
						}
					} else if (colClass == Time.class) {
						PropertyUtils.setProperty(bean, name,
								Time.valueOf(value)); // 时间类型
					} else if (colClass == Timestamp.class) {
						PropertyUtils.setProperty(bean, name,
								Timestamp.valueOf(value)); // 时间戳类型
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
    /**
     * <p>处理时间方法</p>
     * @param value
     * @return
     */
	private final Date parseDate(String value) throws ParseException {
		// 将可能存在的日期、时间分割符去除 （.-:）
		value = StringUtils.replaceEach(value, new String[] { " ", ".", ":",
				"-" }, new String[] { "", "", "", "" });
		if (value.length() > 8) {
			return DateUtils.parseDate(value, "yyyyMMddHHmmss");
		} else {
			return DateUtils.parseDate(value, "yyyyMMdd");
		}
	}
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 得到分页列表的信息 
	 */
	public Page getPage(){
		return new Page();
	}
	

	/**
	 * <p>
	 * 设置返回的json结果
	 * </p>
	 * 
	 * @param resp
	 * @param object
	 * @author sywd 2016-1-13
	 */
	public final void setResult(HttpServletResponse resp, Object object) {
		JsonUtil.toJson(object, resp);
	}

	/**
	 * <p>
	 * 包装行为日志对象
	 * </p>
	 * 
	 * @param req
	 * @param function
	 * @param map
	 * @return @author sywd 2016-1-18
	 */
	public void saveLog(String function, Map<String, Object> map) {
		System.out.println(function);
/*		HttpServletRequest req = this.getRequest();
		SysLog logOperation = new SysLog();
		//SysUser user = (SysUser) req.getSession().getAttribute(Const.SESSION_USER);
		logOperation.setFunction(function);
		//logOperation.setOperator(user.getName());
		logOperation.setInputParam(map.toString());
		logOperation.setOperateTime(new Timestamp(System.currentTimeMillis()));
		logBiz.save(logOperation);*/
	}
	
	public UserVo loginedUserInfo(HttpSession session) {
		Object o = session.getAttribute(Const.SESSION_CUSTOMER_USER);
		
		UserVo u = null;
		
		if (o != null) {
			u = (UserVo) o;
		}
		
		return u;
	}
	
	public void conInfoToSession(HttpSession session, String key, Object o) {
		session.setAttribute(key, o);
	}
	
	public void clearSession(HttpSession session, String key) {
		session.removeAttribute(key);
	}
}
