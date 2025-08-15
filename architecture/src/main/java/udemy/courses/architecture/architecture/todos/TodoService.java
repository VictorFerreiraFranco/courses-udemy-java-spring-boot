package udemy.courses.architecture.architecture.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository repository,
                       TodoValidator validator,
                       MailSender mailSender
    ) {
        this.repository = repository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity save(TodoEntity entity) {
        validator.validate(entity);
        return repository.save(entity);
    }

    public void updateStatus(TodoEntity entity) {
        repository.save(entity);
        String status = entity.getCompleted() == Boolean.TRUE ? "completed" : "not completed";
        mailSender.send("Todo " + entity.getDescription() + " has been updated to " + status);
    }

    public TodoEntity findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
