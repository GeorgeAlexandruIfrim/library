package com.georgeifrim.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<ErrorResponse> handle(BookNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler({BookNotFoundException.class})
//    public ErrorResponse handle1(BookNotFoundException e){
//        return new ErrorResponse(e.getMessage());
//    }
}
