package com.dukaan.admin.controller;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.service.CategoryService;
import com.dukaan.common.model.CategoryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<CategoryTO>> getAllCategories() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(categoryService.getAllCategories());
  }

  @GetMapping()
  public ResponseEntity<List<CategoryTO>> getCategoriesInHierarchy() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(categoryService.getCategoriesInHierarchy());
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryTO> getCategory(@PathVariable String categoryId) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(categoryService.get(categoryId));
  }

  @PostMapping
  public ResponseEntity<CategoryTO> saveCategory(@RequestBody CategoryTO categoryTO) throws ApiException {
    CategoryTO newCategoryTO = categoryService.save(categoryTO);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(newCategoryTO);
  }

  @PutMapping
  public ResponseEntity<CategoryTO> updateCategory(@RequestBody CategoryTO categoryTO) throws ApiException {
    CategoryTO updatedCategoryTO = categoryService.update(categoryTO);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(updatedCategoryTO);
  }

  @DeleteMapping("/{categoryId}")
  public void deleteCategory(@PathVariable String categoryId) throws ApiException {
    categoryService.delete(categoryId);
  }

}
