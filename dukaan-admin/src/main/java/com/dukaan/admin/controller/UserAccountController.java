package com.dukaan.admin.controller;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.security.DukaanUserDetails;
import com.dukaan.admin.service.UserService;
import com.dukaan.common.model.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserAccountController {

  private final UserService userService;

  @Autowired
  public UserAccountController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<UserTO> getAccountDetails(
      @AuthenticationPrincipal DukaanUserDetails dukaanUserDetails) throws ApiException {
    // To get the User object that represents the currently logged-in user, use AuthenticationPrincipal
    String loggedInUserEmail = dukaanUserDetails.getUsername();
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserToByEmail(loggedInUserEmail));
  }

  @PostMapping
  public ResponseEntity<UserTO> updateAccountDetails(@RequestBody UserTO userTO,
      @AuthenticationPrincipal DukaanUserDetails dukaanUserDetails) throws ApiException {
    // To get the User object that represents the currently logged-in user, use AuthenticationPrincipal
    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.updateAccount(dukaanUserDetails, userTO));
  }
}
