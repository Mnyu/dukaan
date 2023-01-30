package com.dukaan.admin.repository;

import com.dukaan.common.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {

  Optional<Category> findByName(String name);

  Optional<Category> findByAlias(String alias);

  @Query("SELECT c FROM Category c WHERE c.parent.id is NULL")
  List<Category> findRootCategories(Sort sort);
}
