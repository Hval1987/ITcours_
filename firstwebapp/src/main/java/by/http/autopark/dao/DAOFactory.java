package by.http.autopark.dao;



import by.http.autopark.dao.daoimplement.*;

/**
 * In this class, we implement a factory
 * for creating instances of classes-implementations of access to dao
 */
public final class DAOFactory {

    private static DAOFactory instance=new DAOFactory();

    private DAOCar sqlCarDAO = new DAOCarImpl();
    private DAOOrder sqlOrderDAO = new DAOOrderImpl();
    private DAORole sqlRoleDAO = new DAORoleImpl();
    private DAOUser sqlUserDAO = new DAOUserImpl();
    private DAOApprovedOrder sqlApprovedOrderDAO=new DAOApproveOrder() ;

    private DAOFactory()  {
    }

    public static DAOFactory getInstance() {
               return instance;
    }

    public DAOCar getDAOCar() {
        return sqlCarDAO;
    }

    public DAOOrder getDAOOrder() {
        return sqlOrderDAO;
    }

    public DAORole getDAORole() {
        return sqlRoleDAO;
    }

    public DAOUser getDAOUser () { return sqlUserDAO; }
    public DAOApprovedOrder getApprovedOrderDAO(){return  sqlApprovedOrderDAO; }

}
