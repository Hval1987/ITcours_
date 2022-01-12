package autopark.dao;

import autopark.bean.Car;
import autopark.dao.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAOCar {
    void changeStatus(String status,int id) throws DAOException;

    void changeAvailability(String availAbility,int id) throws DAOException;

    Car findCar(int id ) throws DAOException;

    void  addCar(Car car) throws DAOException;

    void deleteCarById(int id) throws  DAOException;

    List<Car> getAllCars() throws  DAOException;
}
