package com.tdd.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private String categoryId;
    private String name;
    private String url;
    private String metaTitle;
    private String metaDescription;
    private CategoryDto parentCategory;
    private String activeStartDate;
    private String activeEndDate;
    private Map<String, String> attributes;
    private String description;
    private String overrideGeneratedUrl;
}
