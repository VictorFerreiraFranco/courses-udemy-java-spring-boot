package udemy.courses.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.courses.libraryapi.model.Book;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
