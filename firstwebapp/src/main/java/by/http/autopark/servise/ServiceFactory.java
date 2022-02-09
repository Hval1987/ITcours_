package by.http.autopark.servise;



import by.http.autopark.servise.implement.*;

/**
 * In this singleton, we get the objects-implementations of the services layer,
 * while hiding their implementations
 */
public class ServiceFactory {
    private static ServiceFactory instance=new ServiceFactory();

    private ServiceFactory(){}

    private CarService carService=new CarServiceImpl();

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
    public OrderService getOrderService(){return orderService;}

    public RoleService getRoleService(){
        return roleService;
    }
    public UserService getUserService(){return userService; }
    public ApprovedOrderService getApprovedOrderService(){ return approvedOrderService; }

}

