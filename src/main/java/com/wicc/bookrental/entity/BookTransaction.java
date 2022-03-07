package com.wicc.bookrental.entity;

import com.wicc.bookrental.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_book_transaction",uniqueConstraints = {
        @UniqueConstraint(name="unique_book_transaction_code",columnNames = "code")
})
public class BookTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "BookTransaction_SEQ_GEN")
    @SequenceGenerator(name="BookTransaction_SEQ_GEN",sequenceName = "BookTransaction_SEQ",allocationSize = 1)
    private Integer id;
    @Column(name="code",length = 50)
    private String code;
    @Column(name="from_date",length = 100)
    private Date fromDate;
    @Column(name="to_date",length = 100)
    private Date toDate;
    @Enumerated(value=EnumType.STRING)
    private RentStatus rentStatus;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Book.class)
    @JoinColumn(name="book_id",foreignKey = @ForeignKey(name="FK_BOOKTRANSACTION_BOOKID"))
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Member.class)
    @JoinColumn(name="member_id",foreignKey = @ForeignKey(name="FK_BOOKTRANSACTION_MEMBERID"))
    private Member member;
}
