package by.itacademy.appliances.service;


import by.itacademy.appliances.model.Appliance;
import by.itacademy.appliances.model.criteria.Criteria;
import by.itacademy.appliances.service.exception.ServisException;

import java.util.ArrayList;

public interface ApplianceService {	
	
	 ArrayList<Appliance> find(Criteria criteria) throws ServisException;
	 void ShowInfo();
	 boolean add(Appliance item );


	
}
