package com.dukaan.admin.exception;

import com.dukaan.admin.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ApiError {
  EMAIL_EXISTS(Constants.ERR_001, Constants.EMAIL_EXISTS);

  private String code;
  private String message;

}
