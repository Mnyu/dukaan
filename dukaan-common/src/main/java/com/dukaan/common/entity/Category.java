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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @Column(name = "id", length = 50, nullable = false)
  private String id;

  @Setter
  @Column(name = "name", length = 120, nullable = false, unique = true)
  private String name;

  @Setter
  @Column(name = "alias", length = 64, nullable = false, unique = true)
  private String alias;

  @Setter
  @Column(name = "image", length = 64)
  private String image;

  @Setter
  @Column(name = "is_active", nullable = false)
  private boolean active;

  @Setter
  @ManyToOne()
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private Set<Category> subCategories = new HashSet<>();

  public Category(Category.Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.alias = builder.alias;
    this.image = builder.image;
    this.active = builder.active;
    this.parent = builder.parent;
    this.subCategories = builder.subCategories;
  }

  public static final class Builder {
    /***
     * Constructor to initialize default values
     */
    private Builder() {
  this.active = true;
}

    /***
     * Custom private setter for id to make it not settable
     *
     * @param id String
     * @return Builder
     */
    private Category.Builder id(String id) {
      return this;
    }

    public Category build() {
      this.id = generateId();
      this.subCategories = new HashSet<>();
      return new Category(this);
    }

    private String generateId() {
      return UUID.randomUUID().toString();
    }
  }

}
