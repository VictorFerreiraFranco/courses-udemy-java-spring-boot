package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.courses.libraryapi.controller.dto.book.CreatedBookDTO;
import udemy.courses.libraryapi.controller.dto.book.ResultSearchBookDTO;
import udemy.courses.libraryapi.controller.mapper.BookMapper;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.service.BookService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid CreatedBookDTO dto) {
        Book book = bookMapper.toEntity(dto);
        bookService.save(book);

        URI location = buildHeaderLocation(book.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultSearchBookDTO> get(@PathVariable String id) {
        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    ResultSearchBookDTO dto = bookMapper.toDTO(book);

                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }
}
