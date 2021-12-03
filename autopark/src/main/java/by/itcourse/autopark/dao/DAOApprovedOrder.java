package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Car;
import by.itcourse.autopark.dao.exception.DAOException;

import java.util.List;

public interface DAOApprovedOrder {
    Car findApprovedOrder (int id ) throws DAOException;

    void  addApprovedOrder (Car car) throws DAOException;

    void deleteApprovedOrderById(int id) throws  DAOException;

    List<Car> getAllApprovedOrders () throws  DAOException;
}
