package com.lyb.main;

import com.lyb.Factory.FileProcessorFactory;
import com.lyb.bean.FileResult;
import com.lyb.processor.FileProcessor;

import java.io.IOException;

/**
 * 统计程序的入口
 * @author liuyoubin
 * @date 2019/9/13 - 11:12
 */
public class WC {

    public static void main(String[] args) {

        if(args[0].equals("-s")){//判断是否递归处理


        }else{//不是递归处理，证明只有一个参数 支持 -c -w -l -a
            FileProcessor processor = FileProcessorFactory.getFileProcessorOf(args[0]);
            try {
                FileResult result = (FileResult)processor.disposeFileOf(args[1]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
