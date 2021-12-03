package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Car;
import by.itcourse.autopark.dao.exception.DAOException;

import java.util.List;

public interface DAODamage {

    Car findDamage(int id ) throws DAOException;

    void  addDamage(Car car) throws DAOException;

    void deleteDamageById(int id) throws  DAOException;

    List<Car> getAllDamages() throws  DAOException;
}
