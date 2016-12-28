package com.pb.xc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pb.xc.controller.vo.Message;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;




/**
 * 
 * ProjectName：OnlineExam ClassName：FileOperationUtil Description：文件操作工具类
 * author：Steven Data：2014年9月18日 下午4:40:45 Modifier：Steven Data：2014年9月18日
 * 下午4:40:45 ModifyRemark：
 * 
 * @version
 */
public class FileOperationUtil {
	private List<String> path1 = new ArrayList<String>();// ffmpeg能处理的文件的地址集
	private List<String> path2 = new ArrayList<String>();// ffmpeg不能处理的文件的地址集
	private static ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
	private static String FILE_DIR = "/file/";
//	private static String MENCODER_PATH = ServletActionContext.getServletContext().getRealPath("/") + "/convertvedioutils/mencoder";
	private static String MENCODER_PATH = attr.getRequest().getServletContext().getRealPath("/") + "/convertvedioutils/mencoder";
//	private static String MENCODER_PATH = "E:/Workspaces/TestSpaces/OnlineExam/convertvedioutils/mencoder";
	private static String FFMPEG_PATH = attr.getRequest().getServletContext().getRealPath("/") + "/convertvedioutils/ffmpeg";
//	private static String FFMPEG_PATH = ServletActionContext.getServletContext().getRealPath("/") + "/convertvedioutils/ffmpeg";
//	private static String FFMPEG_PATH = "E:/Workspaces/TestSpaces/OnlineExam/convertvedioutils/ffmpeg";
	
	/**
	 * 
	* @Title: getProperties
	* @Description: 得到src路径下指定名称的Properties文件
	* @param @param fileName
	* @param @return    设定文件
	* @return Properties    返回类型
	 */
	public static Properties getProperties(String fileName) {
		InputStream inputStream = new FileOperationUtil().getClass().getClassLoader().getResourceAsStream(fileName + ".properties");
		Properties p = new Properties();
		
		try{
			p.load(inputStream);
		} catch (IOException e1){
		    e1.printStackTrace();
		}
		
		return p;
	}
	
	/**
	 * 
	 * @Title: saveFileToDisk
	 * @Description: 保存文件到磁盘
	 * @param @param uploadFile 保存的文件
	 * @param @param fileName 文件名
	 * @param @param diskFileName 保存到磁盘的文件名
	 * @param @param ContentType 文件类型
	 * @param @throws Exception 设定文件
	 * @return Message 返回类型
	 */
	public static Message saveFileToDisk(File uploadFile, String fileName, String diskFileName,
			String ContentType) throws Exception {
		Message message = null;

		if (StringContentUtil.isNoEmpty(diskFileName)) {
			message = new Message();

			//HttpSession session = request.getSession();
			//ServletContext application = session.getServletContext();

			String externalName = fileName.substring(fileName.lastIndexOf("."))
					.trim(); // 获取文件扩展名
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
			String savePath = attr.getRequest().getServletContext().getRealPath("/") + FILE_DIR + ContentType + "/";
			// String savePath =
			// ServletActionContext.getServletContext().getRealPath(FILE_DIR +
			// ContentType)+ "/";

			File savefile = new File(new File(savePath), diskFileName
					+ externalName);
			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(uploadFile, savefile);

			Map fileInfo = new HashMap();
			fileInfo.put("fileName", fileName);
			fileInfo.put("filePath", FILE_DIR + ContentType + "/" + diskFileName
					+ externalName);
			fileInfo.put("fileType", ContentType);
			fileInfo.put("savefile", savefile);

			message.setResult(fileInfo);
			message.setSuccess(true);
		}

		return message;
	}

