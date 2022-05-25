package com.mmenshikov.lyukinafashion.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
@Validated
public class OrderFormDto {
    private Long productId;

    @NotBlank
    private String address;

    @Pattern(regexp = "^\\+\\d\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$", message = "wrong phone format")
    private String phone;
}
