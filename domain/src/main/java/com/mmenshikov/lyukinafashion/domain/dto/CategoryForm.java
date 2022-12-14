package com.mmenshikov.lyukinafashion.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Validated
public class CategoryForm {

    @NotBlank
    private String name;

    private Boolean isNew;
}
