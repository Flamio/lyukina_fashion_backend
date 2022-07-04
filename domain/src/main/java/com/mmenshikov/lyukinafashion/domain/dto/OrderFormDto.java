package com.mmenshikov.lyukinafashion.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Validated
public class OrderFormDto {

    @NotNull
    @Valid
    private List<Product> products;

    @NotNull
    @Valid
    private Info info;

    @Data
    @Accessors(chain = true)
    @Validated
    public static class Product {
        @NotNull
        @Min(1)
        @JsonProperty("product_id")
        private Long productId;

        @NotNull
        @Min(1)
        @JsonProperty("size_id")
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

        @NotNull
        @NotEmpty
        private String address;

        @NotNull
        @NotEmpty
        @JsonProperty("contact_type")
        private String contactType;

        @NotNull
        @NotEmpty
        @JsonProperty("contact_name")
        private String contactName;
    }
}
