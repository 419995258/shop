package com.pb.xc.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.jsp.JspWriter;


public class UiWidget {

	public void importJs(String directory,String projecName, JspWriter out) throws IOException {
		File dir = new File(directory);
		if (dir.isFile()) // 判断是否是文件，如果是文件则返回。
			return;
		File[] files = dir.listFiles(); // 列出当前目录下的所有文件和目录
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				importJs(files[i].getAbsolutePath(),projecName, out);
			}
			if(projecName.indexOf("/")!=-1){
				projecName=projecName.replace("/", "\\");
			}
			String path = files[i].getAbsolutePath();
			String jsPath = path.substring(path.indexOf(projecName)+projecName.length()+1, path.length());
			jsPath = jsPath.replace("\\", "/");
			// 以js结尾
			if (".js".equals(jsPath.substring(jsPath.length() - 3, jsPath
					.length()))) {
				out.println();
				out.println("<script type=\"text/javascript\" src=\"" + jsPath
						+ "\"></script>"); // 输出
			}

		}
	}

	public void importCss(String directory,String projecName,JspWriter out) throws IOException {
		File dir = new File(directory);
		if (dir.isFile()) // 判断是否是文件，如果是文件则返回。
			return;
		File[] files = dir.listFiles(); // 列出当前目录下的所有文件和目录
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				importCss(files[i].getAbsolutePath(),projecName , out);
			}
			if(projecName.indexOf("/")!=-1){
				projecName=projecName.replace("/", "\\");
			}
			String path = files[i].getAbsolutePath();
			String cssPath = path.substring(path.indexOf(projecName)+projecName.length()+1, path
					.length()); // 得到从css文件夹开始的路径
			cssPath = cssPath.replace("\\", "/");
			// 如果以css结尾
			if (".css".equals(cssPath.substring(cssPath.length() - 4, cssPath
					.length()))) {
				out.println();

				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""
						+ cssPath + "\" />");// 输出
			}

		}
	}

	public static void main(String[] args) {

		String[] fieldStrings = { "ztdm" // varchar(32) "主键"
				, "ztmc" // varchar(100) "账套名称"
				, "dm" // varchar(100) "代码"
				, "qysj" // varchar(8) "启用时间"
				, "qyid" // varchar(32) "外键——企业Id"
				, "jlsj" // varchar(8) "建立时间"
				, "isbs" // varchar(1) "是否报税"
				, "kmcsh" // varchar(1) "科目初始化"
				, "bz" // varchar(100) "备注"
				, "kjzd" // varchar(1) "会计制度"
				, "isybnsr" // varchar(1) "是否一般纳税人"
				, "nsdjh" // varchar(12) "纳税登记号"
				, "isnzsds" // varchar(1) "是否内资所得税"
				, "iswzsds" // varchar(1) "是否外资所得税"
				, "isxfs" // varchar(1) "是否消费税"
				, "isgrsds" // varchar(1) "是否个人所得税"
				, "iszzs" // varchar(1) "是否增值税"
				, "sfqy" // varchar(1) "是否启用 Y：启用，N：废弃，D：删除"
				, "qyid " // varchar(32) "外键——企业Id",
				, "qymc " // varchar(100) "企业名称",

		};

		String s = toJsonArray(fieldStrings, 5);
		System.out.println(s);

	}

	private static String toJsonArray(String[] fields, int arrayLength) {
		String s = "[";

		for (int i = 0; i < arrayLength; i++) {
			String c = (i == arrayLength - 1) ? "" : ",";
			s += "{";
			for (int j = 0; j < fields.length; j++) {
				String cc = (j == fields.length - 1) ? "" : ",";
				s += "\"" + fields[j] + "\"" + ":" + "\"" + "20100123" + "\""
						+ cc;
			}
			s += "}" + c;
		}
		s += "]";
		return s;
	}

}
