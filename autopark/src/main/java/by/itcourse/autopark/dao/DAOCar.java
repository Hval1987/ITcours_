package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Car;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAOCar {
    Car findCar(int id ) throws SQLException;

    void  addCar(Car car) throws DAOException, SQLException;

    void deleteCarById(int id) throws SQLException;

    List<Car> getAllCars() throws SQLException;
}
