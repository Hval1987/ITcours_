package autopark.dao.daoImplement;


import autopark.bean.Car;
import autopark.dao.DAOCar;

import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class, we implement the DAO interface
 * to access the database for operations with the car class
 */

public class DAOCarImpl implements DAOCar {

    private  static final Logger logger= LoggerFactory.getLogger(DAOCarImpl.class.getName());

    private static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.car WHERE id=?";
    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`car` " +
            "(`transport_type`, `driverId`, `regNumber`,`available`) VALUES(?,?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`car` WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_CARS="SELECT * FROM park.car ";
    private static final String UPDATE_AVAILABILITY="UPDATE `park`.`car` SET `available` = ? WHERE id =?";

    private ConnectionPool connect=ConnectionPool.getInstance();

    public DAOCarImpl()  {
    }

    public void changeAvailability(String availability, int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        try{
            con=connect.takeConnection();
            pstm=con.prepareStatement(UPDATE_AVAILABILITY);
            pstm.setString(1,availability);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            logger.debug("комманда -"+UPDATE_AVAILABILITY+" выполнена успешно");

        }catch(SQLException exc){
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,pstm);

        }

    }

    @Override
    public Car findCar(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        Car car = null;

        try {

            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_SEARCH);
            pstm.setInt(1,id);

            rs=pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_SEARCH+" выполнена успешно");
            car=new Car();
            while(rs.next()){
                car.setTransportType(rs.getString("transport_type"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setAvailable(rs.getString("available"));
                car.setDriverId(rs.getInt("driverId"));
                car.setId(id);
            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,pstm,rs);

        }
        return car;
    }


    @Override
    public void addCar(Car car) throws DAOException{
        Connection con=null;
        PreparedStatement pstm=null;

        try {

            con=connect.takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_INSERT);
            pstm.setString(1,car.getTransportType());
            pstm.setInt(2,car.getDriverId());
            pstm.setString(3,car.getRegNumber());
            pstm.setString(4,car.getAvailable());
            pstm.executeUpdate();
            logger.debug("комманда -"+REQUEST_FOR_INSERT+" выполнена успешно");


        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,pstm);

        }

    }

    @Override
    public void deleteCarById(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        try {

            con=connect.takeConnection();
            pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1,id);
            pstm.executeUpdate();
            logger.debug("комманда -"+REQUEST_FOR_DELETE_BY_ID+" выполнена успешно");
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
    public List<Car> getAllCars()  {
        List<Car> allCars=new ArrayList<>();
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try {

            con=connect.takeConnection();

            stm=con.createStatement();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_CARS);
            logger.debug("комманда -"+REQUEST_FOR_SELECT_ALL_CARS+" выполнена успешно");

            while(rs.next()){
                Car car=new Car();
                car.setTransportType(rs.getString("transport_type"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setDriverId(rs.getInt("driverId"));
                car.setId(rs.getInt("id"));
                car.setAvailable(rs.getString("available"));
                allCars.add(car);
                car=null;

            }


        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new RuntimeException(exc);
        }
        finally {
            connect.closeConnection(con,stm,rs);
        }
        return  allCars;
    }

}
