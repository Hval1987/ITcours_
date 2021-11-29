package by.itcourse.autopark.dao.daoImplements;


import by.itcourse.autopark.dao.DAOCar;
import by.itcourse.autopark.bean.Car;
import by.itcourse.autopark.dao.connectionPool.ConnectionPool;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCarImpl implements DAOCar {
    static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.car WHERE id=?";
    static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`car` " +
            "(`idCharacteristic`, `workable`, `regNumber`) VALUES(?,?,?)";
    static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`car` WHERE id=?";
    static final String REQUEST_FOR_SELECT_ALL_CARS="SELECT * FROM park.car ";


    final ConnectionPool connect=ConnectionPool.getInstance();

    public Connection getConnection() throws SQLException {
        return  connect.takeConnection();
    }

    @Override
    public Car findCar(int id) throws SQLException {

        Car car = null;
        Connection con=getConnection();
        try {

            PreparedStatement pstm=con.prepareStatement(REQUEST_FOR_SEARCH);
            pstm.setInt(1,id);

            ResultSet rs=pstm.executeQuery();
            car=new Car();
            while(rs.next()){
                car.setId_Characteristic(rs.getString("idCharacteristic"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setWorkable(rs.getInt("workable"));
                car.setId(id);
            }
            connect.closeConnection(con,pstm,rs);

            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public void addCar(Car car) throws DAOException, SQLException {
        Connection con=getConnection();

        try {

            con.setAutoCommit(false);

            PreparedStatement pstm=con.prepareStatement(REQUEST_FOR_INSERT);

            pstm.setString(1,car.getId_Characteristic());
            pstm.setInt(2,car.getWorkable());
            pstm.setString(3,car.getRegNumber());
            pstm.executeUpdate();

            con.commit();

            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCarById(int id) throws SQLException {
        Connection con=getConnection();
        try {

            PreparedStatement pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1,id);
            pstm.executeUpdate();
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> getAllCars() throws SQLException {
        List<Car> allCars=new ArrayList<>();
        Connection con=getConnection();
        try {

            Statement stm=con.createStatement();

            ResultSet rs=stm.getResultSet();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_CARS);

            while(rs.next()){
                Car car=new Car();
                car.setId_Characteristic(rs.getString("idCharacteristic"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setWorkable(rs.getInt("workable"));
                car.setId(rs.getInt("id"));
                allCars.add(car);
                car=null;

            }
            connect.closeConnection(con,stm,rs);

            return allCars;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return  allCars;
    }

}
