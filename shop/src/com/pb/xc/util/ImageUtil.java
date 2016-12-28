package com.pb.xc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class ImageUtil {

    private static File file = null;
    /**
     * 读取图像的二进制流
     * 
     * @param infile
     * @return
     */
    public static FileInputStream getByteImage(String infile) {
        FileInputStream inputImage = null;
        file = new File(infile);
        try {
            inputImage = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputImage;
    }
    /**
     * 输出图片
     * @param inputStream
     * @param path
     */
    public static void readBlob(FileInputStream inputStream, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, len);// 写
             }
            inputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 取得文件大小
     * @param f
     * @return
     * @throws Exception
     */
	public static  long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			throw new Exception("文件不存在");
		}
		return s;
	}

	/**
	 * 取得文件夹大小
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception// 取得文件夹大小
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}
	/**
	 * 转换文件或者文件夹大小为可读性数字。
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

}
