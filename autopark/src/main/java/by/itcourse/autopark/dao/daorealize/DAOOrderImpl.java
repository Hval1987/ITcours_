package by.itcourse.autopark.dao.daorealize;

import by.itcourse.autopark.bean.Order;
import by.itcourse.autopark.dao.DAOOrder;
import by.itcourse.autopark.dao.connectionpool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOOrderImpl implements DAOOrder {

    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`order` " +
            "(`request`, `filling_date`, `employer_id`,`approved_order_id`) VALUES(?,?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE FROM park.order WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_ORDERS="SELECT * FROM park.order ";

    static final ConnectionPool connect=ConnectionPool.getInstance();

    @Override
    public Order findOrderInfo(int id) throws SQLException {
        Order order = null;
        Connection con=connect.takeConnection();
        Statement stm = null;
        ResultSet rs = null;

        try {
            stm=con.createStatement();

            rs=stm.getResultSet();
            String query="SELECT * FROM park.order WHERE id="+id;
            rs=stm.executeQuery(query);
            order=new Order();
            while(rs.next()){
                order.setRequest(rs.getString("request"));
                order.setFillingDate(rs.getString("filling_date"));
                order.setEmployerId(rs.getInt("employer_id"));
                order.setApprovedOrderId(id);

            }

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connect.closeConnection(con,stm,rs);
        }
        return order;

    }

    @Override
    public void createOrder(Order ord) throws SQLException {
        Connection con=connect.takeConnection();
        PreparedStatement pstm=null;

        try {
            con.setAutoCommit(false);
            pstm=con.prepareStatement(REQUEST_FOR_INSERT);


            pstm.setString(1,ord.getRequest());
            pstm.setString(2,ord.getFillingDate());
            pstm.setInt(3,ord.getEmployerId());
            pstm.setInt(4,ord.getApprovedOrderId());
            pstm.executeUpdate();

            con.commit();
            connect.closeConnection(con,pstm);
        } catch (SQLException e) {
            e.printStackTrace();
            }
        finally {
            connect.closeConnection(con,pstm);
           }

        }

    @Override
    public void deleteOrder(int id) throws SQLException {
        Connection con=connect.takeConnection();
        PreparedStatement pstm=null;
        try{
            pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1,id);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connect.closeConnection(con,pstm);
        }

    }

    @Override
    public List<Order> getAll() throws SQLException {
        Connection con=connect.takeConnection();
        Statement stm=null;
        ResultSet rs=null;
        List<Order> allOrder=new ArrayList<>();
        try{
            stm=con.createStatement();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_ORDERS);
            Order order=new Order();
            while(rs.next()){
                order.setRequest(rs.getString("request"));
                order.setFillingDate(rs.getString("filling_date"));
                order.setEmployerId(rs.getInt("employer_id"));
                order.setApprovedOrderId(rs.getInt("id"));

                allOrder.add(order);
                order=null;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            connect.closeConnection(con,stm,rs);

        }
        return allOrder;
    }
}
