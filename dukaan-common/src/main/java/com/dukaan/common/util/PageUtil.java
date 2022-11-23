package com.dukaan.common.util;

import com.dukaan.common.model.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtil {

  private PageUtil() {}

  public static Pageable getPageable(int page, int size) {
    page = page > 0 ? page - 1 : 0;
    return PageRequest.of(page, size);
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
}
