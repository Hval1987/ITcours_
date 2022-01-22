package autopark.servise;

import autopark.bean.Car;

import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;

import java.util.List;

/**
 * In this interface we define the behavior of Car objects
 */
public interface CarService {
    void ChangeAvailAbilityCar(int id) throws ServiceException;

    Car findCar(int id ) throws ServiceException ;

    void  addCar(Car car) throws ServiceException, DataErrorException;

    boolean deleteCarById(int id) throws ServiceException;

    List<Car> getAllCars() throws ServiceException;


}
