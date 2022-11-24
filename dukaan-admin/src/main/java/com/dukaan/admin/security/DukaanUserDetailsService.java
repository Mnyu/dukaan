package com.dukaan.admin.security;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.service.UserService;
import com.dukaan.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DukaanUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // username here is the email as configured in DukaanUserDetails
    try {
      User user = userService.getUserByEmail(username);
      return new DukaanUserDetails(user);
    } catch (ApiException e) {
      throw new UsernameNotFoundException(e.getMessage(), e);
    }
  }
}
