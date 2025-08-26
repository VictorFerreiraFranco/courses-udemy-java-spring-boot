package udemy.courses.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Data
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String isbn;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name = "publication_date")
    private LocalDate publishDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private GenderBook gender;

    @Column(precision = 18, scale = 2)
    private Double price;
//    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "id_author")
    private Author author;


}
