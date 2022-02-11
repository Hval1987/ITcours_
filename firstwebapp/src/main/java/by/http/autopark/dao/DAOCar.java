package by.http.autopark.dao;

import by.http.autopark.bean.Car;
import by.http.autopark.dao.exception.DAOException;

import java.util.List;

public interface DAOCar {

    void changeAvailability(String availAbility,int id) throws DAOException;

    Car findCar(int id ) throws DAOException;

    void  addCar(Car car) throws DAOException;

    void deleteCarById(int id) throws  DAOException;

    List<Car> getAllCars() throws  DAOException;
}
