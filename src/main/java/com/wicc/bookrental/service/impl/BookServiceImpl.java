package com.wicc.bookrental.service.impl;

import com.wicc.bookrental.components.FileStorageComponent;
import com.wicc.bookrental.dto.BookDto;
import com.wicc.bookrental.dto.ResponseDto;
import com.wicc.bookrental.entity.Book;
import com.wicc.bookrental.repo.BookRepo;
import com.wicc.bookrental.service.book.BookService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private FileStorageComponent fileStorageComponent;

    @Override
    public BookDto saveBook(BookDto bookDto) throws IOException {
        Book entity=null;
        ResponseDto responseDto=fileStorageComponent.storeFile(bookDto.getMultipartFile());
        if(responseDto.isStatus()) {
            entity = Book.builder()
                    .id(bookDto.getId())
                    .name(bookDto.getName())
                    .totalPage(bookDto.getTotalPage())
                    .isbn(bookDto.getIsbn())
                    .rating(bookDto.getRating())
                    .publishDate((Date) bookDto.getPublishDate())
                    .filePath(responseDto.getMessage())
                    .build();
           entity=bookRepo.save(entity);
           return BookDto.builder().id(entity.getId()).build();
        }else
        {
            log.error(responseDto.getMessage());
            return null;
        }
    }

    @Override
    public List<BookDto> findAll() {
        List<Book>bookList=bookRepo.findAll();
      return bookList.stream().map(
                book -> BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .isbn(book.getIsbn())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer id) throws IOException {
        Optional<Book> optionalBook=bookRepo.findById(id);
        if(optionalBook.isPresent()){
            Book book=optionalBook.get();
                return BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .totalPage(book.getTotalPage())
                        .isbn(book.getIsbn())
                        .rating(book.getRating())
                        .stockCount(book.getStockCount())
                        .publishDate((Data) book.getPublishDate())
                        .filePath(fileStorageComponent.returnFile(book.getFilePath()))
                        .build();
        }
        return null;
    }

    @Override
    public void deleteBookById(Integer id) {
        Optional<Book> optionalBook=bookRepo.findById(id);
        if(optionalBook.isPresent()) {
            bookRepo.deleteById(id);
        }

    }
}