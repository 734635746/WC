package com.lyb.processor;

import com.lyb.bean.ErrorResult;
import com.lyb.bean.FileResult;
import com.lyb.bean.result;
import com.lyb.enums.ErrorTypeEnum;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 19:03
 * 统计文件中的单词数
 */
public class WordProcessor implements FileProcessor{

    @Override
    public result disposeFileOf(String fileUrl) throws IOException {
        if(fileUrl==null){//参数错误
            return  ErrorResult.getErrorResultOf(ErrorTypeEnum.PARAM_ERROR);
        }

        //创建一个文件对象
        File file = new File(fileUrl);
        //单词数
        int count = 0;

        if(file.isFile()){//文件存在
            //文件输入流
            InputStream inputStream = new FileInputStream(file);
            //转换成缓冲字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while((str = reader.readLine())!=null){
                //采用正则表达式来统计单词数
                Pattern p = Pattern.compile("\\b[a-zA-Z]+\\b");
                Matcher m = p.matcher(str);
                while(m.find()){
                    count++;
                }
            }

            //关闭流
            reader.close();
            inputStream.close();

            FileResult result = new FileResult();
            result.setFileName(fileUrl);
            return result.addResult("单词数:", count);

        }else{//文件不存在
            return ErrorResult.getErrorResultOf(ErrorTypeEnum.FILE_NOT_FOUNT);
        }
    }
}
