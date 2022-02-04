package by.http.autopark.dao.daoimplement;


import by.http.autopark.bean.Car;
import by.http.autopark.dao.DAOCar;

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
 * to access the database for operations with the car class
 */

public class DAOCarImpl implements DAOCar {



    private static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.car WHERE id=?";
    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`car` " +
            "(`transport_type`, `driverId`, `regNumber`,`available`) VALUES(?,?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`car` WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_CARS="SELECT * FROM park.car ";
    private static final String UPDATE_AVAILABILITY="UPDATE `park`.`car` SET `available` = ? WHERE id =?";

    private static final String COLUMN_TRANSPORT_TYPE="transport_type";
    private static final String COLUMN_REGISTRATION_NUMBER="regNumber";
    private static final String COLUMN_AVAILABLE="available";
    private static final String COLUMN_DRIVER_ID="driverId";
    private static final String COLUMN_ID="id";


    private  static final Logger logger= LoggerFactory.getLogger(DAOCarImpl.class.getName());
    private ConnectionPool connect=ConnectionPool.getInstance();

    public DAOCarImpl()  {
    }

    /**
     * this method selects all available cars from the database by status
     * @param availability status of ability of car
     * @param id Identification number of changed car
     * @throws DAOException Exception of the DAO layer
     */
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
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    /**
     * this method searches for a car in the database by id
     * @param id ID of the car you are looking for.
     * @return instance of the car object
     * @throws DAOException instance of the car class
     */
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
                car.setTransportType(rs.getString(COLUMN_TRANSPORT_TYPE));
                car.setRegNumber(rs.getString(COLUMN_REGISTRATION_NUMBER));
                car.setAvailable(rs.getString(COLUMN_AVAILABLE));
                car.setDriverId(rs.getInt(COLUMN_DRIVER_ID));
                car.setId(id);
            }

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
        return car;
    }

    /**
     * this method adds an instance of the car class to the database
     * @param car instance of the car class
     * @throws DAOException instance of the car class
     */
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
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }

    }

    /**
     * this method removes an instance
     * of the car class from the database
     * @param id ID of the deleted car
     * @throws DAOException instance of the car class
     */
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
     * this method gets all cars from the database
     * @return list of cars
     */
    @Override
    public List<Car> getAllCars() throws DAOException {
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
                car.setTransportType(rs.getString(COLUMN_TRANSPORT_TYPE));
                car.setRegNumber(rs.getString(COLUMN_REGISTRATION_NUMBER));
                car.setDriverId(rs.getInt(COLUMN_DRIVER_ID));
                car.setId(rs.getInt(COLUMN_ID));
                car.setAvailable(rs.getString(COLUMN_AVAILABLE));
                allCars.add(car);
                car=null;

            }


        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,stm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return  allCars;
    }

}
