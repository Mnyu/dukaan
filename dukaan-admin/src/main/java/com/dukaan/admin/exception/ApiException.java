package com.dukaan.admin.exception;

import lombok.Getter;

@Getter
public class ApiException extends Exception {

  private final ApiError apiError;

  public ApiException(ApiError apiError) {
    super(apiError.getMessage());
    this.apiError = apiError;
  }

  public ApiException(ApiError apiError, Throwable cause) {
    super(apiError.getMessage(), cause);
    this.apiError = apiError;
  }

}
