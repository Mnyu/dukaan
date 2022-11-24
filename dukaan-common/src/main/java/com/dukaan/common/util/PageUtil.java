package com.dukaan.common.util;

import com.dukaan.common.model.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtil {

  private PageUtil() {}

  public static Pageable getPageable(int page, int size) {
    page = page > 0 ? page - 1 : 0;
    return PageRequest.of(page, size);
  }

  public static Pageable getPageable(int page, int size, String[] sortParams) {
    page = page > 0 ? page - 1 : 0;
    return PageRequest.of(page, size, getSort(sortParams));
  }

  public static <T>PaginatedResponse<T> getPaginatedResponse(Page<T> page) {
    return PaginatedResponse.<T>builder()
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .currentPage(page.getNumber() + 1)
        .data(page.getContent())
        .build();
  }

  public static <T, E> PaginatedResponse<E> getTransformedPaginatedResponse(Page<T> page,
      Function<T, E> mapper){
    List<E> transformedDataList = page.getContent().stream().map(mapper).collect(Collectors.toList());
    return PaginatedResponse.<E>builder()
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .currentPage(page.getNumber() + 1)
        .data(transformedDataList)
        .build();
  }

  private static Sort getSort(String[] sortParams) {
    List<Sort.Order> orders = new ArrayList<>();
    // 1. http://localhost:8282/time-cards?sort=prop1,asc
    // 2. http://localhost:8282/time-cards?sort=prop1&sort=prop2
    // #1 and #2 will both have sortParams array with length == 2 like [prop1,asc] and [prop1,prop2]
    // The below if condition is to cover for [prop1,asc], rest cases covered by else part.
    if (sortParams.length == 2 && (Constants.SORT_ASC.equals(sortParams[1])
        || Constants.SORT_DESC.equals(sortParams[1]))) {
      orders.add(new Sort.Order(getSortDirection(sortParams[1]),sortParams[0]));
    } else {
      for (String sortParam : sortParams) {
        if (sortParam.contains(",")) {
          String[] sortParamArr = sortParam.split(",");
          orders.add(new Sort.Order(getSortDirection(sortParamArr[1]), sortParamArr[0]));
        } else {
          orders.add(new Sort.Order(getSortDirection(Constants.SORT_ASC), sortParam));
        }
      }
    }
    return Sort.by(orders);
  }

  private static Sort.Direction getSortDirection(String direction) {
    return direction.equals(Constants.SORT_DESC) ? Sort.Direction.DESC
        : Sort.Direction.ASC;
  }
}
