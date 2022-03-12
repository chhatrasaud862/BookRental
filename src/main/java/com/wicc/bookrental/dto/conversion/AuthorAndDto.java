package com.wicc.bookrental.dto.conversion;

import com.wicc.bookrental.dto.AuthorDto;
import com.wicc.bookrental.entity.Author;

public class AuthorAndDto {
    public Author getAuthor(AuthorDto authorDto){
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setMobileNumber(author.getMobileNumber());
        return author;
    }
}
