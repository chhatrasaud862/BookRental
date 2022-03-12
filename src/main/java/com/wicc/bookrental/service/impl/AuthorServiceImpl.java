package com.wicc.bookrental.service.impl;

import com.wicc.bookrental.dto.AuthorDto;
import com.wicc.bookrental.entity.Author;
import com.wicc.bookrental.repo.AuthorRepo;
import com.wicc.bookrental.service.author.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorDto saveAuthor(AuthorDto authorDto) {
        Author entity= new Author();
        entity.setId(authorDto.getId());
        entity.setName(authorDto.getName());
        entity.setEmail(authorDto.getEmail());
        entity.setMobileNumber(authorDto.getMobileNumber());
        entity=authorRepo.save(entity);
        return AuthorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .build();
    }
    @Override
    public List<AuthorDto> findAll() {
             return authorRepo.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(
                author -> AuthorDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .email(author.getEmail())
                        .mobileNumber(author.getMobileNumber())
                        .build()

        ).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Integer id) {
        Author author;
       Optional<Author>optionalAuthor= authorRepo.findById(id);
       if(optionalAuthor.isPresent()) {
           author = optionalAuthor.get();
           return AuthorDto.builder()
                   .id(author.getId())
                   .name(author.getName())
                   .email(author.getEmail())
                   .mobileNumber(author.getMobileNumber())
                   .build();
       }
        return null;
    }

    @Override
    public void deleteAuthorById(Integer id) {
        authorRepo.deleteById(id);

    }
}
