package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
