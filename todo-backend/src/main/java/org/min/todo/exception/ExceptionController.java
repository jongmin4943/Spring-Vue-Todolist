package org.min.todo.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@RestControllerAdvice
@Log4j2
public class ExceptionController {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ExceptionResponse> emptyDataError(Exception e) {
        log.error(e.getStackTrace());
        return new ResponseEntity<>(new ExceptionResponse(500, "존재하지 않는 데이터입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> serverError(Exception e) {
        log.error(e.getMessage(),e.getStackTrace());
        return new ResponseEntity<>(new ExceptionResponse(500, "잘못된 요청입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            ForbiddenException.class,
            IllegalArgumentException.class,
            BadCredentialsException.class,
            UserNotFoundException.class,
            UsernameDuplicateException.class
    })
    public ResponseEntity<?> generalError(Exception e) {
        log.error(e.getMessage(),e.getStackTrace());
        return new ResponseEntity<>(new ExceptionResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
