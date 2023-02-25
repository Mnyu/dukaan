package com.dukaan.admin.controller;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.service.ProductService;
import com.dukaan.common.model.PaginatedResponse;
import com.dukaan.common.model.ProductTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping()
  public ResponseEntity<PaginatedResponse<ProductTO>> getProducts(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "name") String[] sort,
      @RequestParam(defaultValue = "") String categoryId, @RequestParam(defaultValue = "") String q) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productService.getProducts(page, size, sort, categoryId, q));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductTO> getProduct(@PathVariable String productId) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productService.get(productId));
  }

  @PostMapping
  public ResponseEntity<ProductTO> saveProduct(@RequestBody ProductTO productTO) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productService.save(productTO));
  }

  @PutMapping
  public ResponseEntity<ProductTO> updateProduct(@RequestBody ProductTO productTO) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(productService.update(productTO));
  }

  @DeleteMapping("/{productId}")
  public void deleteProduct(@PathVariable String productId) throws ApiException {
    productService.delete(productId);
  }

}
