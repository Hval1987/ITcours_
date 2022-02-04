package by.http.autopark.controller.commandImplements;

import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;
import org.intellij.lang.annotations.RegExp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we implement the command interface
 * to delete an instance of the order class.
 * in case of an error, we send it to the error page
 */
public class DeleteCertainOrder implements Command {
    private static final String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            ServiceFactory.getInstance().getOrderService().deleteOrder(Integer.parseInt(request.getParameter("id")));
        }catch (ServiceException exc){
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.exception.order.process"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
        response.sendRedirect("Controller?command=GO_TO_DELETE_ORDERS_PAGE");
    }
}
