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
@Table(name = "users")
public class User {

  @Id
  @Column(name = "id", length = 32, nullable = false)
  private String id;

  @Setter
  @Column(name = "email", length = 128, nullable = false, unique = true)
  private String email;

  @Setter
  @Column(name = "password", length = 64, nullable = false) // because length of encoded password = 64
  private String password;

  @Setter
  @Column(name = "first_name", length = 45, nullable = false)
  private String firstName;

  @Setter
  @Column(name = "last_name", length = 45, nullable = false)
  private String lastName;

  @Setter
  @Column(name = "photo_name", length = 64)
  private String photoName;

  @Setter
  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @Setter
  @ManyToMany
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  public User(User.Builder builder) {
    this.id = builder.id;
    this.email = builder.email;
    this.password = builder.password;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.photoName = builder.photoName;
    this.isActive = builder.isActive;
    this.roles = builder.roles;
  }

  public void addRole(Role role) {
    this.roles.add(role);
  }

  public static final class Builder {

    /***
     * Constructor to initialize default values
     */
    private Builder() {
      this.isActive = true;
    }

    /***
     * Custom private setter for id to make it not settable
     *
     * @param id String
     * @return Builder
     */
    private  User.Builder id(String id) {
      return this;
    }

    public User build() {
      this.id = generateId();
      return new User(this);
    }

    private String generateId() {
      return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
  }

}
