package com.dukaan.admin.repository;

import com.dukaan.common.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

  Optional<Product> findByName(String name);

  // For Search Functionality
  @Query("SELECT p FROM Product p WHERE "
      + "LOWER(p.name) LIKE %:searchKey% "
      + "OR LOWER(p.shortDescription) LIKE %:searchKey% "
      + "OR LOWER(p.fullDescription) LIKE %:searchKey% "
      + "OR LOWER(p.brand.name) LIKE %:searchKey% "
      + "OR LOWER(p.category.name) LIKE %:searchKey%")
  Page<Product> findAll(@Param("searchKey") String searchKey, Pageable pageable);
}
