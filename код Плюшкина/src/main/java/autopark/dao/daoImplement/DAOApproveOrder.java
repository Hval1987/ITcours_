package autopark.dao.daoImplement;

import autopark.bean.ApprovedOrder;
import autopark.dao.DAOApprovedOrder;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOApproveOrder implements DAOApprovedOrder {


        private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`approved_order` " +
                                   "(id_assigned_car, status, id_employer,id_driver,id_manager,id_order,date) VALUES(?,?,?,?,?,?,?)";
        private static final String REQUEST_FOR_DELETE_BY_ID="DELETE FROM park.approved_order WHERE id=?";
        private static final String REQUEST_FOR_SELECT_ALL_ORDERS="SELECT * FROM park.approved_order ";
        private static final String REQUEST_FOR_SELECT_BY_ID="SELECT * FROM park.approved_order WHERE id=?";
        private static final String REQUEST_FOR_SELECT_BY_STATUS="SELECT * FROM park.approved_order WHERE status=?";
        private static final String UPDATE_STATUS="UPDATE `park`.`approved_order` SET `status` = ? WHERE id =?";
        private static final String REQUEST_FOR_SELECT_BY_CAR="SELECT * FROM park.approved_order WHERE id_assigned_car=?";

        public ConnectionPool connect=ConnectionPool.getInstance();



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
            approvedOrder=new ApprovedOrder();
            while(rs.next()){
                approvedOrder.setIdAssignedCar(rs.getInt("id_assigned_car"));
                approvedOrder.setIdDriver(rs.getInt("id_driver"));
                approvedOrder.setIdEmployer(rs.getInt("id_employer"));
                approvedOrder.setIdManager(rs.getInt("id_manager"));
                approvedOrder.setStatus(rs.getString("status"));
                approvedOrder.setId(id);
                System.out.println(approvedOrder);
            }

            return approvedOrder;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {

            connect.closeConnection(con,pstm,rs);

        }

    }

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

            while(rs.next()){
                ApprovedOrder approvedOrder=new ApprovedOrder();

                approvedOrder.setIdAssignedCar(rs.getInt("id_assigned_car"));
                approvedOrder.setIdDriver(rs.getInt("id_driver"));
                approvedOrder.setIdEmployer(rs.getInt("id_employer"));
                approvedOrder.setIdManager(rs.getInt("id_manager"));
                approvedOrder.setStatus(rs.getString("status"));
                approvedOrder.setIdOrder((rs.getInt("id_order")));
                approvedOrder.setId(rs.getInt("id"));

                System.out.println(approvedOrder);

                allApprovedOrder.add(approvedOrder);
                approvedOrder=null;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();


        }
        finally{
            connect.closeConnection(con,pstm,rs);

        }
        System.out.println(allApprovedOrder);
        return allApprovedOrder;

    }

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

            System.out.println("add approved order");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,pstm);
        }


    }

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
    public List<ApprovedOrder> getAllApprovedOrders() throws DAOException {
        connect.initPoolData();
        Connection con=connect.takeConnection();
        Statement stm=null;
        ResultSet rs=null;
        List<ApprovedOrder> allApprovedOrder=new ArrayList<>();

        try{
            stm=con.createStatement();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_ORDERS);



            while(rs.next()){
                ApprovedOrder approvedOrder=new ApprovedOrder();

                approvedOrder.setIdAssignedCar(rs.getInt("id_assigned_car"));
                approvedOrder.setIdDriver(rs.getInt("id_driver"));
                approvedOrder.setIdEmployer(rs.getInt("id_employer"));
                approvedOrder.setIdManager(rs.getInt("id_manager"));
                approvedOrder.setStatus(rs.getString("status"));
                approvedOrder.setIdOrder((rs.getInt("id_order")));
                approvedOrder.setId(rs.getInt("id"));



                allApprovedOrder.add(approvedOrder);
                approvedOrder=null;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();


        }
        finally{
            connect.closeConnection(con,stm,rs);

        }
        return allApprovedOrder;

    }

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
            System.out.println("Update status complited");


        }catch(SQLException exc){
            System.out.println("ошибка слоя ДАО");
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,pstm);

        }

    }

}


