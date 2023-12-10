package com.tdd.core.mapper;

import com.tdd.core.dto.CategoryDto;
import com.tdd.core.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(List<Category> categories);
}
