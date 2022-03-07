package com.wicc.bookrental.service.member;
import com.wicc.bookrental.dto.MemberDto;

import java.util.List;

public interface MemberService{
    MemberDto saveMember(MemberDto memberDto);
       List<MemberDto> findAll();
    MemberDto findById(Integer id);
    void deleteMemberById(Integer id);


}
