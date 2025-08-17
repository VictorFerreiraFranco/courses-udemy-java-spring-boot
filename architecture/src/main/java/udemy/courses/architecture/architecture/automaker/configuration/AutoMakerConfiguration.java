package udemy.courses.architecture.architecture.automaker.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import udemy.courses.architecture.architecture.automaker.Motor;
import udemy.courses.architecture.architecture.automaker.TypeMotor;

// Configuraçõoes dos bens
// @Component
// @Lazy - Faz ser carragado somente quando solicitar
// @Scope(BeanDefinition.SCOPE_SINGLETON) - Unica p/ aplicação independete do usuario ou cessão
// @Scope(WebApplicationContext.SCOPE_REQUEST) - Por requisição
// @Scope(WebApplicationContext.SCOPE_SESSION) - Por sessão de usuario
// @Scope(WebApplicationContext.SCOPE_APPLICATION) - por sessão da aplicação, unico para todos user

@Configuration
public class AutoMakerConfiguration {

    @Bean(name = "motorAspirated")
    @Primary
    public Motor motorAspirated() {
        Motor motor = new Motor();

        motor.setHorsepower("120");
        motor.setCylinders("4");
        motor.setLiterage(2.0);
        motor.setModel("AX M80");
        motor.setType(TypeMotor.ASPIRATED);

        return motor;
    }

    @Bean
    public Motor motorElectric() {
        Motor motor = new Motor();

        motor.setHorsepower("110");
        motor.setCylinders("4");
        motor.setLiterage(1.4);
        motor.setModel("ELEC M80x");
        motor.setType(TypeMotor.ELECTRIC);

        return motor;
    }

    @Bean
    public Motor motorTurbo() {
        Motor motor = new Motor();

        motor.setHorsepower("310");
        motor.setCylinders("5");
        motor.setLiterage(2.0);
        motor.setModel("FAst M90x");
        motor.setType(TypeMotor.TURBO);

        return motor;
    }


}
