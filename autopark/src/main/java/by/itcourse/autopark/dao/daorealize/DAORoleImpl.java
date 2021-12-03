package by.itcourse.autopark.dao.daorealize;

import by.itcourse.autopark.bean.Role;
import by.itcourse.autopark.dao.DAORole;
import by.itcourse.autopark.dao.connectionpool.ConnectionPool;
import by.itcourse.autopark.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAORoleImpl implements DAORole {
    private static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`role` (`tittle`) VALUES(?)";
    private static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`role` WHERE id=?";
    private static final String REQUEST_FOR_SELECT_ALL_ROLES="SELECT * FROM park.roles";

    static final ConnectionPool connect=ConnectionPool.getInstance();
    @Override
    public Role findRole(int id) throws DAOException {
        Connection con=null;
        PreparedStatement pstm=null;

        Role role = new Role();
        try {
            con = connect.takeConnection();

            String request = "SELECT * FROM park.role WHERE id=?";
            pstm = con.prepareStatement(request);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                role.setRole(rs.getString("tittle"));

            }
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
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
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
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
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,pstm);

        }
    }

    public List<Role> getAllRoles() throws DAOException {
        List<Role> allRoles = new ArrayList<>();
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try {
            con=connect.takeConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(REQUEST_FOR_SELECT_ALL_ROLES);
            Role role = new Role();
            while (rs.next()) {
                role.setRole(rs.getString("tittle"));
                role.setId(rs.getInt("id"));
                allRoles.add(role);
                role = null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        finally {
            connect.closeConnection(con,stm,rs);

        }
        return allRoles;
    }
}
