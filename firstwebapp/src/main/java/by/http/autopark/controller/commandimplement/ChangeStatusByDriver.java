package by.http.autopark.controller.commandimplement;

import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we implement the command interface
 * for transmitting information for changing the order status by the driver to the services layer.
 * in case of an error, we send it to the error page
 */
public class ChangeStatusByDriver implements Command {
    private final Logger logger= LoggerFactory.getLogger(ChangeStatusByDriver.class.getName());
    private static final String DRIVER_ID="id";
    private static final String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idApproveOrd=Integer.parseInt(request.getParameter(DRIVER_ID));
        try {
            ServiceFactory.getInstance().getApprovedOrderService().ChangeOrderAsComplited(idApproveOrd);
        }catch(ServiceException exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        response.sendRedirect("Controller?command=SHOW_ALL_DRIVER'S_ORDERS");

    }
}
