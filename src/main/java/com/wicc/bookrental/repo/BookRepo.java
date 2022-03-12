package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BookRepo extends JpaRepository<Book,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update tbl_book stock_count set stock_count = :stockValue WHERE id = :book_id",nativeQuery = true)
    void updateBookStock(@Param("book_id") Integer book_id, @Param("stockValue") Integer stockValue);
}
