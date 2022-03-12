package com.wicc.bookrental.dto.conversion;

import com.wicc.bookrental.dto.CategoryDto;
import com.wicc.bookrental.entity.Category;

public class CategoryAndDto {
    public Category getCategory(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription()).build();
    }

}
