package com.dukaan.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "brands")
public class Brand {

  @Id
  @Column(name = "id", length = 50, nullable = false)
  private String id;

  @Setter
  @Column(name = "name", length = 50, nullable = false, unique = true)
  private String name;

  @Setter
  @Column(name = "logo", length = 64)
  private String logo;

  @Setter
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "brands_categories",
      joinColumns = @JoinColumn(name = "brand_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();

  public Brand(Brand.Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.logo = builder.logo;
    this.categories = builder.categories;
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
    private Brand.Builder id(String id) {
      return this;
    }

    public Brand build() {
      this.id = generateId();
      return new Brand(this);
    }

    private String generateId() {
      return UUID.randomUUID().toString();
    }
  }

}
