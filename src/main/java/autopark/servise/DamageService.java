package autopark.servise;

import autopark.bean.Car;
import autopark.dao.exception.DAOException;

import java.util.List;

public interface DamageService {
    Car findDamage(int id ) throws DAOException;

    void  addDamage(Car car) throws DAOException;

    void deleteDamageById(int id) throws  DAOException;

    List<Car> getAllDamages() throws  DAOException;
}
