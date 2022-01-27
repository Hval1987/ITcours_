package autopark.dao;

import autopark.bean.Order;
import autopark.dao.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAOOrder {
    Order findOrderInfo(int id) throws DAOException;
    void createOrder(Order ord) throws DAOException;
    void deleteOrder(int id) throws  DAOException;
    List<Order> getAll() throws DAOException ;
}
