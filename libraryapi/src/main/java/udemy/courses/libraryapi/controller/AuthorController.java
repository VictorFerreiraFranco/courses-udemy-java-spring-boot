package udemy.courses.libraryapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udemy.courses.libraryapi.controller.dto.AuthorDTO;
import udemy.courses.libraryapi.service.AuthorService;

import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody AuthorDTO author){
        var AuthorEntity = author.mappingForAuthor();
        authorService.save(AuthorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(AuthorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
