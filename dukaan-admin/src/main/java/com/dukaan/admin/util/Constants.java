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
}
