package by.http.autopark.controller.commandImplements;

import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAvailability implements Command {
    private final static String CAR_ID="id";
    private static final String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int tmpIdCar=Integer.parseInt(request.getParameter(CAR_ID));
        try{
            ServiceFactory.getInstance().getCarService().ChangeAvailAbilityCar(tmpIdCar);
        }catch (ServiceException exc){
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
        response.sendRedirect("Controller?command=CHANGE_AVAILABILITY_CAR");
    }
}
