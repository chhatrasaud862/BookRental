package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.BookTransaction;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface BookTransactionRepo extends JpaRepository<BookTransaction,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update tbl_book_transaction rent_status set rent_status = 1 where id = :id", nativeQuery = true)
    void updateRentStatus(@Param("id") Integer id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update tbl_book_transaction return_date set to_date = :date where id = :id", nativeQuery = true)
    void updateReturnDate(@Param("date") Date date, @Param("id") Integer id);
}
