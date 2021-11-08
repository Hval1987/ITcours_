package by.itacademy.appliances.service;


public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
    private final ApplianceServiceRealize applianceServiceRealize=new ApplianceServiceRealize();

//	private final ApplianceService ovenService = new OvenServiceImpl();
//	private final ApplianceService laptopService = new LaptopServiceImpl();
//	private final ApplianceService refrigeratorService = new RefrigeratorServiceImpl();
//	private final ApplianceService vacuumClnService = new VacuumClnServiceImpl();
//	private final ApplianceService tabletPcService = new TabletPcServiceImpl();
//	private final ApplianceService speakersService = new SpeakersServiceImpl();
//



	private ServiceFactory() {}

	public ApplianceServiceRealize getApplServiseRealize(){return applianceServiceRealize; }

//	public ApplianceService getOvenService() {
//		return ovenService;
//	}
//
//	public ApplianceService getLaptopService() {
//		return laptopService;
//	}
//
//	public ApplianceService getRefrigeratorService() {
//		return refrigeratorService;
//	}
//
//	public ApplianceService getVacuumClnService() {
//		return vacuumClnService;
//	}
//
//	public ApplianceService getTabletPcService() {
//		return tabletPcService;
//	}
//
//	public ApplianceService getSpeakersService() {
//		return speakersService;
//	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}
