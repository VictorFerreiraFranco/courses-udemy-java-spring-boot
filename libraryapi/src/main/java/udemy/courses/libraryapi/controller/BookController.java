package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udemy.courses.libraryapi.controller.dto.book.CreatedBookDTO;
import udemy.courses.libraryapi.controller.mapper.BookMapper;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.service.BookService;

import java.net.URI;

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

}
