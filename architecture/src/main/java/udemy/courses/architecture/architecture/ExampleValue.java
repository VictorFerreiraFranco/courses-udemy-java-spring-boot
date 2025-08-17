package udemy.courses.architecture.architecture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExampleValue {

    @Value("${app.config.variable}")
    private String variable;

    public void printVariable(){
        System.out.println(variable);
    }
}
