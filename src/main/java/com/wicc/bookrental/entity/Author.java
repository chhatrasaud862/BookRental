package com.wicc.bookrental.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_author",uniqueConstraints = {
        @UniqueConstraint(name="unique_author_email",columnNames = "email"),
        @UniqueConstraint(name = "unique_author_mobileNumber",columnNames ="mobile_number")
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "Author_SEQ_GEN")
    @SequenceGenerator(name="Author_SEQ_GEN",sequenceName = "Author_SEQ",allocationSize = 1)
    private Integer id;
    @Column(name="name",nullable = false,length = 20)
    private String name;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="mobile_number",nullable = false,length = 12)
    private String mobileNumber;
}
