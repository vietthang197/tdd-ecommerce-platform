package com.tdd.catalog.repository;

import com.tdd.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>, ListPagingAndSortingRepository<Category, String> {

    Optional<Category> findByCategoryId(String categoryId);
}
