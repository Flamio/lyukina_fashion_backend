package com.mmenshikov.lyukinafashion.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryDto {
    private Long id;
    private String name;

    @JsonProperty("new")
    private Boolean isNew;
}
