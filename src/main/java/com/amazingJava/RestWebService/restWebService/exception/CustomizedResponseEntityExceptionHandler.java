package com.amazingJava.RestWebService.restWebService.exception;

import com.amazingJava.RestWebService.restWebService.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)    //handle all exception
    public final ResponseEntity<ErrorDetails> handleAllExceptiona(Exception ex,WebRequest request) {

        ErrorDetails errorDetails =  new ErrorDetails(LocalDateTime.now(), ex.getMessage()
                            , request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)    //handle only user not found exception
    public final ResponseEntity<ErrorDetails> handleUserNotFound(Exception ex,WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage()
                ,request.getDescription(false));
        return  new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Error count "+ex.getErrorCount()+" : First error => "+ex.getFieldError().getDefaultMessage(),request.getDescription(false));

        return  new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }


}
