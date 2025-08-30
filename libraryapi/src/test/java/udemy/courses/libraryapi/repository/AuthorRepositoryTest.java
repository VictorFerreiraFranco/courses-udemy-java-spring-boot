package udemy.courses.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void SaveTest(){
        Author author = new Author();
        author.setName("Jack");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(1990, 1, 1));

        var authorSavable = authorRepository.save(author);
        System.out.println("Author:" + authorSavable);
    }

    @Test
    public void updateTest(){
        var id = UUID.fromString("cab40730-e8d6-40fb-857f-c3616d9f5f4f");

        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            System.out.println("Author:" + author);

            author.setBirthDate(LocalDate.of(1960, 1, 20));

            authorRepository.save(author);
        }
    }

    @Test
    public void listTest(){
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Author count: " + authorRepository.count());
    }

    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("cab40730-e8d6-40fb-857f-c3616d9f5f4f");
        authorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("cab40730-e8d6-40fb-857f-c3616d9f5f4f");
        var author = authorRepository.findById(id).get();
        authorRepository.delete(author);
    }

    @Test
    public void saveAuthorWithBooksTest(){
        Author author = new Author();
        author.setName("Maria");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(2000, 1, 1));
        author.setBooks(new ArrayList<>());

        Book book = new Book();
        book.setIsbn("3434-343");
        book.setTitle("Book Test");
        book.setPrice(BigDecimal.valueOf(102));
        book.setGender(GenderBook.ROMANCE);
        book.setPublishDate(LocalDate.of(2015,10,10));
        book.setAuthor(author);

        author.getBooks().add(book);

        Book book2 = new Book();
        book2.setIsbn("1123-3232");
        book2.setTitle("Book empty");
        book2.setPrice(BigDecimal.valueOf(50));
        book2.setGender(GenderBook.SCIENCE);
        book2.setPublishDate(LocalDate.of(2020,10,10));
        book2.setAuthor(author);

        author.getBooks().add(book2);

        authorRepository.save(author);
    }
}
