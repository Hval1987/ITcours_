package autopark.servise.implement;

import autopark.bean.ApprovedOrder;
import autopark.bean.Car;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.CarService;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the service interface for operations on cars
 */
public class CarServiceImpl implements CarService {
    public static final String CAR_SERVISABLE="SERVISEABLE";
    public static final String CAR_FAULTY="FAULTY";
    @Inject
    Validator validator;

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
    public void addCar(Car car) throws ServiceException, DataErrorException {
//        ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
//        Validator validator=validatorFactory.getValidator();
//       Set<ConstraintViolation<Car>> set=validator.validate(car);
//        System.out.println(set.size()+"set ");
//

        if(car!=null){
            DAOFactory daoFactory=DAOFactory.getInstance();
            try {
                car.setAvailable(CAR_SERVISABLE);
                daoFactory.getDAOCar().addCar(car);
            } catch (DAOException  e) {
                e.printStackTrace();
              throw new ServiceException(e);
            }
        }
        else{
            throw new DataErrorException();
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
                System.out.println("Service -list-"+ list);
                if(list.size()==0) {
                    DAOFactory daoFactory = DAOFactory.getInstance();
                    daoFactory.getDAOCar().deleteCarById(id);

                    return true;
                }
                else{

                    return false;
                }

            }catch (DAOException  exc){
                System.out.println("DAO layer error");
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
        List<Car> list=null;

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
