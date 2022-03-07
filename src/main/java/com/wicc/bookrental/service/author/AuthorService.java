package com.wicc.bookrental.service.author;

import com.wicc.bookrental.dto.AuthorDto;

import java.util.List;
public interface AuthorService{
    AuthorDto saveAuthor(AuthorDto authorDto);
    List<AuthorDto> findAll();
    AuthorDto findById(Integer id);
    void deleteAuthorById(Integer id);
}
