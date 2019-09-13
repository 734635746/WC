package com.lyb.processor;

import com.lyb.bean.result;

import java.io.File;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 12:13
 * 统计文件的字符数
 */
public class NumberOfCharProcessor implements FileProcessor{
    /**
     * 统计
     * @param file 文件全路径名
     * @return
     */
    @Override
    public result disposeFileOf(String fileUrl) {
        //创建一个文件对象
        File file = new File(fileUrl);
        
        if(file.isFile()){

        }

        return null;
    }
}
