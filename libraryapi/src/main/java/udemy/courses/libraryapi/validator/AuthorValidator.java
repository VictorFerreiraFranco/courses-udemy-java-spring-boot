package udemy.courses.libraryapi.validator;

import org.springframework.stereotype.Component;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.repository.AuthorRepository;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void validate(Author author) {

        if (authorExists(author))
            throw new DuplicateRecordException("Athor already exists");
    }

    private boolean authorExists(Author author) {
        Optional<Author> authorOptional = repository.findByNameAndBirthDateAndNationality(
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
        );

        if (author.getId() == null)
            return authorOptional.isPresent();

        return  authorOptional.isPresent() && !author.getId().equals(authorOptional.get().getId());
    }
}
