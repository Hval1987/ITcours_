package autopark.dao.daoImplement;

import autopark.bean.Order;
import autopark.dao.DAOOrder;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOOrderImpl implements DAOOrder {

    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`order` " +
            "(`transport_type`, `date`, `employer_id`) VALUES(?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE FROM park.order WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_ORDERS="SELECT * FROM park.order ";
    private static final String REQUEST_FOR_SELECT_BY_ID="SELECT * FROM park.order WHERE id=?";

    public ConnectionPool connect=ConnectionPool.getInstance();


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
            order=new Order();
            while(rs.next()){
                order.setTransportType(rs.getString("transport_type"));
                order.setDate(rs.getString("date"));
                order.setEmployerId(rs.getInt("employer_id"));
                order.setId(id);
                System.out.println(order);
            }

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {

            connect.closeConnection(con,pstm,rs);

        }


    }

    @Override
    public void createOrder(Order ord) throws DAOException {

        Connection con=null;
        PreparedStatement pstm=null;
        System.out.println("inter daoOrder");
        System.out.println(ord);

        try {
            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_INSERT);



            pstm.setString(1,ord.getTransportType());
            pstm.setString(2,ord.getDate());
            pstm.setInt(3,ord.getEmployerId());


            pstm.executeUpdate();

            connect.closeConnection(con,pstm);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
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
            System.out.println("удаление завершено");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
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



            while(rs.next()){
                Order order=new Order();

                order.setTransportType(rs.getString("transport_type"));
                order.setDate(rs.getString("date"));
                order.setEmployerId(rs.getInt("employer_id"));
                order.setId(rs.getInt("id"));

                allOrder.add(order);
                order=null;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();


        }
        finally{
            connect.closeConnection(con,stm,rs);

        }
        return allOrder;
    }
}
