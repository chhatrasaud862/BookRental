package com.wicc.bookrental.repo;

import com.wicc.bookrental.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Integer> {
}
