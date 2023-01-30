package com.dukaan.common.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @Column(name = "id", length = 50, nullable = false)
  private String id;

  @Column(name = "name", length = 40, nullable = false, unique = true)
  private String name;

  @Column(name = "description", length = 150, nullable = false)
  private String description;

  private Role(Role.Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
  }

  public static final class Builder {

    /***
     * Custom private setter for id to make it not settable
     *
     * @param id String
     * @return Builder
     */
    private Role.Builder id(String id) {
      return this;
    }

    public Role build() {
      this.id = generateId();
      return new Role(this);
    }

    private String generateId() {
      return UUID.randomUUID().toString();
    }
  }

}
