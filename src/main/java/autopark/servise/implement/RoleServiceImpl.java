package autopark.servise.implement;

import autopark.bean.Role;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.RoleService;
import autopark.servise.exception.ServiceException;

import java.util.List;

public class RoleServiceImpl implements RoleService {
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

    @Override
    public List<Role> getRolesByTittle(String tittle) {
        if(tittle!=null) {
            String tmpValue = tittle.toLowerCase().trim();
            int idRole;
        }

        return null;
    }

}

