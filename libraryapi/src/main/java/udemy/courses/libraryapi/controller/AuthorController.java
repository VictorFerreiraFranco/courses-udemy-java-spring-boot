package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import udemy.courses.libraryapi.controller.dto.author.AuthorDTO;
import udemy.courses.libraryapi.controller.dto.error.ErrorResponse;
import udemy.courses.libraryapi.controller.mapper.AuthorMapper;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
import udemy.courses.libraryapi.exceptions.OperationNotPermittedException;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.service.AuthorService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO authorDTO){
       try {
           Author author = authorMapper.toEntity(authorDTO);

           authorService.save(author);

           URI location = buildHeaderLocation(author.getId());

           return ResponseEntity.created(location).build();
       } catch (DuplicateRecordException e){
           var error = ErrorResponse.conflict(e.getMessage());
           return ResponseEntity.status(error.status()).body(error);
       }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(
            @PathVariable String id,
            @RequestBody @Valid AuthorDTO authorDTO
    ){
        try {
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
        } catch (DuplicateRecordException e){
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try {
            var idAuthor = UUID.fromString(id);
            Optional<Author> authorOptional =  authorService.findById(idAuthor);

            if (authorOptional.isEmpty())
                return ResponseEntity.notFound().build();

            Author author = authorOptional.get();

            authorService.delete(author);

            return ResponseEntity.noContent().build();
        } catch (OperationNotPermittedException e){
            var error = ErrorResponse.responseDefault(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getDetails(@PathVariable String id){
        var idAuthor = UUID.fromString(id);

        return authorService
                .findById(idAuthor)
                .map(author -> {
                    AuthorDTO dto = authorMapper.toDTO(author);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build() );

    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(required = false) String nacioanity
    ){
        List<Author> authors = authorService.search(name, nacioanity);
        List<AuthorDTO> authorsDTO = authors
                .stream()
                .map(authorMapper::toDTO)
                .toList();

        return ResponseEntity.ok(authorsDTO);
    }
}
