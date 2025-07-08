package udemy.courses.architecture.architecture.automaker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udemy.courses.architecture.architecture.automaker.*;

@RestController
@RequestMapping("/cars")
public class FactoryController {

    @Autowired
    @Qualifier("motorElectric")
    private Motor motor;

    @PostMapping
    public CarStatus startCar(@RequestBody Key key) {
        Car car = new HondaHRV(this.motor);
        return car.ignite(key);
    }

}
