package by.itacademy.appliances.dao;


import by.itacademy.appliances.dao.impl.XMLApplianceDAO;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final XMLApplianceDAO applianceDAO=new XMLApplianceDAO();



//	private final ApplianceSourceDAO ovenDAO = new OvenDAO();
//	private final ApplianceSourceDAO laptopDAO = new LaptopDAO();
//	private final ApplianceSourceDAO refrigeratorDAO = new RefrigeratorDAO();
//	private final ApplianceSourceDAO vacuumCleanerDAO = new VacuumCleanerDAO();
//	private final ApplianceSourceDAO tabletPCDAO = new TabletPcDAO();
//	private final ApplianceSourceDAO speakersDAO = new SpeakersDAO();
	
	private DAOFactory() {}

	public ApplianceSourceDAO getApplianceDAO(){return applianceDAO; }
//	public ApplianceSourceDAO getOvenDAO() {return ovenDAO;}
//	public ApplianceSourceDAO getLaptopDao() {return laptopDAO;}
//	public ApplianceSourceDAO getRefrigeratorDAO() {return refrigeratorDAO;}
//	public ApplianceSourceDAO getVacuumCleanerDAO() {return vacuumCleanerDAO;}
//	public ApplianceSourceDAO getTabletPCDAO() {return tabletPCDAO;}
//	public ApplianceSourceDAO getSpeakersDAODao() {return speakersDAO;}


	public static DAOFactory getInstance() {
		return instance;
	}
}
