//package com.dukaan.admin.repository;
//
//import com.dukaan.common.entity.Category;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest(showSql = false)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(true)
//public class CategoryRepositoryTests {
//
//  @Autowired
//  private CategoryRepository categoryRepo;
//
//  @Test
//  public void testCreateCategory() {
//    testCreateParentAndSubCategories("Computers",
//        List.of("Laptops", "Desktops", "Computer Components"));
//    testCreateParentAndSubCategories("Electronics",
//        List.of("Cameras", "Mobiles"));
//  }
//
//  @Test
//  public void testGetCategory() {
//    Optional<Category> categoryOptional = categoryRepo.findById("6DD60D494C0D4BF2AC6F7B9814668CD0");
//    Category category = categoryOptional.get();
//    Set<Category> subCategories = category.getSubCategories();
//    assertThat(subCategories.size()).isGreaterThan(0);
//  }
//
//  @Test
//  public void testPrintHierarchicalCategories() {
//    Iterable<Category> categories = categoryRepo.findAll();
//    for (Category category : categories) {
//      if (category.getParent() == null) {
//        System.out.println(category.getName());
//        Set<Category> subCategories = category.getSubCategories();
//        for (Category subCategory : subCategories) {
//          System.out.println("--" + subCategory.getName());
//          printChildren(subCategory, 1);
//        }
//      }
//    }
//  }
//
//  private void printChildren(Category parent, int subLevel) {
//    int newSubLevel = subLevel + 1;
//    for (Category subCategory : parent.getSubCategories()) {
//      for(int i=0; i < newSubLevel; i++) {
//        System.out.print("--");
//      }
//      System.out.println(subCategory.getName());
//      printChildren(subCategory, newSubLevel);
//    }
//  }
//
//  private void testCreateParentAndSubCategories(String parentCategory, List<String> subCategories) {
//    Category parent = testCreateCategory(parentCategory, null);
//    subCategories.forEach(subCategory -> testCreateCategory(subCategory, parent));
//  }
//
//  private Category testCreateCategory(String categoryName, Category parent){
//    Category category = getCategory(categoryName, parent);
//    Category savedCategory = categoryRepo.save(category);
//    assertThat(savedCategory.getId()).isNotNull();
//    return savedCategory;
//  }
//
//  private Category getCategory(String name, Category parent) {
//    return Category.builder()
//        .name(name).alias(getAlias(name))
//        .parent(parent)
//        .build();
//  }
//
//  private String getAlias(String name) {
//    return name.replace(" ", "-").toLowerCase();
//  }
//
//}
