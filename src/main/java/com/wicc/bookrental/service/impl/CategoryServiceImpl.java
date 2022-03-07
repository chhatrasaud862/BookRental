package com.wicc.bookrental.service.impl;

import com.wicc.bookrental.dto.CategoryDto;
import com.wicc.bookrental.entity.Category;
import com.wicc.bookrental.repo.CategoryRepo;
import com.wicc.bookrental.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category entity=new Category();
        entity.setId(categoryDto.getId());
        entity.setName(categoryDto.getName());
        entity.setDescription(categoryDto.getDescription());
      entity= categoryRepo.save(entity);

        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();

    }
    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList=categoryRepo.findAll();
        return categoryList.stream().map(
                category-> CategoryDto.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        Category category;
       Optional<Category> optionalCategory=categoryRepo.findById(id);
       if(optionalCategory.isPresent())
       {
           category=optionalCategory.get();
           return CategoryDto.builder()
                   .id(category.getId())
                   .name(category.getName())
                   .description(category.getDescription())
                   .build();
       }
        return null;
    }

    @Override
    public void deleteCategoryId(Integer id) {
        categoryRepo.deleteById(id);

    }
}
