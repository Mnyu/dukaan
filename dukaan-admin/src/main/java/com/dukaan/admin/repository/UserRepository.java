package com.dukaan.admin.repository;

import com.dukaan.common.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

  Optional<User> findByEmail(String email);

  Long countById(String id);
}
