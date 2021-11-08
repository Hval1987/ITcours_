package by.itacademy.appliances.service;



import by.itacademy.appliances.dao.DAOFactory;
import by.itacademy.appliances.dao.exception.DAOException;
import by.itacademy.appliances.model.Appliance;
import by.itacademy.appliances.model.criteria.Criteria;
import by.itacademy.appliances.service.exception.ServisException;

import java.util.ArrayList;

public class ApplianceServiceRealize implements ApplianceService {
    private ArrayList<Appliance> findedAppliance=new ArrayList<>();

    @Override
    public ArrayList<Appliance> find(Criteria criteria) throws ServisException {
        DAOFactory factory= DAOFactory.getInstance();
        try {
            findedAppliance = factory.getApplianceDAO().find(criteria);
            return findedAppliance;
        }catch (DAOException exc){
            System.out.println("ошибка слоя DAO");
            throw new ServisException();
        }

    }

    @Override
    public void ShowInfo() {

        for(Appliance currentApp:findedAppliance)
            System.out.println(currentApp);

    }
    public boolean add(Appliance item){
        DAOFactory factory=DAOFactory.getInstance();
        return factory.getApplianceDAO().add(item);

    }
}
