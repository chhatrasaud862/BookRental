package com.wicc.bookrental.dto.conversion;

import com.wicc.bookrental.dto.MemberDto;
import com.wicc.bookrental.entity.Member;

public class MemberAndDto {
    public Member getMember(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .address(memberDto.getAddress())
                .mobileNumber(memberDto.getMobileNumber())
                .email(memberDto.getEmail()).build();
    }
}
