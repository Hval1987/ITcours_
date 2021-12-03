package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Order;

import java.sql.SQLException;
import java.util.List;

public interface DAOOrder {
    Order findOrderInfo(int id) throws SQLException;
    void createOrder(Order ord) throws SQLException;
    void deleteOrder(int id) throws SQLException;
    List<Order> getAll() throws SQLException;
}
