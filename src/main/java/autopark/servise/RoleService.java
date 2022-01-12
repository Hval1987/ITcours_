package autopark.servise;

import autopark.bean.Role;
import autopark.servise.exception.ServiceException;

import java.util.List;

public interface RoleService {
    Role findRole(int id );

    void  addRole(Role role) ;

    void deleteRoleId(int id) ;

    List<Role> getAllRoles() throws ServiceException;
    List<Role> getRolesByTittle(String tittle);
}
