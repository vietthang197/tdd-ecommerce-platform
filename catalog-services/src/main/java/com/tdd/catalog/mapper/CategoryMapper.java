package com.tdd.catalog.mapper;

import com.tdd.catalog.dto.CategoryDto;
import com.tdd.catalog.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(List<Category> categories);
}
