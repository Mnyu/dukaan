package com.dukaan.admin.service;

import com.dukaan.admin.exception.ApiException;
import com.dukaan.admin.repository.ProductRepository;
import com.dukaan.admin.util.Constants;
import com.dukaan.common.entity.Brand;
import com.dukaan.common.entity.Category;
import com.dukaan.common.entity.Product;
import com.dukaan.common.entity.ProductDetail;
import com.dukaan.common.entity.ProductImage;
import com.dukaan.common.model.PaginatedResponse;
import com.dukaan.common.model.ProductBrandTO;
import com.dukaan.common.model.ProductCategoryTO;
import com.dukaan.common.model.ProductDetailTO;
import com.dukaan.common.model.ProductTO;
import com.dukaan.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final BrandService brandService;
  private final CategoryService categoryService;

  @Autowired
  public ProductService(ProductRepository productRepository,
      BrandService brandService,
      CategoryService categoryService) {
    this.productRepository = productRepository;
    this.brandService = brandService;
    this.categoryService = categoryService;
  }

  public PaginatedResponse<ProductTO> getProducts(int page, int size, String[] sortParams, String searchKey) {
    Pageable pageable = PageUtil.getPageable(page, size, sortParams);
    Page<Product> products;
    if (searchKey != null && !searchKey.isEmpty()) {
      products = productRepository.findAll(searchKey.toLowerCase(), pageable);
    } else {
      products = productRepository.findAll(pageable);
    }
    return PageUtil.getTransformedPaginatedResponse(products, this::getProductTOFromProduct);
  }

  public ProductTO get(String productId) throws ApiException {
    Product product = getProduct(productId);
    return getProductTOFromProduct(product);
  }

  public ProductTO save(ProductTO productTO) throws ApiException {
    validate(productTO);
    Brand brand = brandService.getBrand(productTO.getBrand().getId());
    Category category = categoryService.getCategory(productTO.getCategory().getId());
    validateBrandCategoryRelation(brand, category);
    Product newProduct = getProductFromProductTO(productTO, brand, category);
    return getProductTOFromProduct(productRepository.save(newProduct));
  }

  public ProductTO update(ProductTO prodTO) throws ApiException {
    Product prod = getProduct(prodTO.getId());
    prod.setName(prodTO.getName() != null ? prodTO.getName() : prod.getName());
    prod.setAlias(prodTO.getAlias() != null ? prodTO.getAlias() : prod.getAlias());
    prod.setShortDescription(prodTO.getShortDescription() != null ? prodTO.getShortDescription() : prod.getName());
    prod.setFullDescription(prodTO.getFullDescription() != null ? prodTO.getFullDescription() : prod.getName());
    prod.setActive(prodTO.getActive() != null ? prodTO.getActive() : prod.isActive());
    prod.setInStock(prodTO.getInStock() != null ? prodTO.getInStock() : prod.isInStock());
    prod.setDiscountPercent(prodTO.getDiscountPercent() != null ? prodTO.getDiscountPercent() : prod.getDiscountPercent());
    prod.setCost(prodTO.getCost() != null ? prodTO.getCost() : prod.getCost());
    prod.setPrice(prodTO.getPrice() != null ? prodTO.getPrice() : prod.getPrice());
    prod.setLength(prodTO.getLength() != null ? prodTO.getLength() : prod.getLength());
    prod.setWidth(prodTO.getWidth() != null ? prodTO.getWidth() : prod.getWidth());
    prod.setHeight(prodTO.getHeight() != null ? prodTO.getHeight() : prod.getHeight());
    prod.setWeight(prodTO.getWeight() != null ? prodTO.getWeight() : prod.getWeight());
    prod.setMainImage(prodTO.getMainImage() != null ? prodTO.getMainImage() : prod.getMainImage());
    updateBrandAndCategory(prodTO, prod);
    updateExtraImages(prodTO, prod);
    updateDetails(prodTO, prod);
    prod.setUpdated(new Date());
    Product updatedProd = productRepository.save(prod);
    return getProductTOFromProduct(updatedProd);
  }

  public void delete(String productId) throws ApiException {
    Product product = getProduct(productId);
    productRepository.deleteById(productId);
  }

  private Product getProduct(String productId) throws ApiException {
    if (productId == null) {
      throw new ApiException(Constants.PRODUCT_ID_MANDATORY);
    }
    Optional<Product> prodOptional = productRepository.findById(productId);
    if (prodOptional.isEmpty()) {
      String errMsg = String.format(Constants.PRODUCT_NOT_EXISTS, productId);
      throw new ApiException(errMsg);
    }
    return prodOptional.get();
  }

  private void updateBrandAndCategory(ProductTO prodTO, Product prod) throws ApiException {
    boolean brandCatRelationCheckRequired = false;
    Brand brand = prod.getBrand();;
    Category category = prod.getCategory();
    if (prodTO.getBrand() != null && !brand.getId().equals(prodTO.getBrand().getId())) {
      brand = brandService.getBrand(prodTO.getBrand().getId());
      prod.setBrand(brand);
      brandCatRelationCheckRequired = true;
    }
    if (prodTO.getCategory() != null && !category.getId().equals(prodTO.getCategory().getId())) {
      category = categoryService.getCategory(prodTO.getCategory().getId());
      prod.setCategory(category);
      brandCatRelationCheckRequired = true;
    }
    if (brandCatRelationCheckRequired) {
      validateBrandCategoryRelation(brand, category);
    }
  }

  private void updateExtraImages(ProductTO prodTO, Product prod) throws ApiException {
    if (prodTO.getExtraImages() != null) {
      prod.clearExtraImages();
      prodTO.getExtraImages().forEach(prod::addExtraImage);
    }
  }

  private void updateDetails(ProductTO prodTO, Product prod) throws ApiException {
    if (prodTO.getDetails() != null) {
      prod.clearDetails();
      for (ProductDetailTO detailTO : prodTO.getDetails()) {
        prod.addDetail(detailTO.getName(), detailTO.getValue());
      }
    }
  }

  private void validate(ProductTO productTO) throws ApiException {
    if (productTO.getBrand() == null || productTO.getBrand().getId() == null) {
      throw new ApiException(Constants.BRAND_ID_MANDATORY);
    }
    if (productTO.getCategory() == null || productTO.getCategory().getId() == null) {
      throw new ApiException(Constants.CATEGORY_ID_MANDATORY);
    }
    validateProductName(productTO.getName());
  }

  private void validateProductName(String name) throws ApiException {
    if (productRepository.findByName(name).isPresent()) {
      String errMsg = String.format(Constants.PRODUCT_NAME_EXISTS, name);
      throw new ApiException(errMsg);
    }
  }

  private void validateBrandCategoryRelation (Brand brand, Category category) throws ApiException {
    boolean categoryExists = false;
    for (Category cat : brand.getCategories()) {
      if (cat.getId().equals(category.getId())) {
        categoryExists = true;
        break;
      }
    }
    if (!categoryExists) {
      String errMsg = String.format(Constants.PRODUCT_CATEGORY_NOT_PART_OF_BRAND, category.getId(), brand.getId());
      throw new ApiException(errMsg);
    }
  }

  private Product getProductFromProductTO(ProductTO productTO, Brand brand, Category category) {
    Product product = Product.builder()
        .name(productTO.getName())
        .alias(productTO.getAlias() != null ? productTO.getAlias() : productTO.getName())
        .shortDescription(productTO.getShortDescription())
        .fullDescription(productTO.getFullDescription())
        .discountPercent(productTO.getDiscountPercent())
        .cost(productTO.getCost())
        .price(productTO.getPrice())
        .length(productTO.getLength())
        .width(productTO.getWidth())
        .height(productTO.getHeight())
        .weight(productTO.getWeight())
        .mainImage(productTO.getMainImage())
        .brand(brand)
        .category(category)
        .build();
    if (productTO.getExtraImages() != null) {
      productTO.getExtraImages().forEach(product::addExtraImage);
    }
    if (productTO.getDetails() != null) {
      productTO.getDetails()
          .forEach(detailTO -> product.addDetail(detailTO.getName(), detailTO.getValue()));
    }
    return product;
  }

  private ProductTO getProductTOFromProduct(Product product) {
    return ProductTO.builder()
        .id(product.getId())
        .name(product.getName())
        .alias(product.getAlias())
        .shortDescription(product.getShortDescription())
        .fullDescription(product.getFullDescription())
        .created(product.getCreated())
        .updated(product.getUpdated())
        .active(product.isActive())
        .inStock(product.isInStock())
        .discountPercent(product.getDiscountPercent())
        .cost(product.getCost())
        .price(product.getPrice())
        .length(product.getLength())
        .width(product.getWidth())
        .height(product.getHeight())
        .weight(product.getWeight())
        .mainImage(product.getMainImage())
        .category(getProductCategoryTO(product.getCategory()))
        .brand(getProductBrandTO(product.getBrand()))
        .extraImages(product.getExtraImages().stream()
            .map(ProductImage::getName).collect(Collectors.toList()))
        .details(product.getDetails().stream()
            .map(this::getProductDetailTO).collect(Collectors.toList()))
        .build();
  }

  private ProductCategoryTO getProductCategoryTO(Category category){
    return ProductCategoryTO.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }

  private ProductBrandTO getProductBrandTO(Brand brand){
    return ProductBrandTO.builder()
        .id(brand.getId())
        .name(brand.getName())
        .build();
  }

  private ProductDetailTO getProductDetailTO(ProductDetail productDetail) {
    return ProductDetailTO.builder()
        .id(productDetail.getId())
        .name(productDetail.getName())
        .value(productDetail.getValue())
        .build();
  }
}
