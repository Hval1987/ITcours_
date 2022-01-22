package autopark.servise.implement;

import autopark.bean.Role;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.RoleService;
import autopark.servise.exception.ServiceException;

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
            System.out.println("Ошибка слоя DAO");
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