	/**
	 * 
	 * @Title: saveFileToDisk
	 * @Description: 保存文件到磁盘
	 * @param @param request
	 * @param @param fileName 文件名
	 * @param @param filePath 路径
	 * @param @param ContentType 文件类型
	 * @param @throws Exception 设定文件
	 * @return Message 返回类型
	 * @throws
	 */
	public static Message saveFileToDisk(HttpServletRequest request,
			String fileName, String filePath, String ContentType)
			throws Exception {
		Message message = new Message();

		if (request != null) {
			BufferedInputStream bis = new BufferedInputStream(
					request.getInputStream());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			String savePath = attr.getRequest().getServletContext()
					.getRealPath("/") + FILE_DIR + filePath + "/";
			File savefile = new File(savePath + fileName + ContentType);
			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(savefile);
			BufferedOutputStream stream = new BufferedOutputStream(fos);

			try {
				int i = 1024;
				byte[] buf = new byte[i];

				while ((i = bis.read(buf, 0, i)) > 0) {
					bos.write(buf, 0, i);
				}

				stream.write(bos.toByteArray());
				stream.flush();
				fos.flush();
				message.setSuccess(true);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				stream.close();
				fos.close();
				bos.close();
			}
		}

		return message;
	}

	/**
	 * 保存文件到磁盘
	 * @updatetime :2016-6-12
	 * @param inputStream 文件流
	 * @param diskFileName 保存名字
	 * @param ContentType  保存文件类型
	 * @return
     * @throws Exception
     */
	public static Message saveFileToDisk(InputStream inputStream,
			 String diskFileName, String ContentType)
			throws Exception {
		Message message = new Message();

		if (inputStream != null) {
			BufferedInputStream bis = new BufferedInputStream(
					inputStream);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			/*String savePath = attr.getRequest().getServletContext()
					.getRealPath("/") + FILE_DIR + filePath + "/";*/
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			String savePath = attr.getRequest().getServletContext().getRealPath("/") + FILE_DIR + ContentType + "/";
			File savefile = new File(savePath + diskFileName);
			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(savefile);
			BufferedOutputStream stream = new BufferedOutputStream(fos);

			try {
				int i = 1024;
				byte[] buf = new byte[i];

				while ((i = bis.read(buf, 0, i)) > 0) {
					bos.write(buf, 0, i);
				}

				stream.write(bos.toByteArray());
				stream.flush();
				fos.flush();
				message.setSuccess(true);
				message.getResult().put("filePath",FILE_DIR + ContentType + "/"+diskFileName);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				stream.close();
				fos.close();
				bos.close();
			}
		}

		return message;
	}

	/**
	 * 
	 * @Title: deleteFile
	 * @Description: 根据路径删除文件
	 * @param @param path
	 * @param @throws Exception 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean deleteFile(String path) throws Exception {
		boolean result = false;

		if (StringContentUtil.isNoEmpty(path)) {
			/*String savePath = ServletActionContext.getServletContext()
					.getRealPath(FILE_DIR) + path;*/
			File file = new File(path);

