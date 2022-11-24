package com.dukaan.admin.repository;

import com.dukaan.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

  Optional<User> findByEmail(String email);

  Long countById(String id);

  // For Search functionality
  @Query("SELECT u FROM User u WHERE CONCAT(lower(u.email) , ' ', lower(u.firstName) , ' ', lower(u.lastName)) LIKE %:searchKey%")
  Page<User> findAll(@Param("searchKey") String searchKey, Pageable pageable);
}
