package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.courses.libraryapi.controller.dto.book.BookDTO;
import udemy.courses.libraryapi.controller.dto.book.ResultSearchBookDTO;
import udemy.courses.libraryapi.controller.mapper.BookMapper;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;
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
    public ResponseEntity<Void> save(@RequestBody @Valid BookDTO dto) {
        Book book = bookMapper.toEntity(dto);
        bookService.save(book);

        URI location = buildHeaderLocation(book.getId());

        return ResponseEntity.created(location).build();
    }

    @PostMapping("{id}")
    public ResponseEntity<Object> update(
            @PathVariable String id,
            @RequestBody @Valid BookDTO dto
    ) {
        return bookService.findById(UUID.fromString(id))
                .map(book -> {

                    Book bookAux = bookMapper.toEntity(dto);

                    book.setIsbn(bookAux.getIsbn());
                    book.setTitle(bookAux.getTitle());
                    book.setGender(bookAux.getGender());
                    book.setPublishDate(bookAux.getPublishDate());
                    book.setPrice(bookAux.getPrice());
                    book.setAuthor(bookAux.getAuthor());

                    bookService.update(book);

                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build() );
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
    public ResponseEntity<Page<ResultSearchBookDTO>> search(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) GenderBook gender,
            @RequestParam(value = "publish-year", required = false) Integer publishYear,
            @RequestParam(value = "author-name", required = false) String authorName,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize
    ) {
        Page<Book> BookPage = bookService.search(isbn, title, gender, publishYear, authorName, page, pageSize);

        Page<ResultSearchBookDTO> BookDTOPage = BookPage.map(bookMapper::toDTO);

        return ResponseEntity.ok(BookDTOPage);
    }
}
