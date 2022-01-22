package autopark.bean;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

public  class  Car implements Serializable {
@NotNull
    private int id;
@NotNull
    private String transportType;
@NotNull
@Pattern(regexp = "[А-Я][A-Z]-[1-7][0-9]*")
    private int driverId;
@NotNull
    private String regNumber;
    private String available; //на ходу или нет


    public Car(){}

    public Car(int id, String transportType, int driverId, String regNumber, String status, String available, String bookingDate) {
        this.id = id;
        this.transportType = transportType;
        this.driverId = driverId;
        this.regNumber = regNumber;
        this.available = available;

    }
    //пока нужен такой конструктор, чтобы проверить как работает метод addCar()




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                driverId == car.driverId &&
                transportType.equals(car.transportType) &&
                regNumber.equals(car.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportType, driverId, regNumber);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", transportType='" + transportType + '\'' +
                ", driverId=" + driverId +
                ", regNumber='" + regNumber + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}
