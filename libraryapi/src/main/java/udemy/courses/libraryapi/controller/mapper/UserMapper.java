package udemy.courses.libraryapi.controller.mapper;

import org.mapstruct.Mapper;
import udemy.courses.libraryapi.controller.dto.user.UserDTO;
import udemy.courses.libraryapi.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

}
