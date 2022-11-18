package com.dukaan.admin.controller;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.common.model.UserTO;
import com.dukaan.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserTO>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserTO> getUser(@PathVariable String userId) throws ApiException {
    return ResponseEntity.status(HttpStatus.OK).body(userService.get(userId));
  }

  @PostMapping
  public ResponseEntity<UserTO> saveUser(@RequestBody UserTO userTO) throws ApiException {
    UserTO newUserTO = userService.save(userTO);
    return ResponseEntity.status(HttpStatus.OK).body(newUserTO);
  }

}
