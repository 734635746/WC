package com.lyb.test;

import com.lyb.Factory.FileProcessorFactory;
import com.lyb.bean.result;
import com.lyb.main.WC;
import com.lyb.processor.FileProcessor;
import com.lyb.processor.RecursiveProcessor;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 16:08
 */
@RunWith(JUnit4.class)
public class Test {
    /**
     * 测试非递归功能
     * @throws IOException
     */
    @org.junit.Test
    public void testFunction() throws IOException {
        String[] urls= new String[]{"F:\\file\\a.java","F:\\file\\oo.txt","F:\\file\\"};
        String[] params= new String[]{"-a","-l","-w","-c"};

        for (String url : urls) {
            for (String param : params) {
                testOneProcessor(url,param);
            }
        }

    }

     private void testOneProcessor(String url, String param) throws IOException {
        FileProcessor processor = FileProcessorFactory.getFileProcessorOf(param);
         result result = processor.disposeFileOf(url);
         result.showResult();
     }

/***********************************************/

    /**
     * 测试递归功能
     * @throws IOException
     */
    @org.junit.Test
    public void test() throws IOException {
        String[] urls= new String[]{"F:\\file\\a.java","F:\\file\\oo.txt","F:\\file\\","F:\\file\\*.txt","F:\\file\\?.txt"};
        String[] params= new String[]{"-a","-l","-w","-c"};
        for (String url : urls) {
            for (String param : params) {
                testRecursive(url,param);
            }
        }

    }
    private void testRecursive(String url, String param) throws IOException {
        FileProcessor processor = FileProcessorFactory.getFileProcessorOf(param);
        RecursiveProcessor processor1 = new RecursiveProcessor(processor, url);
        List<result> result = processor1.getDisposeResult();
        for (com.lyb.bean.result result1 : result) {
            result1.showResult();
        }
    }

    /******************************************/
    /**
     * 测试主函数
     */
    @org.junit.Test
    public void testMain(){
        WC.main(new String[]{"-a","F:\\file\\a.java"});
        WC.main(new String[]{"-a",null});
        WC.main(new String[]{"-s","-a","F:\\file\\a.java"});
        WC.main(new String[]{"-s","-a",null});
    }

    /**
     * 测试window
     */
    @org.junit.Test
    public void testWindow(){
        WC.main(new String[]{"-x"});
    }
}
