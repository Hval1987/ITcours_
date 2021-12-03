package by.itcourse.autopark.dao;

import by.itcourse.autopark.dao.daorealize.DAOCarImpl;
import by.itcourse.autopark.dao.daorealize.DAOOrderImpl;
import by.itcourse.autopark.dao.daorealize.DAORoleImpl;
import by.itcourse.autopark.dao.daorealize.DAOUserImpl;


public final class DAOFactory {
    //фабрика для доступа к DAO из слоя  сервисов


    private static DAOFactory instance;


    private DAOCar DAOSqlCar = new DAOCarImpl();
    private DAOOrder DAOSqlOrder = new DAOOrderImpl();
    private DAORole DAOSqlRole = new DAORoleImpl();
    private DAOUser DAOSqlUser = new DAOUserImpl();


    private DAOFactory()  {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public DAOCar getDAOCar() {
        return DAOSqlCar;
    }

    public DAOOrder getDAOOrder() {
        return DAOSqlOrder;
    }

    public DAORole getDAORole() {
        return DAOSqlRole;
    }

    public DAOUser getDAOUser () {return DAOSqlUser; }

}
