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
 * to delete an instance of the car class.
 * in case of an error, we send it to the error page
 */
public class DeleteCar implements Command {
    private final Logger logger= LoggerFactory.getLogger(DeleteCar.class.getName());
    private static final String ID_DELETED_CAR="id";
    private static final String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String x=request.getParameter(ID_DELETED_CAR);
        int tmpId=Integer.parseInt(request.getParameter(ID_DELETED_CAR));

        boolean requirement = false;
        try {
           requirement= ServiceFactory.getInstance().getCarService().deleteCarById(tmpId);

        }catch(ServiceException exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }

        if(requirement) {
            response.sendRedirect("Controller?command=SHOW_ALL_CARS");
        }
        else{
            request.getRequestDispatcher("jsp/MessageErrorDelete.jsp").forward(request,response);

        }

    }
}
