package com.wicc.bookrental.service.bookTransaction;

import com.wicc.bookrental.dto.BookTransactionDto;
import com.wicc.bookrental.enums.RentStatus;

import java.util.List;

public interface BookTransactionService {
    BookTransactionDto saveBookTransaction(BookTransactionDto bookTransactionDto);
    List<BookTransactionDto> findAll();
    BookTransactionDto findById(Integer id);
    void deleteBookTransactionById(Integer id);
    List<BookTransactionDto> findAllRentStatus(RentStatus rentStatus);
}
