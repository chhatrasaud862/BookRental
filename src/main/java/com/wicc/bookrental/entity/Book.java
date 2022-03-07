package com.wicc.bookrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_book",uniqueConstraints = {
        @UniqueConstraint(name="unique_book_isbn",columnNames = "isbn")
})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "Book_SEQ_GEN")
    @SequenceGenerator(name="Book_SEQ_GEN",sequenceName = "Book_SEQ",allocationSize = 1)
    private Integer id;
    @Column(name="name",nullable = false,length = 20)
    private String name;
    @Column(name="total_page",nullable = false)
    private Integer totalPage;
    @Column(name="isbn")
    private Integer isbn;
    @Column(name="rating")
    private Double rating;
    @Column(name="stock_count")
    private Integer stockCount;
    @Column(name="publish_data",updatable = false)
    private Date publishDate;
    @Column(name="photo",columnDefinition = "TEXT")
    private String filePath;
    @ManyToOne
            (fetch = FetchType.LAZY ,targetEntity = Category.class)
    @JoinColumn(name="category_id",foreignKey = @ForeignKey(name="FK_BOOK_CATEGORYID"))
    private Category category;
     @ManyToMany(fetch=FetchType.LAZY)
     @JoinTable(name="tbl_book_author",foreignKey = @ForeignKey(name="FK_JT_AUTHOR_BOOK"))
    private List<Author> authorList;
     @OneToMany(fetch = FetchType.LAZY,mappedBy = "book")
     private List<BookTransaction> bookTransaction;
}
