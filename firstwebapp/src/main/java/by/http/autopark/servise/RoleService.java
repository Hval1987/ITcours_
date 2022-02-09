package by.http.autopark.servise;

import by.http.autopark.bean.Role;
import by.http.autopark.servise.exception.ServiceException;

import java.util.List;

/**
 * In this interface we define the behavior of Role objects
 */
public interface RoleService {
    Role findRole(int id );

    void  addRole(Role role) ;

    void deleteRoleId(int id) ;

    List<Role> getAllRoles() throws ServiceException;
    List<Role> getRolesByTittle(String tittle);
}
