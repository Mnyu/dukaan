package com.dukaan.admin.controller;

import com.dukaan.admin.service.RoleService;
import com.dukaan.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<Iterable<Role>> getAllRoles() {
   return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
  }
}
