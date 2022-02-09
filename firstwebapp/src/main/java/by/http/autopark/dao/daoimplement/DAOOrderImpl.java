package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.Order;
import by.http.autopark.dao.DAOOrder;
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
 * to access the database for operations with the class  Order
 */

public class DAOOrderImpl implements DAOOrder {

    private  static final Logger logger= LoggerFactory.getLogger(DAOOrderImpl.class.getName());

    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`order` " +
            "(`transport_type`, `date`, `employer_id`) VALUES(?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE FROM park.order WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_ORDERS="SELECT * FROM park.order ";
    private static final String REQUEST_FOR_SELECT_BY_ID="SELECT * FROM park.order WHERE id=?";

    private static final String COLUMN_TRANSPORT_TYPE="transport_type";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_EMPLOYER_ID="employer_id";
    private static final String COLUMN_ID="id";



    private ConnectionPool connect=ConnectionPool.getInstance();

    /**
     * in this method, we are looking for
     * order information in the database by id
     * @param id Identification number of order
     * @return instanсe of class order
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public Order findOrderInfo(int id) throws DAOException {
        PreparedStatement pstm=null;
        ResultSet rs = null;
        Order order = null;
        Connection con=connect.takeConnection();


        try {
            pstm=con.prepareStatement(REQUEST_FOR_SELECT_BY_ID);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_SELECT_BY_ID+" выполнена успешно");
            order=new Order();

            while(rs.next()){

                order.setTransportType(rs.getString(COLUMN_TRANSPORT_TYPE));
                order.setDate(rs.getString(COLUMN_DATE));
                order.setEmployerId(rs.getInt(COLUMN_EMPLOYER_ID));
                order.setId(id);

            }

            return order;
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
     * in this method, we add order information to the database
     * @param ord instanсe of class order
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public void createOrder(Order ord) throws DAOException {

        Connection con=null;
        PreparedStatement pstm=null;

        try {
            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_INSERT);
            pstm.setString(1,ord.getTransportType());
            pstm.setString(2,ord.getDate());
            pstm.setInt(3,ord.getEmployerId());
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
     * in this method, we delete the order
     * information from the database by id
     * @param id an instance of the class order that we are deleting
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public void deleteOrder(int id) throws DAOException {
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
     * in this method we get all orders from the database
     * @return list of all orders
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public List<Order> getAll() throws DAOException {
        Connection con=connect.takeConnection();
        Statement stm=null;
        ResultSet rs=null;
        List<Order> allOrder=new ArrayList<>();

        try{
            stm=con.createStatement();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_ORDERS);
            logger.debug("комманда -"+REQUEST_FOR_SELECT_ALL_ORDERS+" выполнена успешно");

            while(rs.next()){
                Order order=new Order();

                order.setTransportType(rs.getString(COLUMN_TRANSPORT_TYPE));
                order.setDate(rs.getString(COLUMN_DATE));
                order.setEmployerId(rs.getInt(COLUMN_EMPLOYER_ID));
                order.setId(rs.getInt(COLUMN_ID));
                allOrder.add(order);
                order=null;
            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException();
        }
        finally{
            try {
                connect.closeConnection(con,stm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return allOrder;
    }
}
