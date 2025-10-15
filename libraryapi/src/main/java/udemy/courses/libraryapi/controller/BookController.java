package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.courses.libraryapi.controller.dto.book.CreatedBookDTO;
import udemy.courses.libraryapi.controller.dto.error.ErrorResponse;
import udemy.courses.libraryapi.controller.mapper.BookMapper;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
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
    public ResponseEntity<Object> save(@RequestBody @Valid CreatedBookDTO dto){
        try {
            Book book = bookMapper.toEntity(dto);
            bookService.save(book);

            URI location = buildHeaderLocation(book.getId());

            return ResponseEntity.created(location).build();
        } catch (DuplicateRecordException e) {
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

}
