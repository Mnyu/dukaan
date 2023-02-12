package com.dukaan.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTO {
  private String id;
  private String name;
  private String alias;
  private String shortDescription;
  private String fullDescription;
  private Date created;
  private Date updated;
  private Boolean active;
  private Boolean inStock;
  private Float discountPercent;
  private Float cost;
  private Float price;
  private Float length;
  private Float width;
  private Float height;
  private Float weight;
  private String mainImage;
  private ProductCategoryTO category;
  private ProductBrandTO brand;
  private List<String> extraImages;
  private List<ProductDetailTO> details;
}
