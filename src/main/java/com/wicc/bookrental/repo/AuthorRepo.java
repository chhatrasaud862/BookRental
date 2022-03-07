package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
