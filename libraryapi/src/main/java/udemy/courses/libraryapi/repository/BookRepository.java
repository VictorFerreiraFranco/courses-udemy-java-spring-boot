package udemy.courses.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;

import java.util.List;
import java.util.UUID;

/**
 * @see BookRepositoryTest
 */
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByAuthor(Author author);

    List<Book> findByTitle(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query(" select l from Book as l order by l.title ")
    List<Book> findByAllPersonalized();

    @Query(" select a from Book l join l.author a")
    List<Author> findAuthorByBookPersonalized();

    @Query("""
        select l.gender
        from Book l
        join l.author a
        where a.nationality = 'Brazilian'
        order by l.gender
    """)
    List<String> findAllGenderAuthorBrazilian();
}
