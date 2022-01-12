package autopark.servise.implement;

import autopark.bean.ApprovedOrder;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.ApprovedOrderService;
import autopark.servise.exception.ServiceException;

import java.util.*;

public class ApprovedOrderImpl implements ApprovedOrderService {
    @Override
   public  HashMap<ApprovedOrder,String> findApprovedDriversOrder(int id) throws ServiceException {
        HashMap <ApprovedOrder,String> map=new HashMap<>();
        List<ApprovedOrder> list=new ArrayList<>();
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



    @Override
    public void addApprovedOrder(ApprovedOrder ord) throws ServiceException {
        if(ord!=null){
            try {
                DAOFactory.getInstance().getApprovedOrderDAO().addApprovedOrder(ord);
                int carId = ord.getIdAssignedCar();
                DAOFactory.getInstance().getDAOCar().changeStatus("BOOKING", carId);
            }catch(DAOException exc){
                System.out.println("ошибка слоя дао");
                throw new ServiceException();
            }

        }

    }

    @Override
    public void deleteApprovedOrder(int id) throws ServiceException {

    }

    @Override
    public List<ApprovedOrder> getAll() throws ServiceException {
        return null;
    }

    @Override
    public void ChangeOrderAsComplited(int id) throws ServiceException {
        ApprovedOrder apprvOrd;

        try{
            if(DAOFactory.getInstance().getApprovedOrderDAO().findApprovedOrder(id).getStatus().equals("IN PROCESS"));
            apprvOrd=DAOFactory.getInstance().getApprovedOrderDAO().findApprovedOrder(id);

            DAOFactory.getInstance().getApprovedOrderDAO().updateStatus("COMPLITED",id); //меняем статус на ЗАВЕРШЕННЫЙ
            DAOFactory.getInstance().getDAOCar().changeStatus("FREE",apprvOrd.getIdAssignedCar()); //берем IDавто из заказа и меняем статус на FREE

        }catch(DAOException exc){
            throw new ServiceException();
        }

    }
}