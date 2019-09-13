package com.lyb.enums;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 17:45
 * 错误类型的枚举
 */
public enum ErrorTypeEnum {

    FILE_NOT_FOUNT("文件不存在!"),
    PARAM_ERROR("参数使用错误!");

    String message;

    ErrorTypeEnum(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
