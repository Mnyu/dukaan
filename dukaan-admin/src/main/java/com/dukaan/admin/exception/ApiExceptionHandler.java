package com.dukaan.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiError> handleCustomRewardsExceptions(ApiException exception) {
    log.error("Exception occurred while invoking API: ", exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getApiError());
  }
}
