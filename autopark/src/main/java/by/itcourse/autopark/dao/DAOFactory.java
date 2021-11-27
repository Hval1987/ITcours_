package by.itcourse.autopark.dao;

import by.itcourse.autopark.dao.daoImplements.DAOCarImpl;

public class DAOFactory{

    private static DAOFactory instance=new DAOFactory();
    private DAOCar DAOSqlAvailableCar=new DAOCarImpl();
    //пока только для car



    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }
    public DAOCar getAvailibleCar(){
        return DAOSqlAvailableCar;
    }





}