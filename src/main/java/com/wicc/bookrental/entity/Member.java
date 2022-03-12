package com.wicc.bookrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_member",uniqueConstraints = {
        @UniqueConstraint(name="unique_member_email",columnNames = "email"),
        @UniqueConstraint(name="unique_member_mobileNumber",columnNames = "mobile_number")
})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "Member_SEQ_GEN")
    @SequenceGenerator(name="Member_SEQ_GEN",sequenceName = "Member_SEQ",allocationSize = 1)
    private Integer id;
    @Column(name="name",nullable = false,length = 25)
    private String name;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="mobile_number",nullable = false,length = 12)
    private String mobileNumber;
    @Column(name="address",nullable = false)
    private String address;

}
