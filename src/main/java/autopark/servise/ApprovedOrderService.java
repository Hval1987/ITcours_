package autopark.servise;


import autopark.bean.ApprovedOrder;
import autopark.servise.exception.ServiceException;

import java.util.HashMap;
import java.util.List;

public interface ApprovedOrderService {
        public HashMap<ApprovedOrder,String> findApprovedDriversOrder(int id) throws ServiceException;
        ApprovedOrder findApprovedOrder(int id) throws ServiceException;
        void addApprovedOrder(ApprovedOrder ord) throws ServiceException;
        void deleteApprovedOrder(int id) throws ServiceException;
        List<ApprovedOrder> getAll() throws ServiceException;
        void ChangeOrderAsComplited(int id) throws ServiceException;


}


