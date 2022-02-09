package by.http.autopark.servise.implement;

/**
 * implementation of the service interface for operations on orders not yet approved
 */

import by.http.autopark.bean.Order;
import by.http.autopark.dao.DAOFactory;
import by.http.autopark.dao.exception.DAOException;
import by.http.autopark.servise.OrderService;
import by.http.autopark.servise.exception.DataErrorException;
import by.http.autopark.servise.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    /**
     * Using the DAO layer, select an order by ID
     * @param id order ID
     * @return The order whose id matches the id in the database
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public Order findOrderInfo(int id) throws ServiceException {
        if (id != 0) {
            try {
                return DAOFactory.getInstance().getDAOOrder().findOrderInfo(id);
            } catch (DAOException exc) {
                throw new ServiceException();
            }
        } else {

            return null;
        }
    }

    /**
     * in this method, we check the input data and send a command to create an order to the DAO layer
     * @param order
     * @return report on successful saving
     * @throws ServiceException Exception of the DAO layer
     * @throws DataErrorException this exception is thrown if the entered data is incorrect
     */
    @Override
    public boolean createOrder(Order order) throws ServiceException, DataErrorException {
        if (order != null) {

            if (order.getDate() != "" &&
                    order.getEmployerId() != 0 &&
                    order.getTransportType() != "") {
                try {
                    DAOFactory.getInstance().getDAOOrder().createOrder(order);
                    return true;
                } catch (DAOException exc) {
                    throw new ServiceException();
                }
            } else
                System.out.println("Enter the correct data");
                throw new DataErrorException();


        }
        else{
            System.out.println("invalid object passed");
            return false;
        }
    }

    /**
     * Send a command to delete the order by ID to the DAO layer
     * @param id order ID
     * @throws ServiceException Exception of the DAO layer
     */
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

    /**
     * Using the DAO layer, select all orders from the database
     * @return list of all orders
     * @throws ServiceException Exception of the DAO layer
     */
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
