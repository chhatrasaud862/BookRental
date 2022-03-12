package com.wicc.bookrental.service.impl;

import com.wicc.bookrental.dto.MemberDto;
import com.wicc.bookrental.entity.Member;
import com.wicc.bookrental.repo.MemberRepo;
import com.wicc.bookrental.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;

    public MemberServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        Member entity=new Member();
        entity.setId(memberDto.getId());
        entity.setName(memberDto.getName());
        entity.setEmail(memberDto.getEmail());
        entity.setMobileNumber(memberDto.getMobileNumber());
        entity.setAddress(memberDto.getAddress());
        entity=memberRepo.save(entity);
        return MemberDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .address(entity.getAddress())
                .build();
    }

    @Override
    public List<MemberDto> findAll() {
         return memberRepo.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(
              member -> MemberDto.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .email(member.getEmail())
                        .mobileNumber(member.getMobileNumber())
                        .build()
        ).collect(Collectors.toList());
    }
    @Override
    public MemberDto findById(Integer id) {
        Member member;
        Optional<Member> optionalMember=memberRepo.findById(id);
        if(optionalMember.isPresent())
        {
            member=optionalMember.get();
            return MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .mobileNumber(member.getMobileNumber())
                    .address(member.getAddress())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteMemberById(Integer id) {
        memberRepo.deleteById(id);

    }
}
