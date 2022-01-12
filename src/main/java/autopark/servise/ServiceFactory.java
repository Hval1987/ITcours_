package autopark.servise;

import autopark.dao.DAOFactory;
import autopark.servise.implement.*;

public class ServiceFactory {
    private static ServiceFactory instance=new ServiceFactory();

    private ServiceFactory(){}

    private CarService carService=new CarServiceImpl();
    private DamageService damageService=new DamageServiceImpl();
    private OrderService orderService=new OrderServiceImpl();
    private RoleService roleService=new RoleServiceImpl() ;
    private UserService userService=new UserServiceImpl();
    private ApprovedOrderService approvedOrderService=new ApprovedOrderImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }
    public CarService getCarService(){
        return carService;
    }
    public DamageService getDamageService(){
        return damageService;
    }
    public OrderService getOrderService(){return orderService;}

    public RoleService getRoleService(){
        return roleService;
    }
    public UserService getUserService(){return userService; }
    public ApprovedOrderService getApprovedOrderService(){ return approvedOrderService; }

}

