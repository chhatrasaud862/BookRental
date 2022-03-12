package com.wicc.bookrental.dto;
import com.wicc.bookrental.entity.Book;
import com.wicc.bookrental.entity.Member;
import com.wicc.bookrental.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class BookTransactionDto {
        private Integer id;
        private String bookCode;
        private String fromDate;
        private Integer noOfDay;
        private String returnDate;
        private RentStatus rentStatus;
        private Integer book;
        private Integer member;

    }


