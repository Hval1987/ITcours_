package by.http.autopark.dao;

import by.http.autopark.bean.Order;
import by.http.autopark.dao.exception.DAOException;

import java.util.List;

public interface DAOOrder {
    Order findOrderInfo(int id) throws DAOException;
    void createOrder(Order ord) throws DAOException;
    void deleteOrder(int id) throws  DAOException;
    List<Order> getAll() throws DAOException ;
}
