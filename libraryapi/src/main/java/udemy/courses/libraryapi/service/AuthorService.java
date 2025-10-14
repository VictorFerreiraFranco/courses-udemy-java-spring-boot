package udemy.courses.libraryapi.service;

import org.springframework.stereotype.Service;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) { return authorRepository.save(author); }

    public void delete(Author author) { authorRepository.delete(author); }

    public Optional<Author> findById(UUID id) { return authorRepository.findById(id); }

    public List<Author> search(String name, String nationality){
        if (name != null && nationality != null)
            return authorRepository.findByNameAndNationality(name, nationality);

        if (name != null)
            return authorRepository.findByName(name);

        if (nationality != null)
            return authorRepository.findByNationality(nationality);

        return authorRepository.findAll();
    }
}
