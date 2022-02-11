package by.http.autopark.controller.commandimplement;

import by.http.autopark.bean.ApprovedOrder;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.DataErrorException;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger logger= LoggerFactory.getLogger(AddApprovedOrder.class.getName());
    private static final String STATUS="IN_PROCESS";
    private static final String USER_ID="userId";
    private static final String ORDER_ID="id_order";
    private static final String SELECTED_CAR="selected_car";
    private static final String MESSAGE="message";
    /**
     * here we get data from the request and form
     * an instance of the approved order object from them,
     * which we pass to the services layer.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        ApprovedOrder approvedOrder=new ApprovedOrder();
        approvedOrder.setStatus(STATUS);
        approvedOrder.setIdManager((Integer) request.getSession().getAttribute(USER_ID));
        int orderId=Integer.parseInt(request.getParameter(ORDER_ID));

        try{
        approvedOrder.setIdEmployer(ServiceFactory.getInstance().getOrderService().findOrderInfo(orderId).getEmployerId());
        approvedOrder.setIdOrder(orderId);
        approvedOrder.setIdManager((Integer) request.getSession().getAttribute(USER_ID));
        approvedOrder.setIdAssignedCar(Integer.parseInt(request.getParameter(SELECTED_CAR)));
        approvedOrder.setIdDriver(Integer.parseInt(request.getParameter(SELECTED_CAR)));
        approvedOrder.setIdDriver(ServiceFactory.getInstance().getCarService().
                    findCar(Integer.parseInt(request.getParameter(SELECTED_CAR))).getDriverId());

        ServiceFactory.getInstance().getApprovedOrderService().addApprovedOrder(approvedOrder);

        }catch(ServiceException  exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE,DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        catch (DataErrorException exc){
            request.setAttribute(MESSAGE,DBResourceManager.getInstance().getMessageValue("message.data.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
       response.sendRedirect("jsp/ApproveOrderSuccess.jsp");
    }
}
