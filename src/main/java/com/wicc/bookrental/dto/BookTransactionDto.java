package com.wicc.bookrental.dto;

import com.wicc.bookrental.entity.Book;
import com.wicc.bookrental.entity.Member;
import com.wicc.bookrental.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookTransactionDto {
    private Integer id;
    private String code;
    private Date fromDate;
    private Date toDate;
    private RentStatus rentStatus;
    private Integer numberOfDay;
    private Integer bookDto;
    private Integer memberDto;
    private BookDto book;
    private MemberDto member;
 public static class BookTransactionDtoBuilder
 {
    public BookTransactionDtoBuilder book(Book book)
    {
        this.book=BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .isbn(book.getIsbn())
                .build();
        return this;
    }
   public BookTransactionDtoBuilder member(Member member)
   {
       this.member=MemberDto.builder()
               .id(member.getId())
               .address(member.getAddress())
               .email(member.getEmail())
               .mobileNumber(member.getMobileNumber())
               .build();
       return this;
   }
 }



}
