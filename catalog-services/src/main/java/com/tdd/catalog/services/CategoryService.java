package com.tdd.catalog.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.dto.CategoryDto;
import com.tdd.catalog.dto.PagingDto;
import com.tdd.catalog.vm.CreateCategoryVM;
import com.tdd.catalog.vm.UpdateGeneralCategoryVM;

public interface CategoryService {
    BaseResponse<CategoryDto> createCategory(CreateCategoryVM request) throws JsonProcessingException;

    BaseResponse<CategoryDto> updateGeneralCategory(UpdateGeneralCategoryVM request);

    BaseResponse<PagingDto<CategoryDto>> findAll(Integer page, Integer size);

    BaseResponse<CategoryDto> findByUrl(String categoryUrl);
}
