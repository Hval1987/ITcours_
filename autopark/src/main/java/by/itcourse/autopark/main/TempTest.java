package by.itcourse.autopark.main;

import by.itcourse.autopark.dao.daoImplements.DAOCarImpl;
import by.itcourse.autopark.beans.Car;

import java.util.Arrays;
import java.util.List;

public class TempTest {
    public static void main(String[] args) {
        Car car= new DAOCarImpl().findCar(2);
        System.out.println(car);
        List<Car> list=new DAOCarImpl().getAllCars();
        System.out.println(Arrays.toString(list.toArray()));
        Car car1=new Car("truck",1,"ВП 7845-5");
        //System.out.println(car1);
        new DAOCarImpl().addCar(car1);

    }

}
