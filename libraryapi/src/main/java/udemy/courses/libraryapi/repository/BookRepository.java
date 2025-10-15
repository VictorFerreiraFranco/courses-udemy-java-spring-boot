package udemy.courses.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByAuthor(Author author);

    List<Book> findByTitle(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("select l from Book as l order by l.title ")
    List<Book> findByAllPersonalized();

    @Query("select a from Book l join l.author a")
    List<Author> findAuthorByBookPersonalized();

    @Query("""
        select l.gender
        from Book l
        join l.author a
        where a.nationality = 'Brasileiro'
        order by l.gender
    """)
    List<String> findAllGenderAuthorBrazilian();

    @Query("select l from Book l where l.gender = :gender order by :parmOrder")
    List<Book> findAllByGender(
        @Param("gender") GenderBook genderBook,
        @Param("parmOrder") String columnOrder
    );

    @Query("select l from Book l where l.gender = ?1 order by ?2")
    List<Book> findAllByGenderPositionalParameter(GenderBook genderBook, String columnOrder);

    @Modifying
    @Transactional
    @Query("delete from Book where gender = ?1")
    void deleteByGender(GenderBook genderBook);

    @Modifying
    @Transactional
    @Query("update Book set publishDate = ?1")
    void updatePublishDate(LocalDate date);

    boolean existsByAuthor(Author author);
}
