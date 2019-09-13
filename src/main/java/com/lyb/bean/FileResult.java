package com.lyb.bean;

import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 11:33
 * 文件处理结果封装类
 */
public class FileResult implements result{
    //文件全路径名称
    String fileName;
    //文件处理集合
    List<String> result;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
