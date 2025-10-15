package udemy.courses.libraryapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import udemy.courses.libraryapi.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


}
