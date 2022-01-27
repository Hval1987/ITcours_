package servlet.commandImplements;

import autopark.bean.ApprovedOrder;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we implement the command  interface
 * for transfer an instance of an approvedorder to the service layer,
 * in case of an error, we forward it to the error page or indicate an error
 * on the approval page
 */

public class AddApprovedOrder implements Command {
    private static final String STATUS="IN_PROCESS";
    private static final String USER_ID="userId";
    private static final String ORDER_ID="id_order";
    private static final String SELECTED_CAR="selected_car";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ApprovedOrder approvedOrder=new ApprovedOrder();
        approvedOrder.setStatus(STATUS);
        approvedOrder.setIdManager((Integer) request.getSession().getAttribute(USER_ID));
        int orderId=Integer.parseInt(request.getParameter(ORDER_ID));

        try{
        approvedOrder.setIdEmployer(ServiceFactory.getInstance().getOrderService().findOrderInfo(orderId).getEmployerId());

        }catch(ServiceException exc){
            System.out.println("ошибка слоя сервис");
        }
        approvedOrder.setIdOrder(orderId);
        approvedOrder.setIdManager((Integer) request.getSession().getAttribute(USER_ID));
        approvedOrder.setIdAssignedCar(Integer.parseInt(request.getParameter(SELECTED_CAR)));
        approvedOrder.setIdDriver(Integer.parseInt(request.getParameter(SELECTED_CAR)));
        try{
            approvedOrder.setIdDriver(ServiceFactory.getInstance().getCarService().
                    findCar(Integer.parseInt(request.getParameter(SELECTED_CAR))).getDriverId());

        }catch (ServiceException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        try{
            ServiceFactory.getInstance().getApprovedOrderService().addApprovedOrder(approvedOrder);
        }catch(ServiceException | DataErrorException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
       response.sendRedirect("jsp/ApproveOrderSuccess.jsp");
    }
}
