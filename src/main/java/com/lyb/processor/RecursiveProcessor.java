package com.lyb.processor;

import com.lyb.bean.ErrorResult;
import com.lyb.bean.result;
import com.lyb.enums.ErrorTypeEnum;
import com.lyb.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyoubin
 * @date 2019/9/14 - 12:27
 * 递归处理指定的目录以及指定的处理方式
 */
public class RecursiveProcessor {
    //文件处理对象
    private FileProcessor processor;
    //需要递归处理的文件目录
    private String url;


    public RecursiveProcessor(FileProcessor processor, String url) {
        this.processor = processor;
        this.url = url;
    }

    /**
     * 获取递归处理的结果集
     * @return 结果集
     */
    public List<result> getDisposeResult() throws IOException {

        if(url==null){//参数错误
            return Arrays.asList(ErrorResult.getErrorResultOf(ErrorTypeEnum.PARAM_ERROR));
        }

        //文件对象
        File file = new File(url);

        if(url.contains("*")||url.contains("?")){//文件目录中包含通配符
            //递归匹配通配符获取目录下的所有文件的url
            List<String> allFileUrl = FileUtils.getAllFileUrlByRegex(url);

            List<result> results = new ArrayList<>();
            for (String fileUrl : allFileUrl) {
                result result = processor.disposeFileOf(fileUrl);
                results.add(result);
            }
            return results;

        }else{
            if(file.isFile()){//文件类型
                result result = processor.disposeFileOf(url);
                return Arrays.asList(result);

            }else if(file.isDirectory()){//目录类型，递归调用
                //递归获取目录下的所有文件的url
                List<String> allFileUrl = FileUtils.getAllFileUrl(file);
                List<result> results = new ArrayList<>();
                for (String fileUrl : allFileUrl) {
                    result result = processor.disposeFileOf(fileUrl);
                    results.add(result);
                }
                return results;

            }else{
                return Arrays.asList(ErrorResult.getErrorResultOf(ErrorTypeEnum.FILE_NOT_FOUNT));
            }
        }


    }
}
