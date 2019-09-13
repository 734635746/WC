package com.lyb.test;

import com.lyb.Factory.FileProcessorFactory;
import com.lyb.processor.FileProcessor;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 16:08
 */
@RunWith(JUnit4.class)
public class Test {

    @org.junit.Test
    public void test() throws IOException {
        FileProcessor processor = FileProcessorFactory.getFileProcessorOf("-c");
        System.out.println(processor.disposeFileOf("F:/a.txt"));
    }
}
