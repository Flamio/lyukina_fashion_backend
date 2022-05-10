package com.mmenshikov.lyukinafashion.category.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryForm {
    private String name;

    private Boolean isNew;
}
