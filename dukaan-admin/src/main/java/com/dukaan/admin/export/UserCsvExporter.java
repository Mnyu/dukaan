package com.dukaan.admin.export;

import com.dukaan.common.entity.Role;
import com.dukaan.common.entity.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserCsvExporter {

  private static final String FILE_NAME_PREFIX = "users_";
  private static final String FILE_NAME_SUFFIX = ".csv";

  public static void export(HttpServletResponse httpServletResponse, Iterable<User> users)
    throws IOException {
    String fileName = getTimestampedFileName();
    setResponseProps(httpServletResponse, fileName);

    CSVFormat csvFormat = CSVFormat.Builder.create()
      .setHeader(UserCsvHeader.class)
      .setAllowMissingColumnNames(true)
      .build();

    try (CSVPrinter printer = new CSVPrinter(httpServletResponse.getWriter(), csvFormat)) {
      for (User user : users) {
        List<String> roleNames = user.getRoles().stream().map(Role::getName).toList();
        printer.printRecord(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
          user.isActive(), roleNames);
      }
    }
  }

  private static void setResponseProps(HttpServletResponse response, String fileName) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
  }

  private static String getTimestampedFileName() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    String timestamp = dateFormat.format(new Date());
    return FILE_NAME_PREFIX + timestamp + FILE_NAME_SUFFIX;
  }
}
