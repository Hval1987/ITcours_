package by.http.autopark.controller.commandImplements;

import by.http.autopark.bean.Order;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.DataErrorException;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we implement the command interface
 * for creating an instance of the order class to transfer it to the services layer for registration.
 * in case of an error, we send it to the error page
 */
public class RegistrationOrder implements Command {

    private static final String TYPE_OF_CAR="type_of_car";
    private static final String DATE_OF_ORDER="date";
    private static final String ROLE_ID="roleId";
    private static final String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Order order=new Order();
        order.setTransportType(request.getParameter(TYPE_OF_CAR));
        order.setDate(request.getParameter(DATE_OF_ORDER));
        order.setEmployerId((Integer)request.getSession().getAttribute(ROLE_ID));

        boolean flag=false;

        try {
                flag=ServiceFactory.getInstance().getOrderService().createOrder(order);
        } catch (ServiceException | DataErrorException exc) {
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
            if (flag) {

                response.sendRedirect("Controller?command=SHOW_ALL_ORDERS");
            }
            else{
                request.setAttribute(MESSAGE,DBResourceManager.getInstance().getMessageValue("message.data.exception"));
                request.getRequestDispatcher("jsp/AddOrderPage.jsp").forward(request,response);

            }
    }
}
