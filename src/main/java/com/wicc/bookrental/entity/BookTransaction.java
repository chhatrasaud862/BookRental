package com.wicc.bookrental.entity;

import com.wicc.bookrental.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_book_transaction", uniqueConstraints = @UniqueConstraint(
                name="unique_code",
                columnNames = "book_code"
        )
)
public class BookTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_is_gen")
    @SequenceGenerator(name = "transaction_is_gen", sequenceName = "transaction_is_gen", allocationSize = 1)
    private Integer id;

    @Column(name = "book_code", nullable = false)
    private String bookCode;

    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column( nullable = false)
    private Integer noOfDays;

    @Column(name = "rent_status", nullable = false)
    private RentStatus rentStatus;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_transaction_book"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_transaction_member"))
    private Member member;
}