package com.lyb.main;

import com.lyb.Factory.FileProcessorFactory;
import com.lyb.bean.ErrorResult;
import com.lyb.bean.result;
import com.lyb.enums.ErrorTypeEnum;
import com.lyb.processor.FileProcessor;
import com.lyb.processor.RecursiveProcessor;
import com.lyb.window;

import java.io.IOException;
import java.util.List;

/**
 * 统计程序的入口
 * @author liuyoubin
 * @date 2019/9/13 - 11:12
 */
public class WC {

    public static void main(String[] args) {

            if(args[0].equals("-x")){//使用图形界面

                window.main(null);

            }else if(args[0].equals("-s")){//判断是否递归处理 -s -a/-l/-w/-c *.txt
                //获取相应的文件处理对象
                FileProcessor processor = FileProcessorFactory.getFileProcessorOf(args[1]);
                //递归处理对象
                RecursiveProcessor recursiveProcessor = new RecursiveProcessor(processor, args[2]);
                //获取处理的结果集
                try {

                    List<result> resultList = recursiveProcessor.getDisposeResult();
                    for (result result : resultList) {
                        result.showResult();
                    }
                } catch (IOException e) {
                    System.out.println( ErrorResult.getErrorResultOf(ErrorTypeEnum.PARAM_ERROR).getMessage());
                }

            }else{//不是递归处理，证明只有一个参数 支持 -c -w -l -a
                FileProcessor processor = FileProcessorFactory.getFileProcessorOf(args[0]);
                try {
                    result result = processor.disposeFileOf(args[1]);
                    result.showResult();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }



    }
}
