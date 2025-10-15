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

import static udemy.courses.libraryapi.repository.specification.BookSpecification.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void update(Book book) {

        if (book.getId() == null)
            throw new IllegalArgumentException("Book id is null");

        bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Optional<Book> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public List<Book> search(
            String isbn, String title, GenderBook gender, Integer publishYear, String authorName
    ){
        Specification<Book> specification = (root, query, cb) -> cb.conjunction();

        if (isbn != null)
            specification = specification.and(isbnEqual(isbn));

        if (title != null)
            specification = specification.and(titleLike(title));

        if (gender != null)
            specification = specification.and(genderEqual(gender));

        if (publishYear != null)
            specification = specification.and(publishYearEqual(publishYear));

        if (authorName != null)
            specification = specification.and(authorNameLike(authorName));

        return bookRepository.findAll(specification);
    }


}
