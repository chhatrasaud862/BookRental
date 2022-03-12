package com.wicc.bookrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_book",uniqueConstraints = {
        @UniqueConstraint(name="unique_book_isbn",columnNames = "isbn")
})
public class Book implements Serializable {
         @Id
        @GeneratedValue(generator = "book_sequence",strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(name = "book_sequence",sequenceName = "book_sequence")
        private Integer id;

         @Column(nullable = false)
        private String name;

         @Column(nullable = false)
        private String isbn;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date published_date;

        @Column(nullable = false)
        private Double rating;

        @Column(nullable = false)
        private Integer numberOfPage;

        @Column(nullable = false)
        private Integer stockCount;

        @Column(name = "file_path")
        private String coverPhotoPath;

        @ManyToMany
        @JoinTable(
                name = "tbl_book_author",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "author_id"))
        private List<Author> authorSet;
        @ManyToOne
        @JoinColumn(name = "category_id",referencedColumnName = "id" ,foreignKey = @ForeignKey(name = "FK_Book_Category"))
        private Category category;

    }
