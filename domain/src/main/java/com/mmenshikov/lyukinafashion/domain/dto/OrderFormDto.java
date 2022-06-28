package com.mmenshikov.lyukinafashion.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Validated
public class OrderFormDto {

    @NotBlank
    private List<Product> products;

    @NotBlank
    private Info info;

    @Data
    @Accessors(chain = true)
    @Validated
    public static class Product {
        @NotBlank
        @Min(1)
        private Long productId;

        @NotBlank
        @Min(1)
        private Long sizeId;

        @NotNull
        @Min(1)
        private Integer quantity;
    }

    @Data
    @Accessors(chain = true)
    @Validated
    public static class Info {

        @Email
        private String email;

        @Pattern(regexp = "^\\+\\d\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$", message = "wrong phone format")
        @NotBlank
        private String phone;

        @NotBlank
        private String address;

        @NotBlank
        private String contactType;
    }
}
