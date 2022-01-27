package autopark.dao.daoImplement;

import autopark.bean.Order;
import autopark.dao.DAOOrder;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;
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

    private ConnectionPool connect=ConnectionPool.getInstance();


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

                order.setTransportType(rs.getString("transport_type"));
                order.setDate(rs.getString("date"));
                order.setEmployerId(rs.getInt("employer_id"));
                order.setId(id);

            }

            return order;
        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {

            connect.closeConnection(con,pstm,rs);

        }
    }

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

            connect.closeConnection(con,pstm);
        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
            }
        finally {
            connect.closeConnection(con,pstm);
           }

        }

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
            connect.closeConnection(con,pstm);
        }

    }

    @Override
    public List<Order> getAll()  {
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

                order.setTransportType(rs.getString("transport_type"));
                order.setDate(rs.getString("date"));
                order.setEmployerId(rs.getInt("employer_id"));
                order.setId(rs.getInt("id"));
                allOrder.add(order);
                order=null;

            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new RuntimeException(exc);


        }
        finally{
            connect.closeConnection(con,stm,rs);

        }
        return allOrder;
    }
}
