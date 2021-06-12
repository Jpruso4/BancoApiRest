
package com.bank.runner.application.exception;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    return buildResponseEntity(httpStatus, exc);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleException(DuplicateKeyException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, exc);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exc) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(httpStatus, exc);
  }
  
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleException(InternalServerError exc) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    return buildResponseEntity(httpStatus, exc);
  }
  
  private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
    return buildResponseEntity(httpStatus, exc, null);
  }

  private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
    ErrorResponse error = new ErrorResponse();
    error.setMessage(exc.getMessage());
    error.setStatus(httpStatus.value());
    error.setTimestamp(new Date());
    error.setErrors(errors);
    return new ResponseEntity<>(error, httpStatus);

  }
}