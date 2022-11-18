package com.dukaan.admin.service;

import com.dukaan.common.model.UserTO;
import com.dukaan.admin.repository.UserRepository;
import com.dukaan.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository,
                    PasswordEncoder passwordEncoder){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserTO> getAllUsers() {
    Iterable<User> users = userRepository.findAll();
    return StreamSupport.stream(users.spliterator(), false)
        .map(this::getUserToFromUser)
        .collect(Collectors.toList());
  }

  public UserTO save(UserTO userTO) {
    User newUser = userRepository.save(getUserFromUserTO(userTO));
    return getUserToFromUser(newUser);
  }

  private UserTO getUserToFromUser(User user) {
    //TODO : throw exception for null user
    return UserTO.builder()
        .id(user.getId())
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .photoName(user.getPhotoName())
        .isActive(user.isActive())
        .roles(user.getRoles())
        .build();
  }

  private User getUserFromUserTO(UserTO userTO) {
    //TODO : throw exception for null userTO
    return User.builder()
        .email(userTO.getEmail())
        .password(passwordEncoder.encode(userTO.getPassword()))
        .firstName(userTO.getFirstName())
        .lastName(userTO.getLastName())
        .photoName(userTO.getPhotoName())
        .isActive(userTO.isActive())
        .roles(userTO.getRoles())
        .build();
  }

}
