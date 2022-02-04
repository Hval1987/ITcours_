package by.http.autopark.servise.implement;

import by.http.autopark.bean.Role;
import by.http.autopark.dao.DAOFactory;
import by.http.autopark.dao.exception.DAOException;
import by.http.autopark.servise.RoleService;
import by.http.autopark.servise.exception.ServiceException;

import java.util.List;

/**
 *implementation of the service interface
 * for roles used by project users
 */

public class RoleServiceImpl implements RoleService {

    /**
     * In this method we get a list of all roles
     * @return  list of all roles
     * @throws ServiceException Exception of the DAO layer
     */
    @Override
    public List<Role> getAllRoles() throws ServiceException {
        List<Role> list=null;

        try {
            list =DAOFactory.getInstance().getDAORole().getAllRoles();
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
        return list;

    }

    /**
     * In this method, we get the role id by its name
     * @param tittle name of role
     * @return id role
     */
    @Override
    public List<Role> getRolesByTittle(String tittle) {
        if(tittle!=null) {
            String tmpValue = tittle.toLowerCase().trim();
            int idRole;
        }

        return null;
    }


    @Override
    public Role findRole(int id) {
        return null;
    }

    @Override
    public void addRole(Role role) {

    }

    @Override
    public void deleteRoleId(int id) {

    }



}

