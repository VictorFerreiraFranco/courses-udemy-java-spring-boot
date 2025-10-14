package udemy.courses.libraryapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import udemy.courses.libraryapi.exceptions.OperationNotPermittedException;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.repository.AuthorRepository;
import udemy.courses.libraryapi.repository.BookRepository;
import udemy.courses.libraryapi.validator.AuthorValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;
    private final BookRepository bookRepository;

    public Author save(Author author) {
        authorValidator.validate(author);
        return authorRepository.save(author);
    }

    public void update (Author author) {

        if (author.getId() == null)
            throw new IllegalArgumentException("Author id is null");

        authorValidator.validate(author);
        authorRepository.save(author);
    }

    public void delete(Author author) {
        if (existsBook(author))
            throw new OperationNotPermittedException("Author has books");

        authorRepository.delete(author);
    }

    public Optional<Author> findById(UUID id) {
        return authorRepository.findById(id);
    }

    public List<Author> search(String name, String nationality){
        if (name != null && nationality != null)
            return authorRepository.findByNameAndNationality(name, nationality);

        if (name != null)
            return authorRepository.findByName(name);

        if (nationality != null)
            return authorRepository.findByNationality(nationality);

        return authorRepository.findAll();
    }

    public boolean existsBook(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
