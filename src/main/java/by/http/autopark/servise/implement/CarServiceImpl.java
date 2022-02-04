package by.http.autopark.servise.implement;

import by.http.autopark.bean.ApprovedOrder;
import by.http.autopark.bean.Car;
import by.http.autopark.dao.DAOFactory;
import by.http.autopark.dao.exception.DAOException;
import by.http.autopark.servise.CarService;
import by.http.autopark.servise.exception.EntryCarInfoException;
import by.http.autopark.servise.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the service interface for operations on cars
 */
public class CarServiceImpl implements CarService {
    public static final String CAR_SERVISABLE="SERVISEABLE";
    public static final String CAR_FAULTY="FAULTY";
    /**
     *  In this method we change the status of the car in case of breakdown or repair
     * @param id  vehicle id
     * @throws ServiceException  Exception of the DAO layer
     */
    @Override
    public void ChangeAvailAbilityCar(int id) throws ServiceException {
        if(id!=0){
            Car selectedСar=null;
            try {
                selectedСar = DAOFactory.getInstance().getDAOCar().findCar(id);
            }catch(DAOException exc){
                System.out.println("DAO layer error");
                throw new ServiceException(exc);

            }

            if(selectedСar.getAvailable().equals(CAR_SERVISABLE)){
                try {
                    DAOFactory.getInstance().getDAOCar().changeAvailability(CAR_FAULTY, id);
                }catch (DAOException exc){
                    System.out.println("DAO layer error");
                    throw new ServiceException(exc);
                }
            }
            else
                try {
                    DAOFactory.getInstance().getDAOCar().changeAvailability(CAR_SERVISABLE, id);
                }catch (DAOException exc){
                    System.out.println("DAO layer error");
                    throw new ServiceException(exc);
                }
        }
    }

    /**
     * Сheck the correctness of the id
     * and search for the car by ID in the database using the DAO
     * @param id vehicle id
     * @return the found car
     * @throws ServiceException  Exception of the DAO layer
     */
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
                System.out.println("DAO layer error");
              throw new ServiceException(exc);
            }
       }
        else{
            car=null;}

        return car;
    }

    /**
     * Check the correctness of the entered data
     * and send a command to the DAO layer to add the car to the database
     * @param car a Car type object that we want to add to the database
     * @throws ServiceException  Exception of the DAO layer
     */
    @Override
    public void addCar(Car car) throws ServiceException, EntryCarInfoException {

        String carNumber = car.getRegNumber().trim().replaceAll("\\s+","");
        int idDriver=car.getDriverId();

        if (car != null) {
            if (carNumber.matches("[0-9]{4}[А-Я]{2}[\\-][0-7]")) {
                if(idDriver>=0) {

                    DAOFactory daoFactory = DAOFactory.getInstance();

                    try {
                        car.setAvailable(CAR_SERVISABLE);
                        daoFactory.getDAOCar().addCar(car);
                    } catch (DAOException e) {
                        e.printStackTrace();
                        throw new ServiceException(e);
                    }
                }
                else
                    throw new  EntryCarInfoException("incorrect name entered");

            } else {
                throw new EntryCarInfoException("error when entering the registration number");

            }
        }
    }

    /**
     * Send a command to the DAO layer to remove the car from the database
     * if it is not booked for the order
     * @param id @param id vehicle id
     * @return report on successful deletion
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public boolean deleteCarById(int id) throws ServiceException {
        if(id!=0){
            List<ApprovedOrder> list=new ArrayList<>();
            try {

                list=DAOFactory.getInstance().getApprovedOrderDAO().findApproveOrderByCar(id);
                if(list.size()==0) {
                    DAOFactory daoFactory = DAOFactory.getInstance();
                    daoFactory.getDAOCar().deleteCarById(id);

                    return true;
                }
                else{

                    return false;
                }

            }catch (DAOException  exc){
                throw new ServiceException(exc);
            }
        }

        return false;

    }

    /**
     * Get a list of all cars from the database using DAO
     * @return list of all cars
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public List<Car> getAllCars() throws ServiceException {
        List<Car> list;

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            list=daoFactory.getDAOCar().getAllCars();
        }catch (DAOException  exc) {
            System.out.println("DAO layer error");
            throw new  ServiceException(exc);
        }
            return  list;
    }
}
