package com.lyb.processor;

import com.lyb.bean.result;

import java.io.IOException;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 11:53
 * 文件处理器的接口
 */
public interface FileProcessor {

    /**
     * 对指定文件(全路径名)进行相应的处理，并放回处理结果
     * @param fileUrl 文件全路径名
     * @return 放回结果
     */
    result disposeFileOf(String fileUrl) throws IOException;

}
