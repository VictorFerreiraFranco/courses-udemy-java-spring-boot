package udemy.courses.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import udemy.courses.libraryapi.model.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Test
    public void SaveTest(){
        Author author = new Author();
        author.setName("Jack");
        author.setNationality("Brazilian");
        author.setBirthDate(LocalDate.of(1990, 1, 1));

        var authorSavable = repository.save(author);
        System.out.println("Author:" + authorSavable);
    }

    @Test
    public void updateTest(){
        var id = UUID.fromString("cab40730-e8d6-40fb-857f-c3616d9f5f4f");

        Optional<Author> optionalAuthor = repository.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            System.out.println("Author:" + author);

            author.setBirthDate(LocalDate.of(1960, 1, 20));

            repository.save(author);
        }
    }

    @Test
    public void listTest(){
        List<Author> authors = repository.findAll();
        authors.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Author count: " + repository.count());
    }

    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("cab40730-e8d6-40fb-857f-c3616d9f5f4f");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("cab40730-e8d6-40fb-857f-c3616d9f5f4f");
        var author = repository.findById(id).get();
        repository.delete(author);
    }
}
