package com.tdd.catalog.repository;

import com.tdd.catalog.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>, ListPagingAndSortingRepository<Category, String> {

    Optional<Category> findByCategoryIdAndIsDeleted(String categoryId, String isDeleted);
    Page<Category> findAllByIsDeleted(String isDeleted, Pageable pageable);
    Optional<Category> findByUrlAndIsDeleted(String url, String isDeleted);
    Optional<Category> findByUrl(String url);
}
