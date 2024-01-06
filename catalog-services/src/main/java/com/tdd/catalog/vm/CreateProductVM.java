package com.tdd.catalog.vm;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductVM implements BaseRequest {

    @NotBlank(message = "Tên sản phẩm không được trống")
    @Size(max = 255, message = "Tên sản phẩm không quá 255 ký tự")
    private String name;

    @Size(max = Integer.MAX_VALUE /2, message = "Mô tả sản phẩm quá dài")
    private String description;

    @Pattern(regexp = "^(SIMPLE|GROUPED|EXTERNAL|VARIANT)$", message = "Loại sản phẩm không hợp lệ")
    private String productType;

    @DecimalMin(value = "0", message = "Default Price không được âm")
    @DecimalMax(value = "10000000000", message = "Default Price không được quá 10.000.000.000 đ")
    private BigDecimal defaultPrice;
}
