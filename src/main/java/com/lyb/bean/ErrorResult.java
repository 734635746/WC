package com.lyb.bean;

import com.lyb.enums.ErrorTypeEnum;

/**
 * @author liuyoubin
 * @date 2019/9/13 - 11:54
 * 错误结果返回封装类
 */
public class ErrorResult implements result{

    /**
     * 错误消息
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public static ErrorResult getErrorResultOf(String message){
        ErrorResult errorResult = new ErrorResult();
        errorResult.message= message;
        return errorResult;
    }

    public static ErrorResult getErrorResultOf(ErrorTypeEnum e){
        ErrorResult errorResult = new ErrorResult();
        errorResult.message= e.getMessage();
        return errorResult;
    }


    @Override
    public void showResult() {
        System.out.println(message);
    }
}
