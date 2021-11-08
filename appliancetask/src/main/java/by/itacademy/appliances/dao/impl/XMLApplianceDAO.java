package by.itacademy.appliances.dao.impl;


import by.itacademy.appliances.dao.ApplianceSourceDAO;
import by.itacademy.appliances.dao.exception.DAOException;
import by.itacademy.appliances.model.Appliance;
import by.itacademy.appliances.model.criteria.Criteria;

import java.util.ArrayList;

public class XMLApplianceDAO implements ApplianceSourceDAO {
    private Searcher searcher;
    {
     	searcher = new Searcher();
    }

	public ArrayList<Appliance> find(Criteria criteria) throws DAOException {
    	return searcher.find(criteria);
	}

	public boolean add(Appliance item) {
		return ApplianceRepository.getInstance().addAppliance(item);

	}

}
