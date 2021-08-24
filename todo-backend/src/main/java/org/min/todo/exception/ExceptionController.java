package org.min.todo.exception;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ExceptionResponse> emptyDataError(Exception e) {
        return new ResponseEntity<>(new ExceptionResponse(500, "존재하지 않는 데이터입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgError(Exception e) {
        return new ResponseEntity<>(new ExceptionResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> serverError(Exception e) {
        return new ResponseEntity<>(new ExceptionResponse(500, "잘못된 요청입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
