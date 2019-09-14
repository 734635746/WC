package com.lyb.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author liuyoubin
 * @date 2019/9/14 - 13:21
 * 文件处理工具类
 */
public class FileUtils {

    /**
     * 递归获取指定目录下的所有文件的url
     * @param file 目录的的File对象
     * @return
     */
    public static List<String> getAllFileUrl(File file) {

        List<String> urlList = new ArrayList<>();

        //获取目录下的所有文件及目录
        File[] listFiles = file.listFiles();

        for (File listFile : listFiles) {
            if(listFile.isFile()){
                urlList.add(listFile.getAbsolutePath());
            }
            if(listFile.isDirectory()){
                urlList.addAll(getAllFileUrl(listFile));
            }
        }

        return urlList;
    }

    /**
     * 递归匹配通配符获取目录下的所有文件的url
     * @param url 目录路径
     * @return
     */
    public static List<String> getAllFileUrlByRegex(String url) {//F:/www/aa/*.txt
        //返回结果集
        List<String> resultList = new ArrayList<>();
        //获取目录
        String dirPath = url.substring(0, url.lastIndexOf(File.separatorChar));
        //获取要匹配的文件名(包含通配符)
        String regFileName = url.substring(url.lastIndexOf(File.separatorChar)+1);
        //模式匹配器
        Pattern p = Pattern.compile(StrToRegex(regFileName));

        //获取目录下所有文件的url
        List<String> allFileUrl = getAllFileUrl(new File(dirPath));
        for (String fileUrl : allFileUrl) {
            //判断文件名是否匹配
            boolean b = p.matcher(fileUrl.substring(fileUrl.lastIndexOf(File.separatorChar)+1)).matches();
            if(b){//匹配
                resultList.add(fileUrl);
            }
        }

        return resultList;
    }

    /**
     * 将含有通配符*、?的文件名转成正则表达式
     * @param regFileName
     * @return
     */
    public static String StrToRegex(String regFileName){
        regFileName = regFileName.replace(".", "#");
        regFileName = regFileName.replaceAll("#", "\\.");
        regFileName = regFileName.replace("*", "#");
        regFileName = regFileName.replaceAll("#", ".*");
        regFileName = regFileName.replace("?", "#");
        regFileName = regFileName.replaceAll("#", ".?");
        return regFileName;
    }
}
