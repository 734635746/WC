package com.lyb.processor;

import com.lyb.bean.ErrorResult;
import com.lyb.bean.FileResult;
import com.lyb.bean.result;
import com.lyb.enums.ErrorTypeEnum;

import java.io.*;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 19:28
 * 统计文件的复杂数据：代码行 / 空行 / 注释行
 */
public class ComplexProcessor implements FileProcessor{

    /**
     * 实现"-a"操作,统计文件的复杂数据：代码行 / 空行 / 注释行
     * @param fileUrl 文件全路径名
     * @return 处理结果
     */
    @Override
    public result disposeFileOf(String fileUrl) throws IOException {

        if(fileUrl==null){//参数错误
            return  ErrorResult.getErrorResultOf(ErrorTypeEnum.PARAM_ERROR);
        }

        int line=0;//总行数
        int emptyLine = 0;//空行
        int codeLine = 0;//代码行
        int annotationLine=0;//注释行
        boolean isStart = false;//标记注释块是否开始
        int startLine = 0;//注释块开始的行数


        //创建一个文件对象
        File file = new File(fileUrl);

        if(file.isFile()){//判断是否为文件
            //文件输入流
            InputStream inputStream = new FileInputStream(file);
            //转换成缓冲字符流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while((str=reader.readLine())!=null){
                //读取的行不为空，则总行数加1
                line++;
                //将读取的行中的所有空白字符替换掉
                str = str.replaceAll("\\s", "");

                if(str.equals("")||str.equals("{")||str.equals("}")){//判断是否为空行
                    if(str.startsWith("")&&(isStart==true)){
                        //这里是处理注释块中含有空行的情况,注释块开始时的空行是注释行，在这里不计入空行
                       continue;
                    }
                    emptyLine++;
                }else if (str.startsWith("//")||str.startsWith("}//")||str.startsWith("{//")){
                    //判断是否为注释行,这里主要是判断单行注释
                    annotationLine++;
                }else if(str.startsWith("/*")){//判断注释块,注释块开始
                    /**
                     /*
                     * 注释块(相当于5行注释)

                     */
                    isStart=true;//注释块开始
                    if(line>startLine&&startLine==0){//这里是为了防止注释块里行有“/*”影响判断
                        startLine = line;
                    }
                }else if(str.startsWith("*/")){//注释块结束
                        annotationLine += line - startLine+1;
                        //结束后重置标志
                        isStart=false;
                        startLine=0;
                }

            }
            //代码行等于总行数-注释行-空行
            codeLine = line-annotationLine-emptyLine;

            //关闭流
            reader.close();
            inputStream.close();

            FileResult result = new FileResult();
            result.setFileName(fileUrl);
            result.addResult("总行数:", line);
            result.addResult("代码行数:", codeLine);
            result.addResult("注释行数:", annotationLine);
            result.addResult("空行数:", emptyLine);

            return result;
        }else{//文件不存在
            return ErrorResult.getErrorResultOf(ErrorTypeEnum.FILE_NOT_FOUNT);
        }

    }
}