package com.dukaan.admin.controller;

import com.dukaan.common.model.LoginTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthenticationManager authManager;

  @Autowired
  public AuthController(AuthenticationManager authManager) {
    this.authManager = authManager;
  }

  @PostMapping("/login")
  public ResponseEntity<String> authenticateUser(@RequestBody LoginTO loginTO) {
    Authentication authentication = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginTO.getEmail(), loginTO.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User signed-in successfully!!!", HttpStatus.OK);
  }
}
