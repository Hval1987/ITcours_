package autopark.servise.implement;


import autopark.bean.Order;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.OrderService;
import autopark.servise.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {


    @Override
    public Order findOrderInfo(int id) throws ServiceException {
        if (id != 0) {
            try {
                System.out.println("right");
                return DAOFactory.getInstance().getDAOOrder().findOrderInfo(id);
            } catch (DAOException exc) {
                System.out.println("ошибка слоя DAO");
                throw new ServiceException();
            }
        } else {

            return null;
        }
    }

    @Override
    public void createOrder(Order ord) throws ServiceException {
        if(ord!=null) {
            try {
                DAOFactory.getInstance().getDAOOrder().createOrder(ord);
            } catch (DAOException exc) {
                System.out.println("ошибка слоя DAO");
                throw new ServiceException();
            }
        }
        else
            System.out.println("Order=null!!!");

    }

    @Override
    public void deleteOrder(int id) throws ServiceException {
        if(id!=0){
            try {
                DAOFactory.getInstance().getDAOOrder().deleteOrder(id);
            }catch (DAOException exc){
                throw new ServiceException();
            }
        }

    }

    @Override
    public List<Order> getAll() throws ServiceException  {
        List<Order> list=null;
        try {
            return DAOFactory.getInstance().getDAOOrder().getAll();
        }catch (DAOException exc){
            throw new ServiceException();

        }
    }

}
