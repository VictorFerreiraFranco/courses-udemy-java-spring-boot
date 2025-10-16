package udemy.courses.libraryapi.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.repository.AuthorRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository repository;

    public void validate(Author author) {
        if (authorExists(author))
            throw new DuplicateRecordException("Athor already exists");
    }

    private boolean authorExists(Author author) {
        Optional<Author> authorFound = repository.findByNameAndBirthDateAndNationality(
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
        );

        if (author.getId() == null)
            return authorFound.isPresent();

        return  authorFound.isPresent() && !author.getId().equals(authorFound.get().getId());
    }
}
