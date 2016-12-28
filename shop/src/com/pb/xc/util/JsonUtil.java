package com.pb.xc.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <p>
 * json工具类
 * </p>
 * 
 * @author sywd 2015-8-9
 * 
 */
public class JsonUtil {
	/**
	 * <p>
	 * 往页面传送json数据
	 * </p>
	 * 
	 * @param o
	 * @param response
	 * @author sywd 2016-1-18
	 */
	public static void toJson(Object o, HttpServletResponse response) {
		response.setContentType("text/plain;charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(o);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 往页面传送text数据
	 * </p>
	 * 
	 * @param message
	 * @param response
	 * @author sywd 2016-1-18
	 */
	public static void toText(String message, HttpServletResponse response) {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(message);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
