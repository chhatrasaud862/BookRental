package com.wicc.bookrental.service.impl;

import com.wicc.bookrental.dto.BookTransactionDto;
import com.wicc.bookrental.entity.Book;
import com.wicc.bookrental.entity.BookTransaction;
import com.wicc.bookrental.entity.Member;
import com.wicc.bookrental.enums.RentStatus;
import com.wicc.bookrental.repo.BookRepo;
import com.wicc.bookrental.repo.BookTransactionRepo;
import com.wicc.bookrental.service.bookTransaction.BookTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookTransactionServiceImpl implements BookTransactionService {
    @Autowired
    private BookTransactionRepo bookTransactionRepo;
    @Autowired
    private BookRepo bookRepo;
    @Override
    public BookTransactionDto saveBookTransaction(BookTransactionDto bookTransactionDto) {
        Book book=new Book();
        book.setId(bookTransactionDto.getId());
        Member member=new Member();
        member.setId(bookTransactionDto.getId());
        LocalDate localDate=LocalDate.now();
        LocalDate localDate1=localDate.plusDays(bookTransactionDto.getNumberOfDay());
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fromDate = localDate.format(formatter);
        String toDate =localDate1.format(formatter);
        Date date=null;
       Date date1=null;
        try{
            date=new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
            date1=new SimpleDateFormat("dd-MM-yyyy").parse(toDate);

       } catch (ParseException e) {
            e.printStackTrace();
        }
        bookTransactionDto.setFromDate(date);
        bookTransactionDto.setFromDate(date1);
        BookTransaction entity=BookTransaction.builder()
                .id(bookTransactionDto.getId())
                .code(bookTransactionDto.getCode())
                .fromDate(bookTransactionDto.getFromDate())
                .toDate(bookTransactionDto.getToDate())
                .rentStatus(bookTransactionDto.getRentStatus())
                .book(book)
                .member(member)
                .build();
        entity=bookTransactionRepo.save(entity);
        JpaRepository<Book, Integer> bookRepository;
        Book book1 = bookRepo.getById(entity.getBook().getId());
        book1.setStockCount(book1.getStockCount() - 1);
        bookRepo.save(book1);
        return BookTransactionDto.builder().id(entity.getId()).build();
    }

    @Override
    public List<BookTransactionDto> findAll() {
        List<BookTransaction> bookTransactionList = bookTransactionRepo.findAll();
        return bookTransactionList.stream().map(
                bookTransaction -> BookTransactionDto.builder()
                .id(bookTransaction.getId())
                .code(bookTransaction.getCode())
                .member(bookTransaction.getMember())
                .book(bookTransaction.getBook())
                .fromDate(bookTransaction.getFromDate())
                .toDate(bookTransaction.getToDate())
                .rentStatus(bookTransaction.getRentStatus())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public BookTransactionDto findById(Integer id) {
        Optional<BookTransaction> optionalTransaction = bookTransactionRepo.findById(id);
        if (optionalTransaction.isPresent()){
            BookTransaction bookTransaction = optionalTransaction.get();
            return BookTransactionDto.builder()
                    .id(bookTransaction.getId())
                    .code(bookTransaction.getCode())
                    .fromDate(bookTransaction.getFromDate())
                    .toDate(bookTransaction.getToDate())
                    .rentStatus(bookTransaction.getRentStatus())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookTransactionById(Integer id) {
        bookTransactionRepo.deleteById(id);

    }

    @Override
    public List<BookTransactionDto> findAllRentStatus(RentStatus rentStatus) {
        List<BookTransaction> bookTransactionList = bookTransactionRepo.findBookTransactionByRentStatus(rentStatus);
        return bookTransactionList.stream().map(
               bookTransaction -> BookTransactionDto.builder()
                .id(bookTransaction.getId())
                .code(bookTransaction.getCode())
                .member(bookTransaction.getMember())
                .book(bookTransaction.getBook())
                .fromDate(bookTransaction.getFromDate())
                .toDate(bookTransaction.getToDate())
                .rentStatus(bookTransaction.getRentStatus())
                .build()
        ).collect(Collectors.toList());
    }
}
