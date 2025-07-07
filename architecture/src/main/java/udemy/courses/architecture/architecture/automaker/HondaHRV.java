package udemy.courses.architecture.architecture.automaker;

import java.awt.*;

public class HondaHRV extends Car {

    public HondaHRV(Motor motor) {
        super(motor);
        setModel("HRV");
        setColor(Color.BLACK);
        setAutoMaker(AutoMaker.HONDA);
    }

}
