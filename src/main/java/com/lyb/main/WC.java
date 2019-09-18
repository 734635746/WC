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
import java.util.Scanner;

/**
 * 统计程序的入口
 * @author liuyoubin
 * @date 2019/9/13 - 11:12
 */
public class WC {

    public static void main(String[] args) {
        System.out.println("*************欢迎使用java源文件统计函数*************");
        System.out.println("*************通过 【param】【fileName】格式来使用");
        System.out.println("*************关于【param】的说明:");
        System.out.println("*************-a(统计代码行、空行 、 注释行)、-l(统计行数)、-w(统计单词数数)、-c(统计字符数)");
        System.out.println("*************-s 配合以上四个参数使用可以开启递归统计功能，同时支持'*'、'?'通配符使用");
        System.out.println("*************-x 单独使用，开启图形化界面功能");
        System.out.println("*************注意：文件分隔符请使用'\\'");
        System.out.println("*************输入'bye'退出程序");
        while(true){
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            if(str.equals("bye")){
                System.out.println("");
                break;
            }
            String[] split = str.split(" ");
            if(split[0].equals("-x")){//使用图形界面

                window.main(null);

            }else if(split[0].equals("-s")){//判断是否递归处理 -s -a/-l/-w/-c *.txt
                //获取相应的文件处理对象
                FileProcessor processor = FileProcessorFactory.getFileProcessorOf(split[1]);
                //递归处理对象
                RecursiveProcessor recursiveProcessor = new RecursiveProcessor(processor, split[2]);
                //获取处理的结果集
                try {
                    List<result> resultList = recursiveProcessor.getDisposeResult();
                    for (result result : resultList) {
                        result.showResult();
                        System.out.println();
                    }
                } catch (IOException e) {
                    System.out.println( ErrorResult.getErrorResultOf(ErrorTypeEnum.PARAM_ERROR).getMessage());
                }

            }else{//不是递归处理，证明只有一个参数 支持 -c -w -l -a
                FileProcessor processor = FileProcessorFactory.getFileProcessorOf(split[0]);
                try {
                    if (processor==null){
                        System.out.println("传入的参数错误!");
                    }else {
                        result result = processor.disposeFileOf(split[1]);
                        result.showResult();
                        System.out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }




    }
}
