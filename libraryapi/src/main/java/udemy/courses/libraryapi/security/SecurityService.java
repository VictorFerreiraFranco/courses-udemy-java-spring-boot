package udemy.courses.libraryapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import udemy.courses.libraryapi.model.User;
import udemy.courses.libraryapi.service.UserService;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public User getUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userLogged = (UserDetails) authentication.getPrincipal();
        return userService.findByLogin(userLogged.getUsername());
    }

}
