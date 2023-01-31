package com.dukaan.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandTO {
  private String id;
  private String name;
  private String logo;
  private Set<BrandCategoryTO> categories = new HashSet<>();

}


