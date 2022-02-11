package by.http.autopark.controller.commandimplement;

import by.http.autopark.bean.Order;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.controller.Command;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * in this class, we implement the command interface
 * to go to the jsp page with all orders.
 * in case of an error, we send it to the error page
 */
public class ShowAllOrders implements Command {

    private  final Logger logger= LoggerFactory.getLogger(ShowAllOrders.class.getName());
    public final static String COMMAND="command";
    private static final String MESSAGE="message";
    public final static String URL="url";
    public final static String ORDER="order";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Order> orders= null;
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);
        HttpSession session=request.getSession();
        session.setAttribute(URL,url);
        try {
            orders= ServiceFactory.getInstance().getOrderService().getAll();
        } catch (ServiceException exc) {
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        request.setAttribute(ORDER,orders);
        request.getRequestDispatcher( "jsp/ShowAllOrderPage.jsp").forward(request,response);
    }
}
