package udemy.courses.libraryapi.controller.dto.author;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,

        @NotBlank(message = "field required")
        @Size(min = 3, max = 100, message = "the field must contain between {min} and {max} characters")
        String name,

        @NotNull(message = "field required")
        @Past(message = "the date must be in the past or present")
        LocalDate birthDate,

        @NotBlank(message = "field required")
        @Size(min = 3, max = 50, message = "the field must contain between {min} and {max} characters")
        String nationality
) {}
