package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Role;
import by.itcourse.autopark.dao.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAORole {
    Role findRole(int id ) throws SQLException;

    void  addRole(Role role) throws DAOException, SQLException;

    void deleteRoleId(int id) throws SQLException;

    List<Role> getAllRoles() throws SQLException;
}
