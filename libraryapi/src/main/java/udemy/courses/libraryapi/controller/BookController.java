package udemy.courses.libraryapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.courses.libraryapi.controller.dto.book.CreatedBookDTO;
import udemy.courses.libraryapi.controller.dto.error.ErrorResponse;
import udemy.courses.libraryapi.exceptions.DuplicateRecordException;
import udemy.courses.libraryapi.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CreatedBookDTO dto){
        try {

            return ResponseEntity.ok(dto);

        } catch (DuplicateRecordException e) {
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

}
