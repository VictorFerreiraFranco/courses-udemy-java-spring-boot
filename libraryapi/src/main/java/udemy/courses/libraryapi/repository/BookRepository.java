package udemy.courses.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByAuthor(Author author);

}
