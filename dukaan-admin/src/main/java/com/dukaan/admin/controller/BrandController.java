package com.dukaan.admin.controller;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.service.BrandService;
import com.dukaan.common.model.BrandTO;
import com.dukaan.common.model.PaginatedResponse;
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
@RequestMapping("/brands")
public class BrandController {

  private final BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping("")
  public ResponseEntity<PaginatedResponse<BrandTO>> getBrands(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "name") String[] sort,
      @RequestParam(defaultValue = "") String q) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(brandService.getBrands(page, size, sort, q));
  }

  @GetMapping("/{brandId}")
  public ResponseEntity<BrandTO> getBrand(@PathVariable String brandId) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(brandService.get(brandId));
  }

  @PostMapping
  public ResponseEntity<BrandTO> saveBrand(@RequestBody BrandTO brandTO) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(brandService.save(brandTO));
  }

  @PutMapping
  public ResponseEntity<BrandTO> updateBrand(@RequestBody BrandTO brandTO) throws ApiException {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(brandService.update(brandTO));
  }

  @DeleteMapping("/{brandId}")
  public void deleteBrand(@PathVariable String brandId) throws ApiException {
    brandService.delete(brandId);
  }
}
