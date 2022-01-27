package servlet.commandImplements;

import autopark.bean.Order;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationOrder implements Command {
    private static final String TYPE_OF_CAR="type_of_car";
    private static final String DATE_OF_ORDER="date";
    private static final String ROLE_ID="roleId";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Order order=new Order();
        order.setTransportType(request.getParameter(TYPE_OF_CAR));
        order.setDate(request.getParameter(DATE_OF_ORDER));

        order.setEmployerId((Integer)request.getSession().getAttribute(ROLE_ID));
        System.out.println("регистрация заказа-"+order);

        boolean flag=false;

        try {
                flag=ServiceFactory.getInstance().getOrderService().createOrder(order);
        } catch (ServiceException | DataErrorException exc) {
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
            if (flag) {

                response.sendRedirect("Controller?command=SHOW_ALL_ORDERS");
            }
            else{
                request.setAttribute("Unsuccess","введите корректные данные");
                request.getRequestDispatcher("RegistrationOrderPage.jsp").forward(request,response);

            }




        System.out.println("отработала регистрация");
    }
}
