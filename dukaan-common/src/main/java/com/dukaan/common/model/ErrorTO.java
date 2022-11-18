package com.dukaan.common.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorTO {
  private String code;
  private String message;
}
