package by.itcourse.autopark.dao;

import by.itcourse.autopark.beans.Order;

public interface DAOOrder {
    Order getOrderInfo(int id);
    void createInfo(Order ord);
    void deleteOrder(int id);
}
