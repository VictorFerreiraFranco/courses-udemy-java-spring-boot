package udemy.courses.libraryapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;
import udemy.courses.libraryapi.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Optional<Book> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public List<Book> search(
            String isbn, String nameAuthor, GenderBook gender, Integer publishYear
    ){
        Specification<Book> specification = null;

        return bookRepository.findAll(specification);
    }
}
