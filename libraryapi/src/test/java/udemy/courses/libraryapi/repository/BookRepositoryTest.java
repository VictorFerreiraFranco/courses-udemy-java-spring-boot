package udemy.courses.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
                .findById(UUID.fromString("a32f2d07-b894-43ae-bb07-3eba5526e590"))
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
        Book book = bookRepository
                .findById(UUID.fromString("dcd467ce-f2d0-4dbe-9965-c553eea2d7db"))
                .orElse(null);

        Author author = authorRepository
                .findById(UUID.fromString("e4523dda-4059-48ab-aaab-4c3fefc63191"))
                .orElse(null);

        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    @Transactional
    void findBook(){
        Book book = bookRepository
                .findById(UUID.fromString("dcd467ce-f2d0-4dbe-9965-c553eea2d7db"))
                .orElse(null);

        System.out.println("Book:" + book.getTitle());
        System.out.println("Author:" + book.getAuthor().getName());
    }

    @Test
    void findByTitleTest(){
        List<Book> books = bookRepository.findByTitle("Book Title");
        books.forEach(System.out::println);
    }

    @Test
    void findByTitleLikeTest(){
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("Book");
        books.forEach(System.out::println);
    }

    @Test
    void findByTitleWithQueryTest(){
        List<Book> books = bookRepository.findByAllPersonalized();
        books.forEach(System.out::println);
    }

    @Test
    void findAuthorByBookQueryTest(){
        List<Author> books = bookRepository.findAuthorByBookPersonalized();
        books.forEach(System.out::println);
    }

    @Test
    void findAllGenderAuthorBrazilianTest(){
        List<String> books = bookRepository.findAllGenderAuthorBrazilian();
        books.forEach(System.out::println);
    }
}