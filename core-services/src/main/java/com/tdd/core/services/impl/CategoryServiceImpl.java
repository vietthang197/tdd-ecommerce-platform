package com.tdd.core.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.core.dto.BaseResponse;
import com.tdd.core.dto.CategoryDto;
import com.tdd.core.entity.Category;
import com.tdd.core.exeception.InvalidDataRequestException;
import com.tdd.core.mapper.CategoryMapper;
import com.tdd.core.repository.CategoryRepository;
import com.tdd.core.services.CategoryService;
import com.tdd.core.utils.DateUtils;
import com.tdd.core.vm.CreateCategoryVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
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
    public BaseResponse<CategoryDto> createCategory(CreateCategoryVM request) throws JsonProcessingException {
        Optional<Category> categoryOptional = Optional.empty();
        if (Strings.isNotBlank(request.getParentCategoryId())) {
            categoryOptional = categoryRepository.findByCategoryId(request.getParentCategoryId());
            if (categoryOptional.isEmpty())
                throw new InvalidDataRequestException("Invalid parentCategoryId!");
        }

        Category category = Category.builder()
                .activeStartDate(DateUtils.toLocalDateTime(request.getActiveStartDate(), DateUtils.DEFAULT_DATE_TIME_FORMAT))
                .activeEndDate(DateUtils.toLocalDateTime(request.getActiveEndDate(), DateUtils.DEFAULT_DATE_FORMAT))
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
            Map<String, String> attributes = mapper.readValue(request.getAttributes(), new TypeReference<Map<String, String>>() {});
            category.setAttributes(attributes);
        }
        categoryRepository.save(category);
        return BaseResponse.ok(categoryMapper.toDto(category));
    }
}
