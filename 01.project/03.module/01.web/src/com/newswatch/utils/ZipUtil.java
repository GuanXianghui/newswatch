package com.newswatch.utils;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * zip包工具类
 */
public class ZipUtil {
    /**
     * 压缩目录zip包
     * @param srcDirPath
     * @param zipFile
     */
	public static void compressDir(String srcDirPath, String zipFile) {
		File srcDir = new File(srcDirPath);
		if (!srcDir.exists()){
            throw new RuntimeException(srcDirPath + "不存在！");
        }
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(new File(zipFile));
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcDir);
		//fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
		//fileSet.setExcludes(...); 排除哪些文件或文件夹
		zip.addFileset(fileSet);
		zip.execute();
	}

    /**
     * main函数
     * @param param
     * @throws Exception
     */
    public static void main(String[] param) throws Exception {
        FileUtil.makeDir("C:\\Users\\sky\\Desktop\\1");
        FileUtil.copy("C:\\Users\\sky\\Desktop\\1\\1.zip", "C:\\Users\\sky\\Desktop\\11111.zip");
        ZipUtil.compressDir("D:\\04.my_projects\\realart\\module\\01.web\\sql", "C:\\Users\\sky\\Desktop\\1\\1.zip");
    }
}