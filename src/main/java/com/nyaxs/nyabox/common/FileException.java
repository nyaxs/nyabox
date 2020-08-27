package com.nyaxs.nyabox.common;

/**
 * @ClassName FileException
 * @Description TODO:
 * @Author Administrator
 * @Date 2020-08-27 9:48
 * @Version 1.0
 **/
public class FileException extends RuntimeException {
    public FileException(String message){
        super(message);
    }

    public FileException(String message, Throwable cause){
        super(message, cause);
    }
}
