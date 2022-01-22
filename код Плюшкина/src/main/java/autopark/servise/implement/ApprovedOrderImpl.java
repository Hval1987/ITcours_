package autopark.servise.implement;
/**
 *This is my class where I perform various actions with approved orders
 */

import autopark.bean.ApprovedOrder;
import autopark.bean.Car;
import autopark.bean.Order;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.ApprovedOrderService;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;

import java.util.*;

public class ApprovedOrderImpl implements ApprovedOrderService {
    private static final String STATUS_IN_PROCESS="IN_PROCESS";
    private static final String STATUS_COMPLITED="COMPLITED";

    /**
     *This method searches for orders for a specific driver by ID
     *
     * @param id  Identification number of the driver for whom we are looking for order
     * @throws ServiceException
     */
    @Override
   public  HashMap<ApprovedOrder,String> findApprovedDriversOrder(int id) throws ServiceException {
        /**
         * In this collection we put the order and the date of this order
         */
        HashMap <ApprovedOrder,String> map=new HashMap<>();
        List<ApprovedOrder> list;
        try {
            list = DAOFactory.getInstance().getApprovedOrderDAO().getAllApprovedOrders();
        }
        catch(DAOException exc){
            System.out.println(exc);
            throw new ServiceException();

        }
        for (ApprovedOrder appr:list) {
            ApprovedOrder tmp; //создам новый объект, ибо в мап ложится только первый
            tmp=appr;
            if(appr.getIdDriver()==id) {
                int idOrder = appr.getIdOrder();
                String date = null;
                try {
                    date = DAOFactory.getInstance().getDAOOrder().findOrderInfo(idOrder).getDate();
                } catch (DAOException exc) {
                    System.out.println(exc + "ошибка слоя DAO");
                    throw new ServiceException();
                }
                map.put(tmp, date);
            }
        }
         return map;
        }

    @Override
    public ApprovedOrder findApprovedOrder(int id) throws ServiceException {
        return null;
    }

    /**
     *This method checks the fields and adds the approved order to the database
     * @param ord Not yet verified approved order
     * @throws ServiceException  Exception of the DAO layer
     * @throws DataErrorException Exception of entry data
     */

    @Override
    public void addApprovedOrder(ApprovedOrder ord) throws ServiceException, DataErrorException {
        if (ord != null) {
            if (ord.getIdDriver() != 0) {
                try {
                    DAOFactory.getInstance().getApprovedOrderDAO().addApprovedOrder(ord);
                } catch (DAOException exc) {
                    System.out.println("ошибка слоя дао");
                    throw new ServiceException();
                }
            }
            else{
                throw new DataErrorException();
            }

        }
    }

    /**
     * Deleting an order
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public void deleteApprovedOrder(int id) throws ServiceException {
    }

    /**
     * This method selects all processed approved orders from the database
     * @return List of all approved orders
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public List<ApprovedOrder> getAll() throws ServiceException {
        return null;
    }

    /**
     *  Mark the order as completed after completion
     * @param id id of the completed order
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public void ChangeOrderAsComplited(int id) throws ServiceException {
        ApprovedOrder apprvOrd;

        try{
            if(DAOFactory.getInstance().getApprovedOrderDAO().findApprovedOrder(id).getStatus().equals(STATUS_IN_PROCESS));
            apprvOrd=DAOFactory.getInstance().getApprovedOrderDAO().findApprovedOrder(id);

            DAOFactory.getInstance().getApprovedOrderDAO().updateStatus(STATUS_COMPLITED,id); //меняем статус на ЗАВЕРШЕННЫЙ


        }catch(DAOException exc){
            throw new ServiceException();
        }

    }

    /**
     * In this method, we are looking for cars that have not yet been booked for orders
     *
     * @param orderId ID of the order for which we are looking for available cars by date
     * @return list of available cars
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public List<Car> ChooseFreeCar(int orderId) throws ServiceException {
        List<Car> car;
        List<Car> requiredList =new ArrayList<>();
        Order order=null;
        List<ApprovedOrder> approvedOrderList=null;
        try{
            car=DAOFactory.getInstance().getDAOCar().getAllCars();

        }
        catch(DAOException exc){
            System.out.println(exc);

            throw new ServiceException(exc);
        }
        try {
            order = DAOFactory.getInstance().getDAOOrder().findOrderInfo(orderId);
        }catch (DAOException exc){
            System.out.println(exc);
            throw new ServiceException(exc);
        }
        try{
            approvedOrderList=DAOFactory.getInstance().getApprovedOrderDAO().getAllApprovedOrders();
        }catch (DAOException exc){
            System.out.println(exc);
            new ServiceException(exc);
        }
        String typeOfTransport=order.getTransportType();

        /**
         * we use this label to determine whether the car passes the criteria or not
         */
        boolean flag=true; //флажок, который показывает, соответствует ли машина из списка всем условиям
        /**
         * In this cycle, we check the name of the car in the list of already processed orders,
         * and then we compare the order dates and the corresponding date of the processed order
         */
        for ( Car tmp : car  ) {

            if(tmp.getTransportType().equals(typeOfTransport)){
                for(ApprovedOrder apprvdTmp:approvedOrderList) {

                    if (tmp.getId() == apprvdTmp.getIdAssignedCar()) {
                        if(order.getDate().equals(apprvdTmp.getDate())){
                            flag=false;
                            System.out.println("ломаемся по дате ");
                            break;
                        }

                    }
                }
            }
            else{
                flag=false;  //не совпал тип транспорта-

            }

            if(flag){  //авто прошло все инстанции))

                requiredList.add(tmp);

                flag=true;
            }
            /**
             *Setting the flag to the starting position for the next cycle
             */
            flag=true;  //установка флага в исходное положение , ибо он паскуда не корректно работает

        }

        return requiredList;
    }
}