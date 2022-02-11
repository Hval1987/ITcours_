package by.http.autopark.dao;

import by.http.autopark.bean.Role;
import by.http.autopark.dao.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAORole {
    Role findRole(int id ) throws DAOException, SQLException;
    Role findRoleByTittle(String tittle) throws DAOException;

    void  addRole(Role role) throws DAOException, SQLException;

    void deleteRoleId(int id) throws DAOException, SQLException;

    List<Role> getAllRoles() throws  DAOException;
}
