package by.itcourse.autopark.dao.daoImplements;

import by.itcourse.autopark.dao.DAOCar;
import by.itcourse.autopark.beans.Car;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCarImpl implements DAOCar {
    //пока без пула


    @Override
    public Car findCar(int id) {
        Car car = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/park?useSSL=false","root", "1355725qwerty");
            Statement stm=con.createStatement();

            ResultSet rs=stm.getResultSet();
            String query="SELECT * FROM park.car WHERE id="+id;
            rs=stm.executeQuery(query);
            car=new Car();
            while(rs.next()){
                car.setId_Characteristic(rs.getString("idCharacteristic"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setWorkable(rs.getInt("workable"));
                car.setId(id);

            }

            return car;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {

        }
        return car;


    }

    @Override
    public void addCar(Car car) throws DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/park?useSSL=false","root", "1355725qwerty");
//            String query="INSERT INTO park.car (idCharacteristic, workable,regNumber) VALUES(?,?,?)";
//            PreparedStatement pstm=con.prepareStatement(query);
//            pstm.setString(1,car.getId_Characteristic());
//            pstm.setInt(2,car.getWorkable());
//            pstm.setString(3,car.getRegNumber());
//            pstm.executeQuery();
            //не работает почему-то, попробую так
            Statement stm=con.createStatement();
            String queryStm="INSERT INTO park.car (idCharacteristic, workable,regNumber) VALUES("+car.getId_Characteristic()+","+car.getWorkable()+","+car.getRegNumber()+")";
            stm.execute(queryStm);
            //опять не работает((
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {

            throw new DAOException();


        }

    }

    @Override
    public void deleteCarById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/park?useSSL=false","root", "1355725qwerty");
            Statement stm=con.createStatement();

            ResultSet rs=stm.getResultSet();
            String query="DELETE* FROM park.car WHERE id="+id;
            rs=stm.executeQuery(query);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> getAllCars() {
        List<Car> allCars=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/park?useSSL=false","root", "1355725qwerty");
            Statement stm=con.createStatement();

            ResultSet rs=stm.getResultSet();
            String query="SELECT * FROM park.car ";
            rs=stm.executeQuery(query);
//            Car car=new Car();

            while(rs.next()){
                Car car=new Car();
                car.setId_Characteristic(rs.getString("idCharacteristic"));
                car.setRegNumber(rs.getString("regNumber"));
                car.setWorkable(rs.getInt("workable"));
                car.setId(rs.getInt("id"));
                allCars.add(car);
                car=null;

            }

            return allCars;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  allCars;



    }
}
