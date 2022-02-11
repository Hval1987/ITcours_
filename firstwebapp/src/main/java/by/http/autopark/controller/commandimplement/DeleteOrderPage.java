package by.http.autopark.controller.commandimplement;

import by.http.autopark.bean.Order;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * in this class we implement the command interface
 * where we go to a page with all orders to select a specific one to delete
 * in case of an error we send it to the error page
 */

public class DeleteOrderPage implements Command {

    private final Logger logger= LoggerFactory.getLogger(DeleteOrderPage.class.getName());

    private static final String MESSAGE="message";
    private static final String COMMAND="command";
    private static final String URL="url";
    private static final String ORDER="order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Order> orders= null;

        try {
            orders = ServiceFactory.getInstance().getOrderService().getAll();
        } catch (ServiceException e) {
            logger.error("ошибка слоя сервисов",e);
            request.setAttribute(MESSAGE,"Извините, но в слое Service возникла ошибка");
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);

        HttpSession session=request.getSession();
        session.setAttribute(URL,url);
        request.setAttribute(ORDER,orders);
        request.getRequestDispatcher( "jsp/DeleteOrderPage.jsp").forward(request,response);

    }
}
