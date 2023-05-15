package com.example.librarymanagement.entities;

import com.example.librarymanagement.dto.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String coverImage;

    private String author;

    private Date releaseYear;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int totalPageNum;

    private int soldQuantity;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;
}
