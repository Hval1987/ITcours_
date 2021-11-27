package by.itcourse.autopark.dao;

import by.itcourse.autopark.beans.Car;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.util.List;

public interface DAOCar {
    Car findCar(int id );

    void  addCar(Car car) throws DAOException;

    void deleteCarById(int id);

    List<Car> getAllCars();
}
