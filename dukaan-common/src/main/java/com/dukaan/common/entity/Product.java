package com.dukaan.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product {
  @Id
  @Column(name = "id", length = 50, nullable = false)
  private String id;

  @Setter
  @Column(name = "name", length = 256, nullable = false, unique = true)
  private String name;

  @Setter
  @Column(name = "alias", length = 256, nullable = false, unique = true)
  private String alias;

  @Setter
  @Column(name = "short_description", length = 512, nullable = false)
  private String shortDescription;

  @Setter
  @Column(name = "full_description", length = 4096, nullable = false)
  private String fullDescription;

  @Setter
  @Column(name = "created", nullable = false)
  private Date created;

  @Setter
  @Column(name = "updated", nullable = false)
  private Date updated;

  @Setter
  @Column(name = "is_active", nullable = false)
  private boolean active;

  @Setter
  @Column(name = "in_stock", nullable = false)
  private boolean inStock;

  @Setter
  @Column(name = "discount_percent", nullable = false)
  private float discountPercent;

  @Setter
  @Column(name = "cost", nullable = false)
  private float cost;

  @Setter
  @Column(name = "price", nullable = false)
  private float price;

  @Setter
  @Column(name = "length", nullable = false)
  private float length;

  @Setter
  @Column(name = "width", nullable = false)
  private float width;

  @Setter
  @Column(name = "height", nullable = false)
  private float height;

  @Setter
  @Column(name = "weight", nullable = false)
  private float weight;

  @Setter
  @Column(name = "main_image", nullable = false)
  private String mainImage;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @Setter
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ProductImage> extraImages;

  @Setter
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductDetail> details;

  public Product(Product.Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.alias = builder.alias;
    this.shortDescription = builder.shortDescription;
    this.fullDescription = builder.fullDescription;
    this.created = builder.created;
    this.updated = builder.updated;
    this.active = builder.active;
    this.inStock = builder.inStock;
    this.discountPercent = builder.discountPercent;
    this.cost = builder.cost;
    this.price = builder.price;
    this.length = builder.length;
    this.width = builder.width;
    this.height = builder.height;
    this.weight = builder.weight;
    this.category = builder.category;
    this.brand = builder.brand;
    this.mainImage = builder.mainImage;
    this.extraImages = builder.extraImages;
    this.details = builder.details;
  }

  public void clearExtraImages() {
    this.extraImages.clear();
  }

  public void addExtraImage(String imgName) {
    this.extraImages.add(ProductImage.builder()
        .name(imgName)
        .product(this)
        .build());
  }

  public void addDetail(String name, String value) {
    this.details.add(ProductDetail.builder()
        .name(name)
        .value(value)
        .product(this)
        .build());
  }

  public void clearDetails() {
    this.details.clear();
  }

  public static final class Builder {
    /***
     * Constructor to initialize default values
     */
    private Builder() {
      this.active = true;
      this.inStock = true;
      this.updated = new Date();
      this.extraImages = new HashSet<>();
      this.details = new ArrayList<>();
    }

    /***
     * Custom private setter for id to make it not settable
     *
     * @param id String
     * @return Builder
     */
    private Product.Builder id(String id) {
          return this;
  }

    /***
     * Custom private setter for created to make it not settable
     *
     * @param created Date
     * @return Builder
     */
    private Product.Builder created(Date created) {
      return this;
    }

    public Product build() {
      this.id = generateId();
      this.created = new Date();
      return new Product(this);
    }
    private String generateId() {
      return UUID.randomUUID().toString();
    }
  }
}
