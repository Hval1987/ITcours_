package by.itcourse.autopark.dao;

import by.itcourse.autopark.bean.Role;
import by.itcourse.autopark.dao.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface DAORole {
    Role findRole(int id ) throws  DAOException;

    void  addRole(Role role) throws DAOException;

    void deleteRoleId(int id) throws DAOException;

    List<Role> getAllRoles() throws  DAOException;
}
