package com.dukaan.admin.service;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.repository.BrandRepository;
import com.dukaan.admin.util.Constants;
import com.dukaan.common.entity.Brand;
import com.dukaan.common.entity.Category;
import com.dukaan.common.model.BrandCategoryTO;
import com.dukaan.common.model.BrandTO;
import com.dukaan.common.model.PaginatedResponse;
import com.dukaan.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BrandService {

  private final BrandRepository brandRepo;
  private final CategoryService categoryService;

  @Autowired
  public BrandService(BrandRepository brandRepository,
      CategoryService categoryService) {
    this.brandRepo = brandRepository;
    this.categoryService = categoryService;
  }

  public PaginatedResponse<BrandTO> getBrands(int page, int size, String[] sortParams, String searchKey) {
    Pageable pageable = PageUtil.getPageable(page, size, sortParams);
    Page<Brand> brands;
    if (searchKey != null && !searchKey.isEmpty()) {
      brands = brandRepo.findAll(searchKey.toLowerCase(), pageable);
    } else {
      brands = brandRepo.findAll(pageable);
    }
    return PageUtil.getTransformedPaginatedResponse(brands, this::getBrandTOFromBrand);
  }


  public BrandTO save(BrandTO brandTO) throws ApiException {
    validateBrandName(brandTO.getName());
    Brand newBrand = brandRepo.save(getBrandFromBrandTO(brandTO));
    return getBrandTOFromBrand(newBrand);
  }

  public BrandTO get(String brandId) throws ApiException {
    Brand brand = getBrand(brandId);
    return getBrandTOFromBrand(brand);
  }

  public BrandTO update(BrandTO brandTO) throws ApiException {
    Brand brand = getBrand(brandTO.getId());
    if (!brand.getName().equals(brandTO.getName())) {
      validateBrandName(brandTO.getName());
      brand.setName(brandTO.getName());
    }
    if (brandTO.getCategories() != null) {
      brand.setCategories(getCategories(brandTO.getCategories()));
    }
    brand.setLogo(brandTO.getLogo() != null ? brandTO.getLogo() : brand.getLogo());
    Brand updatedBrand = brandRepo.save(brand);
    return getBrandTOFromBrand(updatedBrand);
  }

  public void delete(String brandId) throws ApiException {
    Brand brand = getBrand(brandId);
    brandRepo.deleteById(brandId);
  }

  private Brand getBrand(String brandId) throws ApiException {
    if (brandId == null) {
      throw new ApiException(Constants.BRAND_ID_MANDATORY);
    }
    Optional<Brand> brandOptional = brandRepo.findById(brandId);
    if (brandOptional.isEmpty()) {
      String errMsg = String.format(Constants.BRAND_NOT_EXISTS, brandId);
      throw new ApiException(errMsg);
    }
    return brandOptional.get();
  }

  private void validateBrandName(String name) throws ApiException {
    if (brandRepo.findByName(name).isPresent()) {
      String errMsg = String.format(Constants.BRAND_NAME_EXISTS, name);
      throw new ApiException(errMsg);
    }
  }

  private Brand getBrandFromBrandTO(BrandTO brandTO) throws ApiException {
    return Brand.builder()
        .name(brandTO.getName())
        .logo(brandTO.getLogo())
        .categories(getCategories(brandTO.getCategories()))
        .build();
  }

  private Set<Category> getCategories(Set<BrandCategoryTO> brandCategories) throws ApiException {
    Set<Category> categories = new HashSet<>();
    for (BrandCategoryTO brandCategory : brandCategories) {
      categories.add(categoryService.getCategory(brandCategory.getId()));
    }
    return categories;
  }

  private BrandTO getBrandTOFromBrand(Brand brand) {
    Set<BrandCategoryTO> brandCategories = brand.getCategories()
        .stream()
        .map(this::getBrandCategoryTO)
        .collect(Collectors.toSet());
    return BrandTO.builder()
        .id(brand.getId())
        .name(brand.getName())
        .logo(brand.getLogo())
        .categories(brandCategories)
        .build();
  }

  private BrandCategoryTO getBrandCategoryTO(Category category) {
    return BrandCategoryTO.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }

}
