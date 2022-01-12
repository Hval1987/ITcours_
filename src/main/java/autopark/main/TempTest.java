package autopark.main;


import autopark.bean.ApprovedOrder;
import autopark.bean.Car;
import autopark.dao.DAOFactory;

import autopark.dao.exception.DAOException;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import autopark.servise.implement.CarServiceImpl;


import java.util.*;

public class TempTest {
    public static void main(String[] args) throws DAOException, ServiceException {




//        HashMap<String ,Integer> map=new HashMap<>();
//        ArrayList<String> list=new ArrayList<>();
//        map.put("String",1);
//        System.out.println(map);


        //просто проверяю методы
        Car car = new CarServiceImpl().findCar(2);

        System.out.println(car);
        List<Car> list = new CarServiceImpl().getAllCars();
        System.out.println(Arrays.toString(list.toArray()));

        Car car1 = new Car(1,"truck", 1, "ВП 7845-5","free","available","12.05.04");
        System.out.println(DAOFactory.getInstance().getDAORole().findRoleByTittle("driver"));
        System.out.println(ServiceFactory.getInstance().getUserService().findUsersByRole("driver"));
        List<ApprovedOrder> listic=DAOFactory.getInstance().getApprovedOrderDAO().getAllApprovedOrders();
        System.out.println(listic);
        //new DAOCarImpl().addCar(car1);
        //new DAOCarImpl().deleteCarById(31);

    }
}



