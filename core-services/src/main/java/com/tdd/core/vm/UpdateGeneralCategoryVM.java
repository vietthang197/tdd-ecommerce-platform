package com.tdd.core.vm;

import com.tdd.core.annotation.ValidDate;
import com.tdd.core.annotation.ValidJson;
import com.tdd.core.entity.Category;
import com.tdd.core.utils.DateUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGeneralCategoryVM implements Serializable {

    @NotBlank(message = "categoryId can not be blank")
    @Size(max = 255, message = "categoryId over size")
    private String categoryId;

    @ValidDate(pattern = DateUtils.DEFAULT_DATE_TIME_FORMAT, message = "activeStartDate invalid", allowBlank = false)
    private String activeStartDate;

    @ValidDate(pattern = DateUtils.DEFAULT_DATE_TIME_FORMAT, message = "activeEndDate invalid", allowBlank = true)
    private String activeEndDate;

    // format JSON
    @Size(max = 10000, message = "attributes over size")
    @ValidJson(allowBlank = true, checkHashMapJson = true, message = "attributes không hợp lệ")
    private String attributes;

    @Size(max = 255, message = "name max length")
    @NotBlank(message = "name can not be blank")
    private String name;

    @Size(max = Category.URL_LENGTH, message = "url max length")
    private String url;

    @Size(max = Category.DESCIPTION_LENGTH, message = "description max length")
    private String description;

    @Size(max = 255, message = "taxCode max length")
    private String taxCode;

    @Size(max = 255, message = "metaTitle max length")
    private String metaTitle;

    @Size(max = 255, message = "metaDescription max length")
    private String metaDescription;

    @Size(max = Category.OVERRIDE_GENERATED_URL, message = "overrideGeneratedUrl max length")
    private String overrideGeneratedUrl;
}
