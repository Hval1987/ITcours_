package servlet.impl;

import autopark.bean.Order;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Order order=new Order();
        order.setTransportType(request.getParameter("type_of_car"));
        order.setDate(request.getParameter("date"));

        order.setEmployerId((Integer)request.getSession().getAttribute("roleId"));
        System.out.println("регистрация заказа-"+order);



        if(order.getDate()!=""&&
                order.getEmployerId()!=0&&
                      order.getTransportType()!="") {
            try {
                ServiceFactory.getInstance().getOrderService().createOrder(order);
                System.out.println("hglkhgisrhgs");
                response.sendRedirect("Controller?command=SHOW_ALL_ORDERS");
            } catch (ServiceException exc) {
                System.out.println("ошибка слоя сервис!");
            }
        }
        else{
            request.setAttribute("Unsuccess","введите корректные данные");
            request.getRequestDispatcher("RegistrationOrderPage.jsp").forward(request,response);
        }

        System.out.println("отработала регистрация");
    }
}
