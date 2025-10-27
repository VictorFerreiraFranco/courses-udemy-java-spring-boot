package udemy.courses.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.courses.libraryapi.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLogin(String login);
}
