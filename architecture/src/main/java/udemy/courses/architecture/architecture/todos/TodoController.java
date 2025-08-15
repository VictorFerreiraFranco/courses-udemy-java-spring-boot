package udemy.courses.architecture.architecture.todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public TodoEntity save(@RequestBody TodoEntity entity) {
        try {
            return service.save(entity);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("{id}")
    public void updateStatus(@PathVariable Integer id, @RequestBody TodoEntity entity) {
        entity.setId(id);
        service.updateStatus(entity);
    }

    @GetMapping("{id}")
    public TodoEntity findById(@PathVariable Integer id) {
        return service.findById(id);
    }
}
