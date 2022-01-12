package servlet.impl;

import autopark.bean.ApprovedOrder;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import com.mysql.cj.Session;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddApprovedOrder implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ApprovedOrder approvedOrder=new ApprovedOrder();

        approvedOrder.setStatus("IN_PROCESS");
        approvedOrder.setIdManager((Integer) request.getSession().getAttribute("userId"));

        int orderId=Integer.parseInt(request.getParameter("id_order"));

        try{
        approvedOrder.setIdEmployer(ServiceFactory.getInstance().getOrderService().findOrderInfo(orderId).getEmployerId());

        }catch(ServiceException exc){
            System.out.println("ошибка слоя сервис");
        }
        approvedOrder.setIdOrder(orderId);
        approvedOrder.setIdManager((Integer) request.getSession().getAttribute("userId"));
        approvedOrder.setIdAssignedCar(Integer.parseInt(request.getParameter("selected_car")));
        approvedOrder.setIdDriver(Integer.parseInt(request.getParameter("selected_car")));
        try{
            approvedOrder.setIdDriver(ServiceFactory.getInstance().getCarService().
                    findCar(Integer.parseInt(request.getParameter("selected_car"))).getDriverId());

        }catch (ServiceException exc){
            System.out.println("ошибка слоя сервис");
        }
        try{
            ServiceFactory.getInstance().getApprovedOrderService().addApprovedOrder(approvedOrder);
        }catch(ServiceException exc){
            System.out.println("ошибка слоя сервисов");
        }
       response.sendRedirect("jsp/ApproveOrderSuccess.jsp");
    }
}
