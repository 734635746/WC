package com.lyb.processor;

import com.lyb.bean.ErrorResult;
import com.lyb.bean.FileResult;
import com.lyb.bean.result;
import com.lyb.enums.ErrorTypeEnum;

import java.io.*;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 12:13
 * 统计文件的字符数
 */
public class NumberOfCharProcessor implements FileProcessor{
    /**
     * 实现"-c"操作,统计文件的字符数
     * @param fileUrl 文件全路径名
     * @return 处理结果
     */
    @Override
    public result disposeFileOf(String fileUrl) throws IOException {

        if(fileUrl==null){//参数错误
            return  ErrorResult.getErrorResultOf(ErrorTypeEnum.PARAM_ERROR);
        }

        //创建一个文件对象
        File file = new File(fileUrl);
        //文件字符数
        int charNum = 0;

        if(file.isFile()){//文件存在
            //文件输入流
            InputStream inputStream = new FileInputStream(file);
            //转换成缓冲字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while((str=reader.readLine())!=null){
                //将所有的空白字符剔除
                str = str.replaceAll("\\s", "");
                //增加字符数
                charNum += str.length();
            }

            //关闭流
            reader.close();
            inputStream.close();

            FileResult result = new FileResult();
            result.setFileName(fileUrl);
            return result.addResult("字符个数:", charNum);

        }else{//文件不存在
            return ErrorResult.getErrorResultOf(ErrorTypeEnum.FILE_NOT_FOUNT);
        }

    }
}
