package com.dukaan.admin.service;

import com.dukaan.admin.repository.RoleRepository;
import com.dukaan.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Iterable<Role> getAllRoles() {
    return roleRepository.findAll();
  }
}
