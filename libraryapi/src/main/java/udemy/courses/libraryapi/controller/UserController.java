package udemy.courses.libraryapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import udemy.courses.libraryapi.controller.dto.user.UserDTO;
import udemy.courses.libraryapi.controller.mapper.UserMapper;
import udemy.courses.libraryapi.model.User;
import udemy.courses.libraryapi.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO dto) {
        User user = userMapper.toEntity(dto);
        userService.save(user);
    }
}
