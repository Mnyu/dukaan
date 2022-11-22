package com.dukaan.admin.exception;

import com.dukaan.common.model.ErrorTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorTO> handleApiException(ApiException exception) {
    log.error("Exception occurred while invoking API: ", exception);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorTO(exception));
  }

  private ErrorTO getErrorTO(ApiException exception) {
    return ErrorTO.builder()
        .error(exception.getMessage())
        .build();
  }

}
