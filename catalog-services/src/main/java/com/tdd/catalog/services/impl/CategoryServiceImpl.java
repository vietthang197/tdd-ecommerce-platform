package com.tdd.catalog.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.catalog.dto.BaseResponse;
import com.tdd.catalog.dto.CategoryDto;
import com.tdd.catalog.dto.PagingDto;
import com.tdd.catalog.entity.Category;
import com.tdd.catalog.exeception.InvalidDataRequestException;
import com.tdd.catalog.mapper.CategoryMapper;
import com.tdd.catalog.repository.CategoryRepository;
import com.tdd.catalog.services.CategoryService;
import com.tdd.catalog.utils.DateUtils;
import com.tdd.catalog.vm.CreateCategoryVM;
import com.tdd.catalog.vm.UpdateGeneralCategoryVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final Logger logger = LogManager.getLogger();

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ObjectMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ObjectMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<CategoryDto> createCategory(CreateCategoryVM request) throws JsonProcessingException {
        Optional<Category> categoryOptional = Optional.empty();
        if (Strings.isNotBlank(request.getParentCategoryId())) {
            categoryOptional = categoryRepository.findByCategoryId(request.getParentCategoryId());
            if (categoryOptional.isEmpty())
                throw new InvalidDataRequestException("Invalid parentCategoryId!");
        }

        Category category = Category.builder()
                .activeStartDate(DateUtils.toLocalDateTime(request.getActiveStartDate(), DateUtils.DEFAULT_DATE_TIME_FORMAT))
                .activeEndDate(DateUtils.toLocalDateTime(request.getActiveEndDate(), DateUtils.DEFAULT_DATE_TIME_FORMAT))
                .name(request.getName())
                .url(request.getUrl())
                .description(request.getDescription())
                .taxCode(request.getTaxCode())
                .metaTitle(request.getMetaTitle())
                .metaDescription(request.getMetaDescription())
                .overrideGeneratedUrl(request.getOverrideGeneratedUrl())
                .build();

        categoryOptional.ifPresent(category::setParentCategory);

        if (Strings.isNotBlank(request.getAttributes())) {
            Map<String, String> attributes = mapper.readValue(request.getAttributes(), new TypeReference<>() {
            });
            category.setAttributes(attributes);
        }
        categoryRepository.save(category);
        CategoryDto response = categoryMapper.toDto(category);
        return BaseResponse.ok(response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<CategoryDto> updateGeneralCategory(UpdateGeneralCategoryVM request) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryId(request.getCategoryId());
        if (categoryOptional.isEmpty())
            throw new InvalidDataRequestException("Invalid categoryId");

        Category category = categoryOptional.get();
        category.setActiveStartDate(DateUtils.toLocalDateTime(request.getActiveStartDate(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        category.setActiveEndDate(DateUtils.toLocalDateTime(request.getActiveEndDate(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        category.setName(request.getName());
        category.setUrl(request.getUrl());
        category.setDescription(request.getDescription());
        category.setTaxCode(request.getTaxCode());
        category.setMetaTitle(request.getMetaTitle());
        category.setMetaDescription(request.getMetaDescription());
        category.setOverrideGeneratedUrl(request.getOverrideGeneratedUrl());

        categoryRepository.save(category);
        return BaseResponse.ok(categoryMapper.toDto(category));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<PagingDto<CategoryDto>> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
        return BaseResponse.ok(new PagingDto<>(categoryPage.getTotalPages(), categoryPage.getTotalElements(), categoryMapper.toListDto(categoryPage.getContent())));
    }
}
