package com.lyb.Factory;

import com.lyb.processor.*;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 11:42
 * 文件处理的工厂类，用于通过接收的参数放回对应的文件处理类
 */
public class FileProcessorFactory {

    public static FileProcessor getFileProcessorOf(String param){
        //将参数转成小写
        param = param.toLowerCase();

        switch (param){
            case "-c":return new NumberOfCharProcessor();
            case "-l":return new LineProcessor();
            case "-w":return new WordProcessor();
            case "-a":return  new ComplexProcessor();
        }
        return null;
    }

}
