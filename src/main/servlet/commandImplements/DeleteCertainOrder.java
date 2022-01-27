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
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        response.sendRedirect("Controller?command=GO_TO_DELETE_ORDERS_PAGE");
    }
}
