package udemy.courses.libraryapi.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
import udemy.courses.libraryapi.exceptions.FieldInvalidException;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.repository.BookRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private static final int YEAR_REQUIRED_FOR_PRICE = 2020;

    private final BookRepository repository;

    public void validate(Book book) {
        if (bookExistsWithIsbn(book))
            throw new DuplicateRecordException("Book with isbn already exists");

        if (isPriceRequiredAndIsNull(book))
            throw new FieldInvalidException("price", "Price is required");
    }

    private boolean bookExistsWithIsbn(Book book) {
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());

        if (book.getId() == null)
            return bookFound.isPresent();

        return bookFound.isPresent() && !book.getId().equals(bookFound.get().getId());
    }

    private boolean isPriceRequiredAndIsNull(Book book) {
        return book.getPrice() == null &&
                book.getPublishDate().getYear() >= YEAR_REQUIRED_FOR_PRICE;
    }
}
