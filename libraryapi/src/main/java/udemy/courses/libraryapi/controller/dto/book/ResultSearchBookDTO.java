package udemy.courses.libraryapi.controller.dto.book;

import udemy.courses.libraryapi.controller.dto.author.AuthorDTO;
import udemy.courses.libraryapi.model.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultSearchBookDTO(
        UUID id,
        String isdn,
        String title,
        LocalDate publishDate,
        GenderBook gender,
        BigDecimal price,
        AuthorDTO author
) {
}
