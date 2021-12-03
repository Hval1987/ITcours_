package by.itcourse.autopark.bean;

import java.io.Serializable;
import java.util.Objects;

public  class  Car implements Serializable {
    private int id;
    private String idCharacteristic;
    private int workable;
    private String regNumber;

    public Car(){}

    public Car(String idCharacteristic, int workable, String regNumber) {
        //пока нужен такой конструктор, чтобы проверить как работает метод addCar()

        this.idCharacteristic = idCharacteristic;
        this.workable = workable;
        this.regNumber = regNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_Characteristic() {
        return idCharacteristic;
    }

    public void setId_Characteristic(String idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    public int getWorkable() {
        return workable;
    }

    public void setWorkable(int workable) {
        this.workable = workable;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                workable == car.workable &&
                idCharacteristic.equals(car.idCharacteristic) &&
                regNumber.equals(car.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCharacteristic, workable, regNumber);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", id_Characteristic='" + idCharacteristic + '\'' +
                ", workable=" + workable +
                ", regNumber='" + regNumber + '\'' +
                '}';
    }
}
