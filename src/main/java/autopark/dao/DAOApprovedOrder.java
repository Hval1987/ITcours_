package autopark.dao;

import autopark.bean.ApprovedOrder;
import autopark.dao.exception.DAOException;

import java.util.List;

public interface DAOApprovedOrder {

    ApprovedOrder findApprovedOrder (int id ) throws DAOException;
    List<ApprovedOrder> findApproveOrderByCar(int carId) throws DAOException;

    void  addApprovedOrder (ApprovedOrder approvedOrder) throws DAOException;

    void deleteApprovedOrderById(int id) throws  DAOException;

    List<ApprovedOrder> getAllApprovedOrders () throws  DAOException;

    void updateStatus(String Status,int id) throws DAOException;
}
