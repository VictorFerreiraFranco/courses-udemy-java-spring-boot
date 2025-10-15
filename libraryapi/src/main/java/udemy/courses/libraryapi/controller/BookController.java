package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.courses.libraryapi.controller.dto.book.CreatedBookDTO;
import udemy.courses.libraryapi.controller.dto.book.ResultSearchBookDTO;
import udemy.courses.libraryapi.controller.mapper.BookMapper;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;
import udemy.courses.libraryapi.service.BookService;

import java.net.URI;
import java.util.List;
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

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    bookService.delete(book);

                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build() );
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

    @GetMapping
    public ResponseEntity<List<ResultSearchBookDTO>> search(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) GenderBook gender,
            @RequestParam(value = "publish-year", required = false) Integer publishYear,
            @RequestParam(value = "author-name", required = false) String authorName
    ) {
        List<Book> bookList = bookService.search(isbn, title, gender, publishYear, authorName);

        List<ResultSearchBookDTO> bookListDTO = bookList
                .stream()
                .map(bookMapper::toDTO)
                .toList();

        return ResponseEntity.ok(bookListDTO);
    }
}
