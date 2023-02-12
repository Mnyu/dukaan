package com.dukaan.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_details")
public class ProductDetail {
  @Id
  @Column(name = "id", length = 50, nullable = false)
  private String id;

  @Setter
  @Column(name = "name", length = 256, nullable = false)
  private String name;

  @Setter
  @Column(name = "value", length = 256, nullable = false)
  private String value;

  @Setter
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public ProductDetail(ProductDetail.Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.value = builder.value;
    this.product = builder.product;
  }

  public static final class Builder {
    /***
     * Constructor to initialize default values
     */
    private Builder() {}

    /***
     * Custom private setter for id to make it not settable
     *
     * @param id String
     * @return Builder
     */
    private ProductDetail.Builder id(String id) {
          return this;
  }

    public ProductDetail build() {
      this.id = generateId();
      return new ProductDetail(this);
    }
    private String generateId() {
                  return UUID.randomUUID().toString();
          }
  }
}
