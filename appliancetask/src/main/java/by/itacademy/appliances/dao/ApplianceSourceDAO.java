package by.itacademy.appliances.dao;



import by.itacademy.appliances.dao.exception.DAOException;
import by.itacademy.appliances.model.Appliance;
import by.itacademy.appliances.model.criteria.Criteria;

import java.util.ArrayList;

public interface ApplianceSourceDAO {
	ArrayList<Appliance> find(Criteria criteria) throws DAOException;
	boolean add(Appliance newAppliance);
}
