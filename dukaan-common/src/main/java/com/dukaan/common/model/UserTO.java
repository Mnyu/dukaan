package com.dukaan.common.model;

import com.dukaan.common.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTO {
  private String id;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String photoName;
  private boolean isActive;
  private Set<Role> roles;
}
