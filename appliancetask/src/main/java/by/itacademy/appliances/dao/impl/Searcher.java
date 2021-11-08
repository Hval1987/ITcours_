package by.itacademy.appliances.dao.impl;

import by.itacademy.appliances.model.Appliance;
import by.itacademy.appliances.model.criteria.Criteria;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
	
	private ApplianceRepository repository;
	private List<String> searchData;
	private List<Appliance> findedAppliance;

	
	public Searcher() {
		this.repository = ApplianceRepository.getInstance() ;
	}
	

	public ArrayList<Appliance> find(Criteria criteria){
		findedAppliance=new ArrayList<>();
		List<String> criteriaList=criteria.getValuesArrayString();
		ArrayList<Appliance> appliances = repository.getInstance().getData();
		String tmpStr,tmpCriteria;

		int count=0; // счетчик, чтобы определить насколько наш объект совпадает с критериями
		//ищy элемент по строчкам метода toString
		for(int i=0; i<appliances.size();i++){
			count=0;
			tmpStr=appliances.get(i).toString().trim().toLowerCase();
			for(int j=0; j<criteriaList.size();j++){
				tmpCriteria=criteriaList.get(j).trim().toLowerCase();
				if(tmpStr.contains(tmpCriteria)) {
					count++;
				}
			}
			//если счетчик равен размеру листа с критериями, значит найдены все критерии,
			if(count==criteriaList.size()) {
				findedAppliance.add(appliances.get(i));
			}
		}
		return (ArrayList<Appliance>) findedAppliance;
	}

	private List<Appliance> initSearchData() {
		List<Appliance> appliances = repository.getInstance().getData();
		return appliances;
	}
}
