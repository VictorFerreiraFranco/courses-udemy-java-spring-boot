package udemy.courses.libraryapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udemy.courses.libraryapi.controller.dto.AuthorDTO;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.service.AuthorService;

import java.net.URI;
import java.util.List;
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
    public ResponseEntity<Void> save(@RequestBody AuthorDTO authorDTO){
        var author = authorDTO.mappingForAuthor();
        authorService.save(author);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
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

    @PutMapping("{id}")
    public ResponseEntity<Void> update(
            @PathVariable String id,
            @RequestBody AuthorDTO authorDTO
    ){
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional =  authorService.findById(idAuthor);

        if (authorOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Author author = authorOptional.get();

        author.setName(authorDTO.name());
        author.setBirthDate(authorDTO.birthDate());
        author.setNationality(authorDTO.nationality());

        authorService.update(author);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(required = false) String nacioanity
    ){
        List<Author> authors = authorService.search(name, nacioanity);
        List<AuthorDTO> authorsDTO = authors
                .stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getBirthDate(),
                        author.getNationality())
                )
                .toList();

        return ResponseEntity.ok(authorsDTO);
    }
}
