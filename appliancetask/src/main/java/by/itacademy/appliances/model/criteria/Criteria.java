package by.itacademy.appliances.model.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Criteria {

	private HashMap<String, String> params=new HashMap<String, String>();

	private ArrayList<String> valuesArrayString=new ArrayList<>(); //сюда будем закидывать строки с критериями
	private static final String FIND_IN_APPLIANCE_TYPE = "type";


	public Criteria() {}

	public Criteria(String applianceType) {
		params.put(FIND_IN_APPLIANCE_TYPE, applianceType);
	}

	public HashMap<String, String> addSearchCritearia(String key,String value){
		params.put(key, value);
		return params;
	}

	public ArrayList<String> getValuesArrayString()	{
		for(Map.Entry entry:params.entrySet()){
			valuesArrayString.add(entry.toString());
		}
		return valuesArrayString;
	}

}





	
	

