package org.min.todo.exception;

public class UsernameDuplicateException extends RuntimeException{
    public UsernameDuplicateException(String message) {
        super(message);
    }
    public UsernameDuplicateException(String message, Throwable err) {
        super(message,err);
    }
}
