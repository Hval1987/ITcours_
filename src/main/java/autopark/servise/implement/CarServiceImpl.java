package autopark.servise.implement;

import autopark.bean.Car;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.CarService;
import autopark.servise.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;


public class CarServiceImpl implements CarService {


    @Override
    public void ChangeAvailAbilityCar(int id) throws ServiceException {
        if(id!=0){
            Car selectedСar=null;
            try {
                selectedСar = DAOFactory.getInstance().getDAOCar().findCar(id);
            }catch(DAOException exc){
                System.out.println("ошибка слоя DAO");
                throw new ServiceException();

            }

            if(selectedСar.getAvailable().equals("SERVISABLE")){
                try {
                    DAOFactory.getInstance().getDAOCar().changeAvailability("FAULTY", id);
                }catch (DAOException exc){
                    System.out.println("ошибка слоя DAO");
                    throw new ServiceException();
                }

            }
            else
                try {
                    DAOFactory.getInstance().getDAOCar().changeAvailability("SERVISABLE", id);
                }catch (DAOException exc){
                    System.out.println("ошибка слоя DAO");
                    throw new ServiceException();
                }
        }

    }

    @Override
    public Car findCar(int id) throws ServiceException {
        Car car = null;
        if(id!=0) {
            try {
                DAOFactory daofactory = DAOFactory.getInstance();
                System.out.println(daofactory);
                car = daofactory.getDAOCar().findCar(id);
            }
            catch(DAOException exc ){
                System.out.println("ошибка слоя ДАО");
              throw new ServiceException();
            }
       }
        else
            car=null;

        return car;
    }

    @Override
    public void addCar(Car car) throws ServiceException {
        if(car!=null){
            DAOFactory daoFactory=DAOFactory.getInstance();
            try {
                car.setStatus("FREE");
                car.setAvailable("SERVISABLE");
                daoFactory.getDAOCar().addCar(car);
            } catch (DAOException  e) {
                e.printStackTrace();
              throw new ServiceException();
            }
        }


    }

    @Override
    public void deleteCarById(int id) throws ServiceException {
        if(id!=0){
            try {
                DAOFactory daoFactory = DAOFactory.getInstance();
                daoFactory.getDAOCar().deleteCarById(id);
            }catch (DAOException  exc){
                System.out.println("ошибка слоя ДАО");
              throw new ServiceException();
            }
        }

    }

    @Override
    public List<Car> getAllCars() throws ServiceException {
        List<Car> list=null;

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            list=daoFactory.getDAOCar().getAllCars();
        }catch (DAOException  exc) {
            System.out.println("ошибка слоя ДАО");
            throw new  ServiceException();
        }
            return  list;


    }
}
