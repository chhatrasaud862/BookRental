package com.wicc.bookrental.dto;

import com.wicc.bookrental.entity.Author;
import com.wicc.bookrental.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String name;
    private Integer totalPage;
    private Integer isbn;
    private Double rating;
    private Integer stockCount;
    private Data publishDate;
    private String filePath;
    private MultipartFile multipartFile;
    private Integer categoryDto;
    private List<Integer> authorDto;
    private CategoryDto category;
    private List<AuthorDto> authors;
    public static class BookDtoBuilder{
        public BookDtoBuilder category(Category category){
            this.category = CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            return this;
        }

        public BookDtoBuilder authors(List<Author> authors){
            this.authors = authors.stream().map(entity -> AuthorDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .mobileNumber(entity.getMobileNumber())
                    .build()).collect(Collectors.toList());

            return this;
        }
    }


}
