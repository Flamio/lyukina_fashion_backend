package com.mmenshikov.lyukinafashion.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorDto {
    private String error;
    private String stackTrace;
}
