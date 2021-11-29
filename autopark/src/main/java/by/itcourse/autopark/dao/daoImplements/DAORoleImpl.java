package by.itcourse.autopark.dao.daoImplements;

import by.itcourse.autopark.bean.Role;
import by.itcourse.autopark.dao.DAORole;
import by.itcourse.autopark.dao.connectionPool.ConnectionPool;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAORoleImpl implements DAORole {
    static final String  REQUEST_FOR_INSERT="INSERT INTO `park`.`role` (`tittle`) VALUES(?)";
    static final String REQUEST_FOR_DELETE_BY_ID="DELETE  FROM `park`.`role` WHERE id=?";
    static final String REQUEST_FOR_SELECT_ALL_ROLES="SELECT * FROM park.roles";

    final ConnectionPool connect=ConnectionPool.getInstance();
    @Override
    public Role findRole(int id) throws SQLException {
        Connection con = connect.takeConnection();
        Role role = new Role();
        try {

            String request = "SELECT * FROM park.role WHERE id=?";
            PreparedStatement pstm = con.prepareStatement(request);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                role.setRole(rs.getString("tittle"));

            }
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public void addRole(Role role) throws DAOException, SQLException {
        Connection con=connect.takeConnection();
        try {

            PreparedStatement pstm = con.prepareStatement(REQUEST_FOR_INSERT);
            pstm.setString(1, role.getRole());
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRoleId(int id) throws SQLException {
        Connection con=connect.takeConnection();
        try {
            PreparedStatement pstm = con.prepareStatement(REQUEST_FOR_DELETE_BY_ID);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            connect.closeConnection(con,pstm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Role> getAllRoles() throws SQLException {
        List<Role> allRoles = new ArrayList<>();
        Connection con=connect.takeConnection();
        try {

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(REQUEST_FOR_SELECT_ALL_ROLES);
            Role role = new Role();
            while (rs.next()) {
                role.setRole(rs.getString("tittle"));
                role.setId(rs.getInt("id"));
                allRoles.add(role);
                role = null;
            }
            connect.closeConnection(con,stm,rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRoles;
    }
}
