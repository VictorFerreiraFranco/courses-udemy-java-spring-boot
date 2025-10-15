package udemy.courses.libraryapi.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import udemy.courses.libraryapi.controller.dto.book.BookDTO;
import udemy.courses.libraryapi.controller.dto.book.ResultSearchBookDTO;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.repository.AuthorRepository;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java( authorRepository.findById(dto.idAuthor()).orElse(null) )")
    public abstract Book toEntity(BookDTO dto);

    @Mapping(source = "isbn", target = "isbn")
    public abstract ResultSearchBookDTO toDTO(Book book);
}