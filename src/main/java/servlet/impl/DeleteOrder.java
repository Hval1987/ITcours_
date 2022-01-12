package servlet.impl;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("сработал блок удаления");
        try {
            ServiceFactory.getInstance().getOrderService().deleteOrder(Integer.parseInt(request.getParameter("id")));
            System.out.println(request.getParameter("id"));
            response.sendRedirect("Controller?command=SHOW_ALL_ORDERS");
        }catch(ServiceException exc){
            System.out.println("ошибка слоя сервиса");
        }




    }
}
