package by.http.autopark.servise;

import by.http.autopark.bean.Order;
import by.http.autopark.servise.exception.DataErrorException;
import by.http.autopark.servise.exception.ServiceException;

import java.util.List;

/**
 * In this interface we define the behavior of Order objects
 */
public interface OrderService {
        Order findOrderInfo(int id) throws ServiceException;
        boolean createOrder(Order ord) throws ServiceException, DataErrorException;
        void deleteOrder(int id) throws ServiceException;
        List<Order> getAll() throws ServiceException;


}

