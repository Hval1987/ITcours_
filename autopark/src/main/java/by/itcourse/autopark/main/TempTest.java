package by.itcourse.autopark.main;

import by.itcourse.autopark.dao.daorealize.DAOCarImpl;
import by.itcourse.autopark.bean.Car;

import java.sql.SQLException;
import java.util.*;

public class TempTest {
    public static void main(String[] args) {


//        HashMap<String ,Integer> map=new HashMap<>();
//        ArrayList<String> list=new ArrayList<>();
//        map.put("String",1);
//        System.out.println(map);


        try {
            //просто проверяю методы
            Car car = new DAOCarImpl().findCar(2);
            System.out.println(car);
            List<Car> list = new DAOCarImpl().getAllCars();
            System.out.println(Arrays.toString(list.toArray()));
            Car car1 = new Car("truck", 1, "ВП 7845-5");
            //new DAOCarImpl().addCar(car1);
            //new DAOCarImpl().deleteCarById(31);
        } catch (SQLException exc) {
            System.out.println("просто перехватываем исключение");
        }

    }
}



