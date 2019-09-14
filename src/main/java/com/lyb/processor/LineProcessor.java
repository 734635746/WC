package com.lyb.processor;

import com.lyb.bean.ErrorResult;
import com.lyb.bean.FileResult;
import com.lyb.bean.result;
import com.lyb.enums.ErrorTypeEnum;

import java.io.*;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 18:58
 * 统计文件的行数
 */
public class LineProcessor implements  FileProcessor{

    /**
     * 实现"-l"操作,统计文件的行数
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
        //行数
        int line = 0;

        if(file.isFile()){//文件存在
            //文件输入流
            InputStream inputStream = new FileInputStream(file);
            //转换成缓冲字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while((reader.readLine())!=null){
                line++;
            }

            //关闭流
            reader.close();
            inputStream.close();

            FileResult result = new FileResult();
            result.setFileName(fileUrl);
            return result.addResult("行数:", line);

        }else{//文件不存在
            return ErrorResult.getErrorResultOf(ErrorTypeEnum.FILE_NOT_FOUNT);
        }
    }
}
