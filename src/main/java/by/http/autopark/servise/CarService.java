package by.http.autopark.servise;

import by.http.autopark.bean.Car;

import by.http.autopark.servise.exception.EntryCarInfoException;
import by.http.autopark.servise.exception.ServiceException;

import java.util.List;

/**
 * In this interface we define the behavior of Car objects
 */
public interface CarService {
    void ChangeAvailAbilityCar(int id) throws ServiceException;

    Car findCar(int id ) throws ServiceException ;

    void  addCar(Car car) throws ServiceException, EntryCarInfoException;

    boolean deleteCarById(int id) throws ServiceException;

    List<Car> getAllCars() throws ServiceException;


}