			if (file.exists()) {
				result = file.delete();
			}
		}

		return result;
	}
	
	public static boolean deleteFile2(String fPath) throws Exception {
		boolean result = false;

		if (StringContentUtil.isNoEmpty(fPath)) {
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			String path = attr.getRequest().getServletContext()
					.getRealPath("/") + "file/" + fPath;
			File file = new File(path);

			if (file.exists()) {
				File pFile = file.getParentFile();
				result = file.delete();
				
				File[] ls = pFile.listFiles();
				if (ls.length <= 0) {
					result = pFile.delete();
				}
			}
		}

		return result;
	}
	
	public static boolean deleteFile3(String fPath) throws Exception {
		boolean result = false;

		if (StringContentUtil.isNoEmpty(fPath)) {
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			String path = attr.getRequest().getServletContext()
					.getRealPath("/") + fPath;
			File file = new File(path);

			if (file.exists()) {
				File pFile = file.getParentFile();
				result = file.delete();
				
				File[] ls = pFile.listFiles();
				if (ls.length <= 0) {
					result = pFile.delete();
				}
			}
		}

		return result;
	}
	
	public static boolean deleteFile(String ContentType, String fileName) throws Exception {
		boolean result = false;
		
		if (StringContentUtil.isNoEmpty(ContentType) && StringContentUtil.isNoEmpty(fileName)) {
			String path = attr.getRequest().getServletContext()
					.getRealPath("/") + FILE_DIR + ContentType + "/";
			File file = new File(path + fileName);
			
			if (file.exists()) {
				result = file.delete();
			}
		}
		
		return result;
	}
	
	public static boolean deleteByFilePath(String filePath) throws Exception {
		boolean result = false;
		
		if (StringContentUtil.isNoEmpty(filePath)) {
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			String path = attr.getRequest().getServletContext()
					.getRealPath("/") + FILE_DIR + filePath;
			File file = new File(path);
			
			if (file.exists()) {
				result = file.delete();
			}
		}
		
		return result;
	}
	
	public static boolean findFile(String ContentType, String fileName) throws Exception {
		boolean result = false;
		
		if (StringContentUtil.isNoEmpty(ContentType) && StringContentUtil.isNoEmpty(fileName)) {
			String path = attr.getRequest().getServletContext()
					.getRealPath("/") + FILE_DIR + ContentType + "/";
			File file = new File(path + fileName);
			
			if (file.exists()) {
				result = true;
			}
		}
		
		return result;
	}
	
	public static boolean convertVideo(HttpServletRequest request,File uploadFile, String fileName, String diskFileName,String ContentType) throws Exception {
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();

		String externalName = fileName.substring(fileName.lastIndexOf("."))
				.trim(); // 获取文件扩展名
		String savePath = attr.getRequest().getServletContext()
				.getRealPath("/") + "/filedir/temp/";

		String vedioBasePath = attr.getRequest().getServletContext()
				.getRealPath("/") + "/filedir/media/";
		
		/*String otherPath = ServletActionContext.getServletContext()
				.getRealPath("/") + "/filedir/media/other/";*/

		File savefile = new File(new File(savePath), diskFileName
				+ externalName);
		
		if (!savefile.getParentFile().exists()) {
			savefile.getParentFile().mkdirs();
		}
		FileUtils.copyFile(uploadFile, savefile);
		boolean flag = processVedio(savefile, vedioBasePath);
		savefile.delete();
		
		return flag;
	}
	
	public static boolean processVedio(File f, String filePath)  throws Exception {  
		String name = f.getName();
		boolean result = false;
		
		if (name.matches(".+\\.(avi|wmv|mkv|asx|swf|asf|vob|mp3|mp4|mpg|mov|flv)$")) {
			result= pFLV(f, filePath);
			if (result) {
				result= pMP4(f, filePath);
			}
		} else {
			result= pFLV1(f, filePath);
			if (result) {
				result= pMP4(f, filePath);
			}
		}
		
		return result;
    } 
	
	public static String pAVI(File f, String filePath)  throws Exception {
//		String utilPath = "E:/Workspaces/TestSpaces/OnlineExam/convertvedioutils/mencoder";
		String str = f.getPath();
		String name = f.getName();
		List<String> commend = new java.util.ArrayList<String>();  
        commend.add(MENCODER_PATH);  
        commend.add("\"" + str + "\"");  
        commend.add("-oac");
        commend.add("mp3lame");  
        commend.add("-lameopts");  
        commend.add("preset=64");  
        commend.add("-ovc");  
        commend.add("xvid");  
        commend.add("-xvidencopts");  
        commend.add("bitrate=600");  
        commend.add("-of");  
        commend.add("avi");  
        commend.add("-o");  
        String file = name.substring(0, name.lastIndexOf("."));
        String fileName = file + ".avi";
        String saveFilePath = filePath + "flv/" + fileName;
        fileDirIsExit(saveFilePath);
        commend.add("\"" + saveFilePath + "\"");// 最后输出出来的avi，尽量不要出现二义性，否则会覆盖掉原有的视频
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commend);  
            Process p = builder.start();  
            int exitValue = doWaitFor(p);  
            if (exitValue != -1) {  
                return saveFilePath;
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            //System.out.println("********avi转换出错********");  
        }  
        
        return null;
    }
	
	public static boolean pFLV(File f, String filePath)  throws Exception {  
//		String utilPath = "E:/Workspaces/TestSpaces/OnlineExam/convertvedioutils/ffmpeg";
		String str = f.getPath();
		String name = f.getName();
		List<String> commend = new ArrayList<String>();
		commend.add(FFMPEG_PATH);  
        commend.add("-i");  
        commend.add("\"" + str + "\"");  
        commend.add("-ab");  
        commend.add("64");  
        commend.add("-ac");  
        commend.add("2");  
        commend.add("-ar");  
        commend.add("22050");  
        commend.add("-b");  
        commend.add("1000");  
        //commend.add("-r");  
        //commend.add("29.97");
        commend.add("-qscale");
        commend.add("4");
        commend.add("-y");  
        String file = name.substring(0, name.lastIndexOf("."));  
        String fileName = file + ".flv";
        String saveFilePath = filePath + "flv/" + fileName;
        fileDirIsExit(saveFilePath);
        commend.add("\"" + saveFilePath + "\"");
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commend);  
            Process p = builder.start();  
            int exitValue = doWaitFor(p);  
            if (exitValue != -1) {
            	processImg(fileName, filePath);
                return true;
            }  
        } catch (Exception e) {  
            // System.out.println("*********转换为FLV格式出错*********");  
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
	
	public static boolean pFLV1(File f, String filePath) throws Exception {
		String aviPath = pAVI(f, filePath);
		
		if (StringContentUtil.isNoEmpty(aviPath)) {
//			String utilPath = "E:/Workspaces/TestSpaces/OnlineExam/convertvedioutils/ffmpeg";
			String str = aviPath;
			List<String> commend = new ArrayList<String>(); 
	        commend.add(FFMPEG_PATH);  
	        commend.add("-i");  
	        commend.add("\"" + aviPath + "\"");  
	        commend.add("-ab");  
	        commend.add("64");  
	        commend.add("-ac");  
	        commend.add("2");  
	        commend.add("-ar");  
	        commend.add("22050");  
	        commend.add("-b");  
	        commend.add("1500");  
	       // commend.add("-r");  
	        //commend.add("29.97");  
	        commend.add("-qscale");
	        commend.add("4");
	        commend.add("-y");  
	        String file = str.substring(str.lastIndexOf("/") + 1,  
                    str.lastIndexOf("."));
	        String fileName = file + ".flv";
	        String saveFilePath = filePath + "flv/" + fileName;
	        fileDirIsExit(saveFilePath);
	        commend.add("\"" + saveFilePath + "\"");
	        try {  
	            ProcessBuilder builder = new ProcessBuilder();  
	            builder.command(commend);  
	            Process p = builder.start();  
	            int exitValue = doWaitFor(p);
	            if (exitValue != -1) {
	            	processImg(fileName, filePath);
	                return true;
	            }
	        } catch (Exception e) {  
	            //System.out.println("*********转换为FLV格式出错*********");  
	            e.printStackTrace();
	            return false;  
	        } finally {
	        	deleteFile(aviPath);
	        }
		}
		  
		return false;
    }
	
	public static boolean pMP4(File f, String filePath)  throws Exception {
//		String utilPath = "E:/Workspaces/TestSpaces/OnlineExam/convertvedioutils/mencoder";
		String str = f.getPath();
		String name = f.getName();
		List<String> commend = new ArrayList<String>();  
        commend.add(MENCODER_PATH);  
        commend.add("\"" + str + "\"");  
        commend.add("-oac");
        commend.add("faac");
        commend.add("-faacopts");
        commend.add("mpeg=4:object=2:raw:br=12");
        //commend.add("-ofps");
        //commend.add("15");
        //commend.add("-lameopts");  
        //commend.add("preset=64");  
        commend.add("-ovc");  
        commend.add("x264");
        commend.add("-x264encopts");
        commend.add("nocabac:level_idc=30:bframes=0:bitrate=512:threads=auto:turbo=1:global_header:threads=auto:subq=5:frameref=6:partitions=all:trellis=1:chroma_me:me=umh");
        //commend.add("bitrate=440:global_header");
        //commend.add("-xvidencopts");  
       // commend.add("bitrate=600");  
        //commend.add("-vf");
        //commend.add("scale=720:576");
        commend.add("-of");
        commend.add("lavf");
        commend.add("-lavfopts");
        commend.add("format=mp4");
        commend.add("-o");  
        String file = name.substring(0, name.lastIndexOf("."));
        String fileName = file + ".mp4";
        String saveFilePath = filePath + "other/" + fileName;
        fileDirIsExit(saveFilePath);
        commend.add(saveFilePath);// 最后输出出来的avi，尽量不要出现二义性，否则会覆盖掉原有的视频
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commend);  
            Process p = builder.start();  
            int exitValue = doWaitFor(p);  
            if (exitValue != -1) {  
                return true;
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            //System.out.println("********mp4转换出错********");  
        }  
        
        return false;
    }
	
	public static boolean processImg(String fileName, String filePath) {  
        List<String> commend = new java.util.ArrayList<String>();  
        commend.add(FFMPEG_PATH);
        commend.add("-i");  
        commend.add("\"" + fileName + "\"");  
        commend.add("-y");  
        commend.add("-f");  
        commend.add("image2");  
        commend.add("-ss");  
        commend.add("5");  
        commend.add("-t");  
        commend.add("0.001");  
        commend.add("-s");  
        commend.add("320x240");
        String saveFilePath = filePath + "img/" + fileName + ".jpg";
        //commend.add("\"e:\\img\\"+ fileName.substring(10, fileName.lastIndexOf(".")) + ".jpg\"");  
        commend.add(saveFilePath);
        try {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commend);  
            builder.start();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    }
	
	public static int doWaitFor(Process p) throws Exception {
        InputStream in = null;  
        InputStream err = null;  
        int exitValue = -1; // returned to caller when p is finished  
        try {  
            //System.out.println("comeing");  
            in = p.getInputStream();  
            err = p.getErrorStream();  
            boolean finished = false; // Set to true when p is finished  
  
            while (!finished) {  
                try {  
                    while (in.available() > 0) {  
                        // Print the output of our system call  
                        Character c = new Character((char) in.read());  
                        //System.out.print(c);  
                    }  
                    while (err.available() > 0) {  
                        // Print the output of our system call  
                        Character c = new Character((char) err.read());  
                        //System.out.print(c);  
                    }  
  
                    // Ask the process for its exitValue. If the process  
                    // is not finished, an IllegalThreadStateException  
                    // is thrown. If it is finished, we fall through and  
                    // the variable finished is set to true.  
                    exitValue = p.exitValue();  
                    finished = true;  
  
                } catch (IllegalThreadStateException e) {  
                    // Process is not finished yet;  
                    // Sleep a little to save on CPU cycles  
                    Thread.currentThread().sleep(500);  
                }  
            }  
        } catch (Exception e) {  
            // unexpected exception! print it out for debugging...  
            //System.err.println("doWaitFor();: unexpected exception - " + e.getMessage());
        	e.printStackTrace();
        } finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
  
            } catch (IOException e) {
            	e.printStackTrace();
                //System.out.println(e.getMessage());  
            }  
            if (err != null) {  
                try {  
                    err.close();  
                } catch (IOException e) {
                	e.printStackTrace();
                    //System.out.println(e.getMessage());  
                }  
            }  
        }  
        // return completion status to caller  
        return exitValue;  
    }  

	public static void fileDirIsExit(File savefile) throws Exception {
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();
	}
	
	public static void fileDirIsExit(String path) throws Exception {
		File savefile = new File(path);
		if (!savefile.getParentFile().exists())
			savefile.getParentFile().mkdirs();
	}
	
	public static String getContextPath() throws Exception {
		return attr.getRequest().getServletContext().getRealPath("/") + FILE_DIR;
	}

	public static String getContextPath2() throws Exception {
		return attr.getRequest().getServletContext().getRealPath("/");
	}

	public static String getServerPath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + FILE_DIR;
		return basePath;
	}
	
	/**
	  * 将文件转成base64 字符串
	  * @param path文件路径
	  * @return  *
	  * @throws Exception
	  */

	 public static String encodeBase64File(String path) throws Exception {
	  File file = new File(path);;
	  FileInputStream inputFile = new FileInputStream(file);
	  byte[] buffer = new byte[(int) file.length()];
	  inputFile.read(buffer);
	  inputFile.close();
	  return new BASE64Encoder().encode(buffer);

	 }

	 /**
	  * 将base64字符解码保存文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void decoderBase64File(String base64Code, String targetPath)
	   throws Exception {
	  byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();

	 }

	 /**
	  * 将base64字符保存文本文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void toFile(String base64Code, String targetPath)
	   throws Exception {

	  byte[] buffer = base64Code.getBytes();
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();
	 }
}
