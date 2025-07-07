package udemy.courses.architecture.architecture.automaker;

public class Motor {
    private String model;
    private String horsepower;
    private String cylinders;
    private Double literage;
    private TypeMotor type;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public Double getLiterage() {
        return literage;
    }

    public void setLiterage(Double literage) {
        this.literage = literage;
    }

    public TypeMotor getType() {
        return type;
    }

    public void setType(TypeMotor type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "model='" + model + '\'' +
                ", horsepower='" + horsepower + '\'' +
                ", cylinders='" + cylinders + '\'' +
                ", literage=" + literage +
                ", type=" + type +
                '}';
    }
}
