package by.itacademy.appliances.mainRun;


import by.itacademy.appliances.model.Appliance;
import by.itacademy.appliances.model.Laptop;
import by.itacademy.appliances.model.Oven;
import by.itacademy.appliances.model.criteria.Criteria;
import by.itacademy.appliances.service.ApplianceServiceRealize;
import by.itacademy.appliances.service.ServiceFactory;

import by.itacademy.appliances.service.exception.ServisException;
import org.xml.sax.SAXException;


import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, SAXException {
		Appliance appliance;
		Criteria criteria;


		ServiceFactory factory = ServiceFactory.getInstance();
		ApplianceServiceRealize service=factory.getApplServiseRealize();

		try {
			criteria = new Criteria("Oven");
			criteria.addSearchCritearia("power consumption", "1000");
			service.find(criteria);
			service.ShowInfo();
			System.out.println();


			//////////////////////////////////////////////////////////////////
			criteria = new Criteria();
			criteria.addSearchCritearia("power consumption", "17");
			service.find(criteria);
			service.ShowInfo();
			System.out.println();

			/////////////////////////////////////////////////////////////////
			criteria = new Criteria("vacuum Cleaner");
			service.find(criteria);
			service.ShowInfo();

			System.out.println(service.add(new Oven("oven","4",1500,
					55,78,95,75,40)));

			criteria = new Criteria("Oven");
			service.find(criteria);
			service.ShowInfo();
		}catch (ServisException exc){
			System.out.println("ошибка слоя Service -"+exc);
		}

	}

}
