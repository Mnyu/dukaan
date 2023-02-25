package com.dukaan.admin.service;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.repository.CategoryRepository;
import com.dukaan.admin.util.Constants;
import com.dukaan.common.entity.Category;
import com.dukaan.common.model.CategoryTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class CategoryService {

  private final String PARENT_CATEGORY_DELIMITER= "$";
  private final CategoryRepository catRepo;

  @Autowired
  public CategoryService(CategoryRepository catRepo) {
    this.catRepo = catRepo;
  }

  public List<CategoryTO> getAllCategories() {
    Iterable<Category> categories = catRepo.findAll(Sort.by("name").ascending());
    return StreamSupport.stream(categories.spliterator(), false)
        .map(this::getCategoryToFromCategory)
        .sorted(Comparator.comparing(CategoryTO::getName))
        .collect(Collectors.toList());
  }

  public List<CategoryTO> getCategoriesInHierarchy() {
    Iterable<Category> categories = catRepo.findRootCategories(Sort.by("name").ascending());
    return StreamSupport.stream(categories.spliterator(), false)
        .map(this::getCategoryToFromCategory)
        .sorted(Comparator.comparing(CategoryTO::getName))
        .collect(Collectors.toList());
  }

  public CategoryTO get(String categoryId) throws ApiException {
    Category category = getCategory(categoryId);
    return getCategoryToFromCategory(category);
  }

  public void delete(String categoryId) throws ApiException {
    Category category = getCategory(categoryId);
    if (category.getSubCategories().size() > 0) {
      String errMsg = String.format(Constants.CATEGORY_CANNOT_BE_DELETED, categoryId);
      throw new ApiException(errMsg);
    }
    catRepo.deleteById(categoryId);
  }

  public CategoryTO save(CategoryTO categoryTO) throws ApiException {
    validate(categoryTO);
    Category parentCategory = null;
    String allParentIds = null;
    if (categoryTO.getParent() != null) {
      parentCategory = getCategory(categoryTO.getParent());
      allParentIds = getAllParentIds(parentCategory);
    }
    Category newCategory = catRepo.save(getCategory(categoryTO, parentCategory, allParentIds));
    return getCategoryToFromCategory(newCategory);
  }

  public CategoryTO update(CategoryTO categoryTO) throws ApiException {
    Category category = getCategory(categoryTO.getId());
    if (categoryTO.getName() != null && !categoryTO.getName().equals(category.getName())) {
      validateCategoryName(categoryTO.getName());
      category.setName(categoryTO.getName());
    }
    if (categoryTO.getAlias() != null && !categoryTO.getAlias().equals(category.getAlias())) {
      validateCategoryAlias(categoryTO.getAlias());
      category.setAlias(categoryTO.getAlias());
    }
    if (categoryTO.getParent() != null) {
      Category parentCategory = getCategory(categoryTO.getParent());
      String allParentIds = getAllParentIds(parentCategory);
      category.setParent(parentCategory);
      category.setAllParentIds(allParentIds);
    }
    category.setImage(categoryTO.getImage() != null ? categoryTO.getImage() : category.getImage());
    category.setActive(categoryTO.getActive() != null ? categoryTO.getActive() : category.isActive());
    Category updatedCategory = catRepo.save(category);
    return getCategoryToFromCategory(updatedCategory);
  }

  protected Category getCategory(String categoryId) throws ApiException {
    if (categoryId == null) {
      throw new ApiException(Constants.CATEGORY_ID_MANDATORY);
    }
    Optional<Category> categoryOpt = catRepo.findById(categoryId);
    if (categoryOpt.isEmpty()) {
      String errMsg = String.format(Constants.CATEGORY_NOT_EXISTS, categoryId);
      throw new ApiException(errMsg);
    }
    return categoryOpt.get();
   }

  private void validate(CategoryTO categoryTO) throws ApiException {
    validateCategoryName(categoryTO.getName());
    validateCategoryAlias(categoryTO.getAlias());
  }

  private void validateCategoryName(String name) throws ApiException {
    if (catRepo.findByName(name).isPresent()) {
      String errMsg = String.format(Constants.CATEGORY_NAME_EXISTS, name);
      throw new ApiException(errMsg);
    }
  }

  private void validateCategoryAlias(String alias) throws ApiException {
    if (catRepo.findByAlias(alias).isPresent()) {
      String errMsg = String.format(Constants.CATEGORY_ALIAS_EXISTS, alias);
      throw new ApiException(errMsg);
    }
  }

  private String getAllParentIds(Category parentCategory) {
    String allParentIdsString = parentCategory.getAllParentIds() == null ? PARENT_CATEGORY_DELIMITER
        : parentCategory.getAllParentIds();
    allParentIdsString = allParentIdsString + parentCategory.getId() + PARENT_CATEGORY_DELIMITER;
    return allParentIdsString;
  }

  private Category getCategory(CategoryTO categoryTO, Category parentCategory, String allParentIds) {
    return Category.builder()
        .name(categoryTO.getName())
        .alias(categoryTO.getAlias())
        .parent(parentCategory)
        .allParentIds(allParentIds)
        .build();
  }

  private CategoryTO getCategoryToFromCategory(Category category) {
    return CategoryTO.builder()
        .id(category.getId())
        .name(category.getName())
        .alias(category.getAlias())
        .active(category.isActive())
        .image(category.getImage())
        .parent(category.getParent() == null ? null : category.getParent().getId())
        .subCategories(category.getSubCategories().stream()
            .map(this::getCategoryToFromCategory).collect(
            Collectors.toList()))
        .build();
  }
}
