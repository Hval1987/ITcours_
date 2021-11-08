package by.itacademy.appliances.dao.impl;

import by.itacademy.appliances.dao.dom_parse.DomParser;
import by.itacademy.appliances.model.Appliance;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;


public class ApplianceRepository {
	private static final ApplianceRepository instance = new ApplianceRepository();

	private ArrayList<Appliance> data;
	private ApplianceRepository()  {
		try {
		    String  path=this.getClass().getClassLoader().getResource("applianced.xml").toString();
		   	DomParser parser = new DomParser();
			data = parser.parse(path);
		}catch(IOException | SAXException  exc ){
			System.out.println("error! alarm!! "+exc);
		}
	}
	
	public ArrayList<Appliance> getData(){
		return data;
	}
	
	public static ApplianceRepository getInstance() {
		return instance;
	}
	public boolean addAppliance(Appliance item){
		if (!(item instanceof Appliance)) {
            return false;
        } else if (item == null) {
            return false;
        }
        data.add(item);
        return true;
		// здесь наверное можно попробовать через jaxb  обратно в xml загнать, или же не нужно...??
	}
}
