package by.http.autopark.dao.daoimplement;

import by.http.autopark.bean.Role;
import by.http.autopark.dao.DAORole;
import by.http.autopark.dao.connectionpool.ConnectionPool;
import by.http.autopark.dao.exception.ConnectionPoolException;
import by.http.autopark.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In this class, we implement the DAO interface
 * to access the database for operations with the class  Role
 */

public class DAORoleImpl implements DAORole {

    private  static final Logger logger= LoggerFactory.getLogger(DAORoleImpl.class.getName());

    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`role` (`tittle`) VALUES(?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`role` WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_ROLES="SELECT * FROM park.role";
    private static final String REQUEST_FOR_FIND_BY_ID="SELECT * FROM park.role WHERE id=?";
    private static final String REQUEST_FOR_FIND_BY_TITTLE="SELECT * FROM park.role WHERE tittle=?";

    private static final String COLUMN_TITTLE="tittle";
    private static final String COLUMN_ID="id";

    final ConnectionPool connect=ConnectionPool.getInstance();

    /**
     * in this method, we are looking for a role by id
     * @param id  Identification number of role
     * @return istanсe of class role
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public Role findRole(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;

        Role role = new Role();
        try {
            con = connect.takeConnection();

            String request =REQUEST_FOR_FIND_BY_ID;
            pstm = con.prepareStatement(request);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_FIND_BY_ID+" выполнена успешно");
            while (rs.next()) {
                role.setRole(rs.getString(COLUMN_TITTLE));
            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return role;
    }

    /**
     * this method returns an object
     * of the role class by the name of the role
     * @param tittle name of the role
     * @return instance of the role class
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public Role findRoleByTittle(String tittle) throws DAOException {
        connect.initPoolData();
        Connection con=null;
        PreparedStatement pstm=null;

        Role role = new Role();
        try {
            con = connect.takeConnection();

            String request = REQUEST_FOR_FIND_BY_TITTLE;
            pstm = con.prepareStatement(request);
            pstm.setString(1, tittle);

            ResultSet rs = pstm.executeQuery();
            logger.debug("комманда -"+REQUEST_FOR_FIND_BY_TITTLE+" выполнена успешно");
            while (rs.next()) {
                role.setId(rs.getInt(COLUMN_ID));
                role.setRole(tittle);

            }

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
        return role;
    }

    /**
     * in this method, we add
     * a specific role to the database
     * @param role
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public void addRole(Role role) throws DAOException {
        PreparedStatement pstm=null;
        Connection con=null;

        try {

            con=connect.takeConnection();

            pstm = con.prepareStatement(REQUEST_FOR_INSERT);
            pstm.setString(1, role.getRole());
            pstm.executeUpdate();

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    /**
     * in this method, we remove  role from the database by id
     * @param id Identification number of role
     * @throws DAOException Exception of the DAO layer
     */
    @Override
    public void deleteRoleId(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;
        try {
            con=connect.takeConnection();
            pstm = con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            logger.debug("комманда -"+REQUEST_FOR_DELETE_BY_ID+" выполнена успешно");

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,pstm);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }

    /**
     * in this method we get all rolls from database
     * @return list off all rolles
     * @throws DAOException Exception of the DAO layer
     */
    public List<Role> getAllRoles() throws DAOException {
        connect.initPoolData();
        List<Role> allRoles = new ArrayList<>();
        Connection con=connect.takeConnection();
        Statement stm=null;
        ResultSet rs=null;

        try {

            stm = con.createStatement();
            rs = stm.executeQuery(REQUEST_FOR_SELECT_ALL_ROLES);
            logger.debug("комманда -"+REQUEST_FOR_SELECT_ALL_ROLES+" выполнена успешно");

            while (rs.next()) {
                Role role = new Role();
                role.setRole(rs.getString(COLUMN_TITTLE));
                role.setId(rs.getInt(COLUMN_ID));
                allRoles.add(role);
                role = null;
            }


        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            try {
                connect.closeConnection(con,stm,rs);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }

        }
        return allRoles;
    }
}
