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

public class ChangeAvailability implements Command {
    private final Logger logger= LoggerFactory.getLogger(ChangeAvailability.class.getName());
    private final static String CAR_ID="id";
    private static final String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int tmpIdCar=Integer.parseInt(request.getParameter(CAR_ID));
        try{
            ServiceFactory.getInstance().getCarService().ChangeAvailAbilityCar(tmpIdCar);
        }catch (ServiceException exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
        response.sendRedirect("Controller?command=CHANGE_AVAILABILITY_CAR");
    }
}
