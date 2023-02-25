package com.dukaan.admin.util;

public final class Constants {

  public static final String EXPORT_CSV ="csv";

  //Error Constants
  public static final String USER_EMAIL_EXISTS ="User with email %s already exists.";
  public static final String USER_NOT_EXISTS ="No User exists with id %s.";
  public static final String USER_EMAIL_NOT_EXISTS ="No User exists with email %s.";
  public static final String USER_ID_MANDATORY ="User id is mandatory.";
  public static final String UNABLE_TO_EXPORT_CSV ="Unable to export users data to csv.";
  public static final String WRONG_EXPORT_FORMAT ="Wrong format for export specified.";
  public static final String ACCOUNT_UPDATE_ID_MISMATCH ="User id does not match with the logged-in user id.";

  public static final String CATEGORY_NOT_EXISTS ="No category exists with id %s.";
  public static final String CATEGORY_ID_MANDATORY ="Category id is mandatory.";
  public static final String CATEGORY_NAME_EXISTS ="Category with name %s already exists.";
  public static final String CATEGORY_ALIAS_EXISTS ="Category with alias %s already exists.";
  public static final String CATEGORY_CANNOT_BE_DELETED ="Category %s cannot be deleted as it has sub-categories.";

  public static final String BRAND_NOT_EXISTS ="No brand exists with id %s.";
  public static final String BRAND_ID_MANDATORY ="Brand id is mandatory.";
  public static final String BRAND_NAME_EXISTS ="Brand with name %s already exists.";

  public static final String PRODUCT_CATEGORY_NOT_PART_OF_BRAND ="Category %s does not belong to brand %s.";
  public static final String PRODUCT_NAME_EXISTS ="Product with name %s already exists.";
  public static final String PRODUCT_ID_MANDATORY ="Product id is mandatory.";
  public static final String PRODUCT_NOT_EXISTS ="No product exists with id %s.";


  public static final String PARENT_CATEGORY_DELIMITER= "$";
}
