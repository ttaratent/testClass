package com.sqb.exception;

public class MyException extends Exception {

    public MyException() {
    }
    
    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Exception e) {
        super(message,e);
    }
}
