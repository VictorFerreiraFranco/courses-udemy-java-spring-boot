package udemy.courses.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.courses.libraryapi.model.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
