package com.wicc.bookrental.dto.conversion;
import com.wicc.bookrental.dto.BookDto;
import com.wicc.bookrental.entity.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookAndDto {
    public Book getBook(BookDto bookDto) throws ParseException {
        return Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .isbn(bookDto.getIsbn())
                .numberOfPage(bookDto.getNumberOfPage())
                .rating(bookDto.getRating())
                .stockCount(bookDto.getStockCount())
                .published_date(new SimpleDateFormat("dd-MM-yyyy").parse(bookDto.getPublished_date()))
                .authorSet(bookDto.getAuthorList())
                .category(bookDto.getCategory())
                .build();
    }
}
