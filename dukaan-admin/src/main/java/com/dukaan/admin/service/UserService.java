package com.dukaan.admin.service;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.export.UserCsvExporter;
import com.dukaan.admin.util.Constants;
import com.dukaan.common.util.PageUtil;
import com.dukaan.common.model.PaginatedResponse;
import com.dukaan.common.model.UserTO;
import com.dukaan.admin.repository.UserRepository;
import com.dukaan.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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

  public PaginatedResponse<UserTO> getUsers(int page, int size, String[] sortParams, String searchKey) {
    Pageable pageable = PageUtil.getPageable(page, size, sortParams);
    Page<User> users;
    if (searchKey != null && !searchKey.isEmpty()) {
      users = userRepository.findAll(searchKey.toLowerCase(), pageable);
    } else {
      users = userRepository.findAll(pageable);
    }
    return PageUtil.getTransformedPaginatedResponse(users, this::getUserToFromUser);
  }

  public UserTO get(String userId) throws ApiException {
    User user = getUser(userId);
    return getUserToFromUser(user);
  }

  public UserTO save(UserTO userTO) throws ApiException {
    validate(userTO);
    User newUser = userRepository.save(getUserFromUserTO(userTO));
    return getUserToFromUser(newUser);
  }

  public UserTO update(UserTO userTO) throws ApiException {
    validate(userTO);
    User user = getUser(userTO.getId());
    user.setEmail(userTO.getEmail() != null ? userTO.getEmail() : user.getEmail());
    user.setPassword(userTO.getPassword() != null ? passwordEncoder.encode(userTO.getPassword())
        : user.getPassword());
    user.setFirstName(userTO.getFirstName() != null ? userTO.getFirstName() : user.getFirstName());
    user.setLastName(userTO.getLastName() != null ? userTO.getLastName() : user.getLastName());
    user.setPhotoName(userTO.getPhotoName() != null ? userTO.getPhotoName() : user.getPhotoName());
    user.setActive(userTO.getActive() != null ? userTO.getActive() : user.isActive());
    user.setRoles(userTO.getRoles() != null ? userTO.getRoles() : user.getRoles());
    User updatedUser = userRepository.save(user);
    return getUserToFromUser(updatedUser);
  }

  public void delete(String userId) throws ApiException {
    Long count = userRepository.countById(userId);
    if (count == null || count == 0L) {
      String errMsg = String.format(Constants.USER_NOT_EXISTS, userId);
      throw new ApiException(errMsg);
    }
    userRepository.deleteById(userId);
  }

  public void export(String format, HttpServletResponse response) throws ApiException {
    Iterable<User> users = userRepository.findAll(Sort.by("firstName").ascending());
    switch (format) {
      case Constants.EXPORT_CSV :  exportToCSV(response, users); break;
      default: throw new ApiException(Constants.WRONG_EXPORT_FORMAT);
    }
  }

  public User getUserByEmail(String email) throws ApiException {
    Optional<User> userOptional = userRepository.findByEmail(email);
    if (userOptional.isEmpty()) {
      String errMsg = String.format(Constants.USER_EMAIL_NOT_EXISTS, email);
      throw new ApiException(errMsg);
    }
    return userOptional.get();
  }

  private void exportToCSV(HttpServletResponse response, Iterable<User> users) throws ApiException {
    try {
      UserCsvExporter.export(response, users);
    } catch (IOException ex) {
      throw new ApiException(Constants.UNABLE_TO_EXPORT_CSV, ex);
    }
  }

  private User getUser(String userId) throws ApiException {
    if (userId == null) {
      throw new ApiException(Constants.USER_ID_MANDATORY);
    }
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      String errMsg = String.format(Constants.USER_NOT_EXISTS, userId);
      throw new ApiException(errMsg);
    }
    return userOptional.get();
  }

  private void validate(UserTO userTO) throws ApiException {
    if (userEmailExists(userTO.getEmail())) {
      String errMsg = String.format(Constants.USER_EMAIL_EXISTS, userTO.getEmail());
      throw new ApiException(errMsg);
    }
  }

  private boolean userEmailExists(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  private UserTO getUserToFromUser(User user) {
    return UserTO.builder()
        .id(user.getId())
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .photoName(user.getPhotoName())
        .active(user.isActive())
        .roles(user.getRoles())
        .build();
  }

  private User getUserFromUserTO(UserTO userTO) {
    return User.builder()
        .email(userTO.getEmail())
        .password(passwordEncoder.encode(userTO.getPassword()))
        .firstName(userTO.getFirstName())
        .lastName(userTO.getLastName())
        .photoName(userTO.getPhotoName())
        .isActive(userTO.getActive())
        .roles(userTO.getRoles())
        .build();
  }

}
