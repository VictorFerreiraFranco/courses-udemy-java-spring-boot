package udemy.courses.libraryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;
import udemy.courses.libraryapi.repository.AuthorRepository;
import udemy.courses.libraryapi.repository.BookRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionalService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void execute(){
        Author author = new Author();
        author.setName("João");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(1995, 1, 1));

        authorRepository.save(author);

        if (author.getName().equals("João")){
            throw new RuntimeException("RollBack");
        }

        Book book = new Book();
        book.setIsbn("3434-3434");
        book.setTitle("Book lian bookk");
        book.setPrice(BigDecimal.valueOf(12.34));
        book.setGender(GenderBook.FANTASY);
        book.setPublishDate(LocalDate.of(2015,10,10));
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Transactional
    public void execute2(){
        Author author = new Author();
        author.setName("João");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(1995, 1, 1));

        authorRepository.saveAndFlush(author);

        Book book = new Book();
        book.setIsbn("3434-3434");
        book.setTitle("Book lian bookk");
        book.setPrice(BigDecimal.valueOf(12.34));
        book.setGender(GenderBook.FANTASY);
        book.setPublishDate(LocalDate.of(2015,10,10));
        book.setAuthor(author);

        bookRepository.saveAndFlush(book);

        if (author.getName().equals("João")){
            throw new RuntimeException("RollBack");
        }
    }


    @Transactional
    public void updateNotSave(){
        var book = bookRepository
                .findById(UUID.fromString("dadbb4b8-e281-4e10-9000-20da0e0e86f8"))
                .orElse(null);

        book.setPublishDate(LocalDate.now());
    }
}
