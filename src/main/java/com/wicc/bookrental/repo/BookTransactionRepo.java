package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.BookTransaction;
import com.wicc.bookrental.enums.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookTransactionRepo extends JpaRepository<BookTransaction,Integer> {
    List<BookTransaction> findBookTransactionByRentStatus(RentStatus rentStatus);
}
