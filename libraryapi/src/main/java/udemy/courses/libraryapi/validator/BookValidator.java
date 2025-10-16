package udemy.courses.libraryapi.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.repository.BookRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository repository;

    public void validate(Book book) {
        if (bookExistsWithIsbn(book))
            throw new DuplicateRecordException("Book with isbn already exists");
    }

    private boolean bookExistsWithIsbn(Book book) {
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());

        if (book.getId() == null)
            return bookFound.isPresent();

        return bookFound.isPresent() && !book.getId().equals(bookFound.get().getId());
    }

}
