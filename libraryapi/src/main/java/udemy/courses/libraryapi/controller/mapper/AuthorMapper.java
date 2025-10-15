package udemy.courses.libraryapi.controller.mapper;

import org.mapstruct.Mapper;
import udemy.courses.libraryapi.controller.dto.author.AuthorDTO;
import udemy.courses.libraryapi.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);
}
