package com.example.librarymanagement.entities;

import com.example.librarymanagement.dto.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String coverImage;

    private String author;

    private Date releaseDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer totalPageNum;

    private Integer soldQuantity;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Review> reviews;
}
