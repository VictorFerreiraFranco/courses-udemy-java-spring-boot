package udemy.courses.libraryapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udemy.courses.libraryapi.controller.dto.AuthorDTO;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.service.AuthorService;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO author){
        var AuthorEntity = author.mappingForAuthor();
        authorService.save(AuthorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(AuthorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getDetails(@PathVariable String id){
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional =  authorService.findById(idAuthor);

        if (authorOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Author author = authorOptional.get();

        AuthorDTO dto = new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
        );

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional =  authorService.findById(idAuthor);

        if (authorOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Author author = authorOptional.get();

        authorService.delete(author);

        return ResponseEntity.noContent().build();
    }
}
