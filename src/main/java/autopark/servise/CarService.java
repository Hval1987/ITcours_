package autopark.servise;

import autopark.bean.Car;

import autopark.servise.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;


public interface CarService {
    void ChangeAvailAbilityCar(int id) throws ServiceException;

    Car findCar(int id ) throws ServiceException ;

    void  addCar(Car car) throws ServiceException;

    void deleteCarById(int id) throws ServiceException;

    List<Car> getAllCars() throws ServiceException;


}
