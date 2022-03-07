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
@Table(name="tbl_category")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "Category_SEQ_GEN")
        @SequenceGenerator(name="Category_SEQ_GEN",sequenceName = "Category_SEQ",allocationSize = 1)
        private Integer id;
        @Column(name="name",nullable = false)
        private String name;
        @Column(name="description",nullable = false)
        private String description;
        //FetchType.EAGER is auto update the mapping class object and LAZY is only one get
        @OneToMany
                (fetch = FetchType.LAZY,mappedBy = "category")
        private List<Book> bookList;
}
