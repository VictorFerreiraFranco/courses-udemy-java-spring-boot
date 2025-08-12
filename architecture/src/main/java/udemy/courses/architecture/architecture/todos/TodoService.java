package udemy.courses.architecture.architecture.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public TodoEntity save(TodoEntity entity) {
        return repository.save(entity);
    }

}
