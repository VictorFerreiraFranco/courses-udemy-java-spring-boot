package udemy.courses.libraryapi.controller.dto.user;

import java.util.List;

public record UserDTO(String login, String password, List<String> roles) {
}
