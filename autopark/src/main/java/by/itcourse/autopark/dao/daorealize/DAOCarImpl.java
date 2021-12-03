package by.itcourse.autopark.dao.daorealize;


import by.itcourse.autopark.dao.DAOCar;
import by.itcourse.autopark.bean.Car;
import by.itcourse.autopark.dao.connectionpool.ConnectionPool;
import by.itcourse.autopark.dao.exception.DAOException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCarImpl extends DAOConnection  implements DAOCar {
    private static final String  REQUEST_FOR_SEARCH="SELECT * FROM park.car WHERE id=?";
    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`car` " +
            "(`id Characteristic`, `workable`, `regNumber`) VALUES(?,?,?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`car` WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_CARS="SELECT * FROM park.car ";

    public ConnectionPool connect=getConnectionPool();

    public DAOCarImpl()  {
    }

    @Override
    public Car findCar(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        Car car = null;

        try {
            con=getConnectionPool().takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_SEARCH);
            pstm.setInt(1,id);

            rs=pstm.executeQuery();
            car=new Car();
            while(rs.next()){
                car.setId_Characteristic(rs.getString("id Characteristic"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setWorkable(rs.getInt("workable"));
                car.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
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
            con=getConnectionPool().takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_INSERT);
            pstm.setString(1,car.getId_Characteristic());
            pstm.setInt(2,car.getWorkable());
            pstm.setString(3,car.getRegNumber());
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
            con=getConnectionPool().takeConnection();

            pstm=con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1,id);
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
    public List<Car> getAllCars() throws DAOException {
        List<Car> allCars=new ArrayList<>();
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try {
            con=getConnectionPool().takeConnection();

            stm=con.createStatement();
            rs=stm.executeQuery(REQUEST_FOR_SELECT_ALL_CARS);

            while(rs.next()){
                Car car=new Car();
                car.setId_Characteristic(rs.getString("id Characteristic"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setWorkable(rs.getInt("workable"));
                car.setId(rs.getInt("id"));
                allCars.add(car);
                car=null;

            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,stm,rs);
        }
        return  allCars;
    }

}
