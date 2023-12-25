package com.tdd.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingDto<T> {
    private Integer totalPages;
    private Long totalElements;
    private List<T> content;
}
