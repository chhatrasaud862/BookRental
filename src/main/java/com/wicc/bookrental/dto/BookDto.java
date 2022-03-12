package com.wicc.bookrental.dto;

import com.wicc.bookrental.entity.Author;
import com.wicc.bookrental.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String name;
    private Integer numberOfPage;
    private String isbn;
    private String published_date;
    private Double rating;
    private Integer stockCount;
    private Category category;
    private List<Author> authorList;
    private MultipartFile multipartFile;
    private String coverPhotoPath;
}
