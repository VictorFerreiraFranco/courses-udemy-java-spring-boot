package udemy.courses.architecture.architecture.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    private TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validate(TodoEntity entity) {
        if (existsTodoWithDescription(entity.getDescription()))
            throw new IllegalArgumentException("Description already exists");
    }

    public boolean existsTodoWithDescription(String description) {
        return todoRepository.existsByDescription(description);
    }
}
