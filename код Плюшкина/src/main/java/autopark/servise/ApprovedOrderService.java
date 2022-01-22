package autopark.servise;


import autopark.bean.ApprovedOrder;
import autopark.bean.Car;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;

import java.util.HashMap;
import java.util.List;

/**
 * In this interface we define the behavior of approveOrder objects
 */

public interface ApprovedOrderService {
        HashMap<ApprovedOrder,String> findApprovedDriversOrder(int id) throws ServiceException;
        ApprovedOrder findApprovedOrder(int id) throws ServiceException;
        void addApprovedOrder(ApprovedOrder ord) throws ServiceException, DataErrorException;
        void deleteApprovedOrder(int id) throws ServiceException;
        List<ApprovedOrder> getAll() throws ServiceException;
        void ChangeOrderAsComplited(int id) throws ServiceException;
        List<Car> ChooseFreeCar(int OrderId) throws ServiceException;


}


