package com.tdd.catalog.vm;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateGeneralProductInfoVM implements BaseRequest {
    @DecimalMin(value = "0", message = "Giá bán thường không được âm")
    @DecimalMax(value = "10000000000", message = "Giá bán thường không quá 10.000.000.000 vnđ")
    private BigDecimal regularPrice;

    @DecimalMin(value = "0", message = "Giá ưu đãi không được âm")
    @DecimalMax(value = "10000000000", message = "Giá ưu đãi không quá 10.000.000.000 vnđ")
    private BigDecimal salePrice;
}
