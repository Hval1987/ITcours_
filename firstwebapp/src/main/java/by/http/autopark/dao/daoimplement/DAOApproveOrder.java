package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.ApprovedOrder;
import by.http.autopark.dao.DAOApprovedOrder;
import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import by.http.autopark.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class, we implement the DAO interface
 * to access the database for operations with the class  ApprovedOrder
 */

public class DAOApproveOrder implements DAOApprovedOrder {

        private  static final Logger logger= LoggerFactory.getLogger(DAOApproveOrder.class.getName());


        private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`approved_order` " +
                                   "(id_assigned_car, status, id_employer,id_driver,id_manager,id_order,date) VALUES(?,?,?,?,?,?,?)";
        private static final String REQUEST_FOR_DELETE_BY_ID="DELETE FROM park.approved_order WHERE id=?";
        private static final String REQUEST_FOR_SELECT_ALL_ORDERS="SELECT * FROM park.approved_order ";
        private static final String REQUEST_FOR_SELECT_BY_ID="SELECT * FROM park.approved_order WHERE id=?";
        private static final String REQUEST_FOR_SELECT_BY_STATUS="SELECT * FROM park.approved_order WHERE status=?";
        private static final String UPDATE_STATUS="UPDATE `park`.`approved_order` SET `status` = ? WHERE id =?";
        private static final String REQUEST_FOR_SELECT_BY_CAR="SELECT * FROM park.approved_order WHERE id_assigned_car=?";

        private static final String COLUMN_ID="id";
        private static final String COLUMN_ID_ORDER="id_order";
        private static final String COLUMN_ID_ASSIGNED_CAR="id_assigned_car";
        private static final String COLUMN_ID_DRIVER="id_driver";
        private static final String COLUMN_ID_EMPLOYER="id_employer";
        private static final String COLUMN_ID_MANAGER="id_manager";
        private static final String COLUMN_STATUS="status";


    public ConnectionPool connect=ConnectionPool.getInstance();

