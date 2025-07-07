package udemy.courses.architecture.architecture.automaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import udemy.courses.architecture.architecture.automaker.Motor;
import udemy.courses.architecture.architecture.automaker.TypeMotor;

@Configuration
public class AutoMakerConfiguration {

    @Bean
    public Motor motor() {
        Motor motor = new Motor();

        motor.setHorsepower("120");
        motor.setCylinders("4");
        motor.setLiterage(2.0);
        motor.setModel("AX M80");
        motor.setType(TypeMotor.ASPIRATED);

        return motor;
    }



}
