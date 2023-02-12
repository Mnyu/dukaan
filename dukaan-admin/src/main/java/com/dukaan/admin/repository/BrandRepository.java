package com.dukaan.admin.repository;

import com.dukaan.common.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, String> {

  Optional<Brand> findByName(String name);

  // For Search functionality
  @Query("SELECT b FROM Brand b WHERE LOWER(b.name) LIKE %:searchKey%")
  Page<Brand> findAll(@Param("searchKey") String searchKey, Pageable pageable);

  @Query("SELECT b FROM Brand b ORDER BY b.name ASC")
  List<Brand> findAll();
}
