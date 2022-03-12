package com.wicc.bookrental.service.book;


import com.wicc.bookrental.dto.BookDto;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookService  {
    BookDto saveBook(BookDto bookDto) throws IOException, ParseException;
    List<BookDto> findAll();
    BookDto findById(Integer id) throws IOException;
    void deleteBookById(Integer id);


}
