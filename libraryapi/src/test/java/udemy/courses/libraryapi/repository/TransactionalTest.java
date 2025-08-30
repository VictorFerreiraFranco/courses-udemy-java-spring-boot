package udemy.courses.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import udemy.courses.libraryapi.model.Author;
import udemy.courses.libraryapi.service.TransactionalService;

@SpringBootTest
public class TransactionalTest {

    @Autowired
    TransactionalService transactionalService;

    @Test
    void transactionalSimple() {
        transactionalService.execute();
    }

    @Test
    void transactionalSimple2() {
        transactionalService.execute2();
    }

    @Test
    void updateNotSave() {
        transactionalService.updateNotSave();
    }
}
