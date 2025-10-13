package udemy.courses.libraryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udemy.courses.libraryapi.controller.dto.AuthorDTO;
import udemy.courses.libraryapi.model.Author;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @PostMapping
    private ResponseEntity save(@RequestBody AuthorDTO author){
        return new ResponseEntity("Autor salvo com successo!", HttpStatus.CREATED);
    }
}
