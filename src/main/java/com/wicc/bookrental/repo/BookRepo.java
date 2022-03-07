package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {
}
