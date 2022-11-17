//package com.dukaan.admin.user;
//
//import com.dukaan.common.entity.Role;
//import com.dukaan.common.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////@Rollback(false)
//public class UserRepositoryTests {
//
//  @Autowired
//  private UserRepository userRepo;
//
//  @Autowired
//  private TestEntityManager entityManager;
//
//  @Test
//  public void testCreateUser() {
//          Role adminRole = entityManager.find(Role.class, "611F4954CCE948489D4A89F1842DD00E");
//          User adminUser = User.builder()
//              .email("admin@dukaan.com")
//              .password("admin")
//              .firstName("Admin")
//              .lastName("Admin")
//              .build();
//          adminUser.addRole(adminRole);
//          User savedUser = userRepo.save(adminUser);
//          assertThat(savedUser.getId()).isEqualTo(adminUser.getId());
//  }
//}