    /**
     * in this method, we search the database for approved orders by id
     * @param id  Identification number of finded Approve order
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public ApprovedOrder findApprovedOrder(int id) throws DAOException {
        PreparedStatement pstm=null;
        ResultSet rs = null;
        ApprovedOrder approvedOrder = null;
        connect.initPoolData();
        Connection con=connect.takeConnection();

        try {
            pstm=con.prepareStatement(REQUEST_FOR_SELECT_BY_ID);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_SELECT_ALL_ORDERS+" выполнена успешно");

            approvedOrder=new ApprovedOrder();
            while(rs.next()){
                approvedOrder.setIdAssignedCar(rs.getInt(COLUMN_ID_ASSIGNED_CAR));
                approvedOrder.setIdDriver(rs.getInt(COLUMN_ID_DRIVER));
                approvedOrder.setIdEmployer(rs.getInt(COLUMN_ID_EMPLOYER));
                approvedOrder.setIdManager(rs.getInt(COLUMN_ID_MANAGER));
                approvedOrder.setStatus(rs.getString(COLUMN_STATUS));
                approvedOrder.setId(id);
            }

            return approvedOrder;
        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    /**
     * in this method, we search for
     * all approved orders for a specific machine
     * @param carId Identification number of car
     * @return list of all approved orders
     * @throws DAOException Exception of the Service layer
     */
    @Override
    public List<ApprovedOrder> findApproveOrderByCar(int carId) throws DAOException {
        connect.initPoolData();
        Connection con=connect.takeConnection();
        PreparedStatement pstm=null;
        ResultSet rs=null;
        List<ApprovedOrder> allApprovedOrder=new ArrayList<>();

        try{
            pstm=con.prepareStatement(REQUEST_FOR_SELECT_BY_CAR);
            pstm.setInt(1,carId);
            rs=pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_SELECT_BY_CAR+" выполнена успешно");

            while(rs.next()){
                ApprovedOrder approvedOrder=new ApprovedOrder();

                approvedOrder.setIdAssignedCar(rs.getInt(COLUMN_ID_ASSIGNED_CAR));
                approvedOrder.setIdDriver(rs.getInt(COLUMN_ID_DRIVER));
                approvedOrder.setIdEmployer(rs.getInt(COLUMN_ID_EMPLOYER));
                approvedOrder.setIdManager(rs.getInt(COLUMN_ID_MANAGER));
                approvedOrder.setStatus(rs.getString(COLUMN_STATUS));
                approvedOrder.setIdOrder((rs.getInt(COLUMN_ID_ORDER)));
                approvedOrder.setId(rs.getInt(COLUMN_ID));

                allApprovedOrder.add(approvedOrder);
                approvedOrder=null;

            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new RuntimeException(exc);


        }
        finally{
            try {
                connect.closeConnection(con,pstm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return allApprovedOrder;

    }

    /**
     * in this method, we add an approved order to the database
     * @param approvedOrder
     * @throws DAOException Exception of the Service layer
     */
    @Override
    public void addApprovedOrder(ApprovedOrder approvedOrder) throws DAOException {
        connect.initPoolData();
        Connection con=null;
        PreparedStatement pstm=null;

        try {
            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_INSERT);

            pstm.setInt(1,approvedOrder.getIdAssignedCar());
            pstm.setString(2,approvedOrder.getStatus());
            pstm.setInt(3,approvedOrder.getIdEmployer());
            pstm.setInt(4,approvedOrder.getIdDriver());
            pstm.setInt(5,approvedOrder.getIdManager());
            pstm.setInt(6,approvedOrder.getIdOrder());
            pstm.setString(7, approvedOrder.getDate());

            pstm.executeUpdate();
            logger.debug("комманда -"+REQUEST_FOR_INSERT+" выполнена успешно");

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    /**
     * in this method, we delete the approved order
     * from the database by ID
     * @param id Identification number of deleted Approve order
     * @throws DAOException Exception of the Service layer
     */
    @Override
    public void deleteApprovedOrderById(int id) throws DAOException {
        connect.initPoolData();
        Connection con=null;
        PreparedStatement pstm=null;
        try{
            con=connect.takeConnection();
            pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1,id);
            pstm.executeUpdate();
            logger.debug("комманда -"+REQUEST_FOR_DELETE_BY_ID+" выполнена успешно");

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    /**
     * in this method, we search for
     *  all approved orders
     * @return list of all approved orders
     * @throws DAOException Exception of the Service layer
     */
    @Override
    public List<ApprovedOrder> getAllApprovedOrders() throws DAOException {
        connect.initPoolData();
        Connection con=connect.takeConnection();
        Statement stm=null;
        ResultSet rs=null;
        List<ApprovedOrder> allApprovedOrder=new ArrayList<>();

        try{
            stm=con.createStatement();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_ORDERS);
            logger.debug("комманда -"+REQUEST_FOR_SELECT_ALL_ORDERS+" выполнена успешно");

            while(rs.next()){
                ApprovedOrder approvedOrder=new ApprovedOrder();

                approvedOrder.setIdAssignedCar(rs.getInt(COLUMN_ID_ASSIGNED_CAR));
                approvedOrder.setIdDriver(rs.getInt(COLUMN_ID_DRIVER));
                approvedOrder.setIdEmployer(rs.getInt(COLUMN_ID_EMPLOYER));
                approvedOrder.setIdManager(rs.getInt(COLUMN_ID_MANAGER));
                approvedOrder.setStatus(rs.getString(COLUMN_STATUS));
                approvedOrder.setIdOrder((rs.getInt(COLUMN_ID_ORDER)));
                approvedOrder.setId(rs.getInt(COLUMN_ID));

                allApprovedOrder.add(approvedOrder);
                approvedOrder=null;

            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally{
            try {
                connect.closeConnection(con,stm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return allApprovedOrder;
    }

    /**
     * in this method we update the status
     * of the approved order
     * @param status
     * @param id
     * @throws DAOException Exception of the Service layer
     */
    @Override
    public void updateStatus(String status, int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        try{
            connect.initPoolData();
            con=connect.takeConnection();
            pstm=con.prepareStatement(UPDATE_STATUS);
            pstm.setString(1,status);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            logger.debug("комманда -"+UPDATE_STATUS+" выполнена успешно");

        }catch(SQLException exc){
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }
}


