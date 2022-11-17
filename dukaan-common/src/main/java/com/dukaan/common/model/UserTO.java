package com.dukaan.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String photoName;
  private boolean isActive;
  private Set<String> roles;
}
