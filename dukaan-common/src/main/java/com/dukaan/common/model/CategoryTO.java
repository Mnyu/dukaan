package com.dukaan.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryTO {
  private String id;
  private String name;
  private String alias;
  private String image;
  private boolean active;
  private String parent;
  private List<CategoryTO> subCategories = new ArrayList<>();
}
