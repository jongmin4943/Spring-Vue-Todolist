package org.min.todo.exception;

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message,Throwable err) {
        super(message,err);
    }
}
