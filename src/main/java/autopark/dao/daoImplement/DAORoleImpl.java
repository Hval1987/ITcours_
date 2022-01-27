package autopark.dao.daoImplement;

import autopark.bean.Role;
import autopark.dao.DAORole;
import autopark.dao.connectionPool.ConnectionPool;
import autopark.dao.exception.DAOException;
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

    final ConnectionPool connect=ConnectionPool.getInstance();
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
                role.setRole(rs.getString("tittle"));

            }
            connect.closeConnection(con,pstm);

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,pstm);

        }
        return role;
    }

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
                role.setId(rs.getInt("id"));
                System.out.println((rs.getInt("id")));
                role.setRole(tittle);

            }
            connect.closeConnection(con,pstm);

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,pstm);

        }

        return role;

    }

    @Override
    public void addRole(Role role) throws DAOException {
        PreparedStatement pstm=null;
        Connection con=null;

        try {

            con=connect.takeConnection();

            pstm = con.prepareStatement(REQUEST_FOR_INSERT);
            pstm.setString(1, role.getRole());
            pstm.executeUpdate();
            connect.closeConnection(con,pstm);

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,pstm);

        }

    }

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
            connect.closeConnection(con,pstm);

        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,pstm);

        }
    }

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

                role.setRole(rs.getString("tittle"));

                role.setId(rs.getInt("id"));

                allRoles.add(role);

                role = null;
            }


        } catch (SQLException exc) {
            logger.warn("",exc);
            throw new DAOException(exc);
        }
        finally {
            connect.closeConnection(con,stm,rs);

        }
        System.out.println(allRoles);
        return allRoles;
    }
}