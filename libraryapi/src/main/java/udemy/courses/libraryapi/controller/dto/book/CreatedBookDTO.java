package udemy.courses.libraryapi.controller.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;
import udemy.courses.libraryapi.model.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreatedBookDTO(
        @ISBN(message = "invalid ISBN")
        @NotBlank(message = "field required")
        String isbn,

        @NotBlank(message = "field required")
        String title,

        @NotNull(message = "field required")
        @Past(message = "the date must be in the past or present")
        LocalDate publishDate,

        GenderBook gender,

        BigDecimal price,

        @NotNull(message = "field required")
        UUID idAuthor
) {
}
