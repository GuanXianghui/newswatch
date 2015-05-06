package com.newswatch.utils;

import java.io.*;

/**
 * 文件工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-1 11:33
 */
public class FileUtil {
    /**
     * 缓存大小
     */
    private static final int BUFFER_SIZE = 1444;

    /**
     * 拷贝文件
     * @param src
     * @param dst
     */
    public static void copy(String src, String dst) {
        try {
            int byteRead;
            if (new File(src).exists()) { //文件存在时
                InputStream inStream = new FileInputStream(src); //读入原文件
                FileOutputStream fs = new FileOutputStream(dst);
                byte[] buffer = new byte[BUFFER_SIZE];
                while ( (byteRead = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteRead);
                }
                fs.flush();
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 写文件
     * @param filePath
     * @param content
     * @throws Exception
     */
    public static void writeFile(String filePath, String content) throws Exception {
        FileOutputStream outSTr = new FileOutputStream(new File(filePath));
        BufferedOutputStream Buff=new BufferedOutputStream(outSTr);
        Buff.write(content.getBytes());
        Buff.flush();
        Buff.close();
    }

    /**
     * 拷贝文件
     * @param src
     * @param dst
     */
    public static void copy(File src, File dst) {
        try {
            int byteRead;
            if (src.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(src); //读入原文件
                FileOutputStream fs = new FileOutputStream(dst);
                byte[] buffer = new byte[BUFFER_SIZE];
                while ( (byteRead = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteRead);
                }
                fs.flush();
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 创建目录
     * @param path
     */
    public static void makeDir(String path)
    {
        File file = new File(path);

        if (!file.exists())
        {
            if (!file.mkdirs())
            {
                throw new RuntimeException("尝试创建文件夹:[" + path + "]失败！");
            }
        }
    }

    /**
     * 删除文件或者文件夹
     * @param path
     */
    public static void deleteFile(String path){
        File file = new File(path);
        deleteFile(file);
    }

    /**
     * 删除文件或者文件夹
     * @param file
     */
    public static void deleteFile(File file){
        if (file.exists())
        {
            file.delete();
        }
    }
}
