package com.wicc.bookrental.service.impl;
import com.wicc.bookrental.dto.BookTransactionDto;
import com.wicc.bookrental.dto.conversion.BookAndDto;
import com.wicc.bookrental.dto.conversion.MemberAndDto;
import com.wicc.bookrental.entity.Book;
import com.wicc.bookrental.entity.BookTransaction;
import com.wicc.bookrental.enums.RentStatus;
import com.wicc.bookrental.repo.BookTransactionRepo;
import com.wicc.bookrental.service.bookTransaction.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookTransactionServiceImpl implements BookTransactionService {
   @Autowired
    private final BookTransactionRepo bookTransactionRepo;
   private final MemberServiceImpl memberService;
   private final BookServiceImpl bookService;
    public BookTransactionServiceImpl(BookTransactionRepo bookTransactionRepo,
                                      MemberServiceImpl memberService, BookServiceImpl bookService) {
        this.bookTransactionRepo = bookTransactionRepo;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @Override
    public BookTransactionDto saveBookTransaction(BookTransactionDto bookTransactionDto) throws ParseException, IOException {
        BookTransaction entity = BookTransaction.builder()
                .id(bookTransactionDto.getId())
                .bookCode(bookTransactionDto.getBookCode())
                .rentStatus(RentStatus.RENT)
                .fromDate(new Date())
                .noOfDays(bookTransactionDto.getNoOfDay())
                .member(new MemberAndDto().getMember(
                        memberService.findById(bookTransactionDto.getMember())))
                .book(new BookAndDto().getBook(
                        bookService.findById(bookTransactionDto.getBook())))
                .build();
        entity = bookTransactionRepo.save(entity);
        Book book = new BookAndDto().getBook(bookService.findById(bookTransactionDto.getBook()));
        bookService.updateBookStock(book.getId(),book.getStockCount()-1);

        return BookTransactionDto.builder().id(entity.getId()).build();
    }
    @Override
    public List<BookTransactionDto> findAll() {
        return bookTransactionRepo.findAll().stream().map(transaction -> {
            return BookTransactionDto.builder()
                    .id(transaction.getId())
                    .rentStatus(transaction.getRentStatus())
                    .member(transaction.getMember().getId())
                    .bookCode(transaction.getBookCode()).build();
        }).collect(Collectors.toList());
    }
    @Override
    public BookTransactionDto findById(Integer id) throws ParseException {
        Optional<BookTransaction> optionalBookTransaction = bookTransactionRepo.findById(id);
        BookTransaction bookTransaction;
        if (optionalBookTransaction.isPresent()) {
            bookTransaction = optionalBookTransaction.get();
            return BookTransactionDto.builder()
                    .id(bookTransaction.getId())
                    .bookCode(bookTransaction.getBookCode())
                    .member(bookTransaction.getMember().getId())
                    .book(bookTransaction.getBook().getId())
                    .returnDate(String.valueOf(bookTransaction.getReturnDate()))
                    .fromDate(String.valueOf(bookTransaction.getFromDate()))
                    .noOfDay(bookTransaction.getNoOfDays())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteBookTransaction(Integer id) {

    }
    public List<BookTransaction> findAllTransaction() {
        return bookTransactionRepo.findAll();
    }
    public void saveReturnTransaction(BookTransactionDto bookTransactionDto) throws IOException, ParseException {
        bookTransactionRepo.updateRentStatus(bookTransactionDto.getId());
             bookTransactionRepo.updateReturnDate(new Date(),bookTransactionDto.getId());
        BookTransactionDto transactionDto1= findById(bookTransactionDto.getId());
        try {
            bookService.updateBookStock(transactionDto1.getId()
                    , bookService.findById(transactionDto1.getId())
                            .getStockCount() +1 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BookTransaction> findAllRent() {
        List<BookTransaction> transactionListOfRent = new ArrayList<>();
        List<BookTransaction> transactions = bookTransactionRepo.findAll();
        for (Integer i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getRentStatus().ordinal() == RentStatus.RENT.ordinal()) {
                transactionListOfRent.add(transactions.get(i));
            }
        }
        return transactionListOfRent;
    }
    public BookTransaction findTransactionByBookCode(String bookCode){
        BookTransaction bookTransaction = null;
        List<BookTransaction> transactionList = bookTransactionRepo.findAll();
        for(Integer i =0;i<transactionList.size();i++){
            if(transactionList.get(i).getBookCode().equals(bookCode) && transactionList.get(i).getReturnDate() == null){
                bookTransaction = transactionList.get(i);
            }
        }
        return bookTransaction;
    }
    public List<BookTransaction> findAllReturnTransaction() {
        List<BookTransaction> transactionListOfReturn = new ArrayList<>();
        List<BookTransaction> transactions = bookTransactionRepo.findAll();
        for (Integer i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getRentStatus().ordinal() == RentStatus.RETURN.ordinal()) {
                transactionListOfReturn.add(transactions.get(i));
            }
        }
        return transactionListOfReturn;
    }
}
