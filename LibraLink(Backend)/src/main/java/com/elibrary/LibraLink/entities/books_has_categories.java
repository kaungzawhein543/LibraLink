package com.elibrary.LibraLink.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="books_has_categories")
public class books_has_categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book_id;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category_id;
}
