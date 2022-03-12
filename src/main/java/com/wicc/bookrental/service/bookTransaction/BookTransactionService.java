package com.wicc.bookrental.service.bookTransaction;

import com.wicc.bookrental.dto.BookTransactionDto;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
@Repository
public interface BookTransactionService {
BookTransactionDto saveBookTransaction(BookTransactionDto bookTransactionDto) throws ParseException, IOException;
List<BookTransactionDto> findAll();
BookTransactionDto findById(Integer id) throws ParseException;
void deleteBookTransaction(Integer id);
}