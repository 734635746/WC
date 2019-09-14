package com.lyb.test;

import com.lyb.Factory.FileProcessorFactory;
import com.lyb.bean.result;
import com.lyb.processor.FileProcessor;
import com.lyb.processor.RecursiveProcessor;
import com.lyb.util.FileUtils;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 16:08
 */
@RunWith(JUnit4.class)
public class Test {

    @org.junit.Test
    public void test() throws IOException {
        FileProcessor processor = FileProcessorFactory.getFileProcessorOf("-a");
        System.out.println(processor.disposeFileOf("F:\\a.txt"));
    }

    @org.junit.Test
    public void testDir(){
        File file = new File("F:\\");
        File[] list = file.listFiles();
        for (File s : list) {
            System.out.println(s.getAbsolutePath());
        }
    }

    @org.junit.Test
    public void testFileUtils(){
        List<String> list = FileUtils.getAllFileUrl(new File("F:\\课程资料"));
        for (String s : list) {
            System.out.println(s);
        }
    }

    @org.junit.Test
    public void testRecursiveFile() throws IOException {
        FileProcessor processor = FileProcessorFactory.getFileProcessorOf("-a");
        RecursiveProcessor recursiveProcessor = new RecursiveProcessor(processor, "F:\\file\\?.txt");
        List<result> disposeResult = recursiveProcessor.getDisposeResult();
        for (result result : disposeResult) {
                result.showResult();
        }
    }

    @org.junit.Test
    public void testSub(){
        String url= "F:\\前端资料\\网页模板资料\\01\\*.java";
        String substring = url.substring(url.lastIndexOf(File.separatorChar)+1);
        System.out.println(substring);
    }

    @org.junit.Test
    public void testGetAllFileUrlByRegex(){
        List<String> allFileUrlByRegex = FileUtils.getAllFileUrlByRegex("F:\\file\\?.txt");
        for (String fileUrlByRegex : allFileUrlByRegex) {
            System.out.println(fileUrlByRegex);
        }
    }

    @org.junit.Test
    public void testS(){
        System.out.println(File.separatorChar);
    }
}
