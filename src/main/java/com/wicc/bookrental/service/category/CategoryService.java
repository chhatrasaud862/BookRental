package com.wicc.bookrental.service.category;

import com.wicc.bookrental.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    List<CategoryDto> findAll();
    CategoryDto findById(Integer id);
    void deleteCategoryId(Integer id);
}
