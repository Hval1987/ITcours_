package autopark.bean;

import java.io.Serializable;
import java.util.Objects;

public  class  Car implements Serializable {
    private int id;
    private String transportType;
    private int driverId;
    private String regNumber;
    private String status;  //занят или нет
    private String available; //на ходу или нет
    private String bookingDate;

    public Car(){}

    public Car(int id, String transportType, int driverId, String regNumber, String status, String available, String bookingDate) {
        this.id = id;
        this.transportType = transportType;
        this.driverId = driverId;
        this.regNumber = regNumber;
        this.status = status;
        this.available = available;
        this.bookingDate = bookingDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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
                ", status='" + status + '\'' +
                ", available='" + available + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                '}';
    }
}
