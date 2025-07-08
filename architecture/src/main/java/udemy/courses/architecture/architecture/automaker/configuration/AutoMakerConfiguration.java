package udemy.courses.architecture.architecture.automaker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import udemy.courses.architecture.architecture.automaker.Motor;
import udemy.courses.architecture.architecture.automaker.TypeMotor;

@Configuration
public class AutoMakerConfiguration {

    @Bean(name = "motorAspirated")
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
