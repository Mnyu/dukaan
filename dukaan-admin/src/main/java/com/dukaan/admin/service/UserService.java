package com.dukaan.admin.service;

import com.dukaan.common.model.UserTO;
import com.dukaan.admin.repository.UserRepository;
import com.dukaan.common.entity.Role;
import com.dukaan.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public List<UserTO> listAll() {
    Iterable<User> users = userRepository.findAll();
    return StreamSupport.stream(users.spliterator(), false)
        .map(this::getUserToFromUser)
        .collect(Collectors.toList());
  }

  private UserTO getUserToFromUser(User user) {
    //TODO : throw exception for null user
    Set<String> roleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    return UserTO.builder()
        .id(user.getId())
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .photoName(user.getPhotoName())
        .isActive(user.isActive())
        .roles(roleNames)
        .build();
  }

}
