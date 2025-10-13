package udemy.courses.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@ToString(exclude = {"author"})
@EntityListeners(AuditingEntityListener.class)
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
    private BigDecimal price;

    @ManyToOne(
//        cascade = {CascadeType.ALL},
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_author")
    private Author author;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "id_user")
    private UUID idUser;
}
