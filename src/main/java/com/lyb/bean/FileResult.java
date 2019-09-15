package com.lyb.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 11:33
 * 文件处理结果封装类
 */
public class FileResult implements result{
    //文件全路径名称
    private String fileName;
    //文件处理集合
    private List<String> result = new ArrayList<>();

    public FileResult addResult(String fileName, Integer resultNum){
        this.result.add(fileName+" "+resultNum);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void showResult() {
        System.out.println(fileName);
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Override
    public String printfResult() {
        StringBuffer buffer = new StringBuffer();
        for (String s : result) {
            buffer.append(s+"\r\n");
        }
        return buffer.toString();
    }
}
