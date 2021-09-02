package org.min.todo.exception;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
    public ForbiddenException(String message, Throwable err) {
        super(message,err);
    }
}
