package by.http.autopark.controller.commandimplement;

import by.http.autopark.bean.Car;
import by.http.autopark.bean.Order;
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
import java.util.List;

/**
 * in this class, we implement the command interface
 * to transfer an instance of an approved order to the service level,
 * in case of an error, we send it to the error page or indicate an error
 * on the approval page
 *
 */

public class ApproveCertainOrder implements Command {
    private final Logger logger= LoggerFactory.getLogger(ApproveCertainOrder.class.getName());
    public static final String MESSAGE="message";
    public static final String ORDER="order";
    public static final String CARS="cars";
    public static final String ID="id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id=Integer.parseInt(request.getParameter(ID));
        Order order=null;
        List<Car> cars=null;
        try{
            cars=ServiceFactory.getInstance().getApprovedOrderService().ChooseFreeCar( id);
            order= ServiceFactory.getInstance().getOrderService().findOrderInfo(id);
        }catch (ServiceException exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        request.setAttribute(ORDER,order);
        request.setAttribute(CARS,cars);

        request.getRequestDispatcher("jsp/ApproveCertainOrder.jsp").forward(request,response);

    }
}
