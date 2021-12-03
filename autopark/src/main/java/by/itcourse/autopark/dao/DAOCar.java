package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Car;
import by.itcourse.autopark.dao.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAOCar {
    Car findCar(int id ) throws DAOException;

    void  addCar(Car car) throws DAOException;

    void deleteCarById(int id) throws  DAOException;

    List<Car> getAllCars() throws  DAOException;
}
