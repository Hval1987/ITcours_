package autopark.servise;

import autopark.bean.Order;
import autopark.servise.exception.ServiceException;

import java.util.List;

public interface OrderService {
        Order findOrderInfo(int id) throws ServiceException;
        void createOrder(Order ord) throws ServiceException;
        void deleteOrder(int id) throws ServiceException;
        List<Order> getAll() throws ServiceException;


}

