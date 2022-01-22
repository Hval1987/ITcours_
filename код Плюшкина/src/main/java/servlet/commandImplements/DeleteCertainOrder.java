package servlet.commandImplements;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCertainOrder implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        try{
            ServiceFactory.getInstance().getOrderService().deleteOrder(Integer.parseInt(request.getParameter("id")));
        }catch (ServiceException exc){

            System.out.println("ошибка слоя сервисов");
        }
        response.sendRedirect("Controller?command=GO_TO_DELETE_ORDERS_PAGE");
        System.out.println("редирект ");
    }
}
