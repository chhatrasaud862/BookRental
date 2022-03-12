package com.wicc.bookrental.service.impl;

import com.wicc.bookrental.component.FileStorageComponent;
import com.wicc.bookrental.dto.BookDto;
import com.wicc.bookrental.dto.ResponseDto;
import com.wicc.bookrental.entity.Book;
import com.wicc.bookrental.repo.BookRepo;
import com.wicc.bookrental.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;
    private final FileStorageComponent fileStorageComponent;

    public BookServiceImpl(BookRepo bookRepo, CategoryServiceImpl categoryService, AuthorServiceImpl authorService, FileStorageComponent fileStorageComponent) {
        this.bookRepo = bookRepo;
        this.categoryService = categoryService;
        this.authorService = authorService;

        this.fileStorageComponent = fileStorageComponent;
    }
    public BookDto saveBook(BookDto bookDto) throws IOException, ParseException {
        Book entity=new Book();
        ResponseDto responseDto= fileStorageComponent.storeFile(bookDto.getMultipartFile());
           if(responseDto.isStatus())
           {
               entity.setId(bookDto.getId());
               entity.setName(bookDto.getName());
               entity.setNumberOfPage(bookDto.getNumberOfPage());
               entity.setIsbn(bookDto.getIsbn());
               entity.setRating(bookDto.getRating());
               entity.setPublished_date(new SimpleDateFormat("dd-MM-yyyy").parse(bookDto.getPublished_date()));
               entity.setStockCount(bookDto.getStockCount());
               entity.setCoverPhotoPath(responseDto.getMessage());
               entity.setAuthorSet(bookDto.getAuthorList());
               entity.setCategory(bookDto.getCategory());
              entity= bookRepo.save(entity);
           }
        return BookDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .numberOfPage(entity.getNumberOfPage())
                .isbn(entity.getIsbn())
                .rating(entity.getRating())
                .published_date(String.valueOf(new SimpleDateFormat("dd-MM-yyyy").parse(bookDto.getPublished_date())))
                .stockCount(entity.getStockCount())
                .coverPhotoPath(entity.getCoverPhotoPath())
                .authorList(entity.getAuthorSet())
                .category(entity.getCategory())
                .build();

    }

    @Override
    public List<BookDto> findAll() {
        return bookRepo.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(book -> {
            try {
                return BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .isbn(book.getIsbn())
                        .numberOfPage(book.getNumberOfPage())
                        .stockCount(book.getStockCount())
                        .rating(book.getRating())
                        .published_date(new SimpleDateFormat("dd-MM-yyyy").format(book.getPublished_date()))
                        .coverPhotoPath(fileStorageComponent.base64Encoded(book.getCoverPhotoPath()))
                        .category(book.getCategory())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer id) throws IOException {
        Optional<Book>optionalBook = bookRepo.findById(id);
        Book book = null;
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
            return BookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .isbn(book.getIsbn())
                    .numberOfPage(book.getNumberOfPage())
                    .stockCount(book.getStockCount())
                    .rating(book.getRating())
                    .published_date(new SimpleDateFormat("dd-mm-yyyy").format(book.getPublished_date()))
                    .coverPhotoPath(fileStorageComponent.base64Encoded(book.getCoverPhotoPath()))
                    .authorList(book.getAuthorSet())
                    .category(book.getCategory()).build();

        }
        return BookDto.builder().id(book.getId()).build();
    }


    @Override
    public void deleteBookById(Integer id) {
        bookRepo.deleteById(id);

    }
    public void updateBookStock(Integer bookId, Integer stackValue) {
    bookRepo.updateBookStock(bookId,stackValue);
    }


}
