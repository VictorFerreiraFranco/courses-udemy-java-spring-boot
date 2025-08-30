package udemy.courses.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest(){
        Book book = new Book();

        book.setIsbn("3434-34334");
        book.setTitle("Book Title");
        book.setPrice(BigDecimal.valueOf(12.34));
        book.setGender(GenderBook.FANTASY);
        book.setPublishDate(LocalDate.of(2015,10,10));

        Author author = authorRepository
                .findById(UUID.fromString("e4523dda-4059-48ab-aaab-4c3fefc63191"))
                        .orElse(null);

        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    void saveCascadeTest(){
        Book book = new Book();

        book.setIsbn("3434-34334");
        book.setTitle("Book Title");
        book.setPrice(BigDecimal.valueOf(12.34));
        book.setGender(GenderBook.FANTASY);
        book.setPublishDate(LocalDate.of(2015,10,10));

        Author author = new Author();
        author.setName("João da Silva");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(1995, 1, 1));

        book.setAuthor(author);

        bookRepository.save(book);
    }


    @Test
    void updateAuthorByBookTest()
    {
        var book = bookRepository
                .findById(UUID.fromString("dcd467ce-f2d0-4dbe-9965-c553eea2d7db"))
                .orElse(null);

        var author = authorRepository
                .findById(UUID.fromString("e4523dda-4059-48ab-aaab-4c3fefc63191"))
                .orElse(null);

        book.setAuthor(author);

        bookRepository.save(book);
    }
}