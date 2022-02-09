package by.http.autopark.controller.commandImplements;

import by.http.autopark.bean.User;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddCar implements Command {
    private final static String DRIVER="driver";
    private final static String LIST_OF_DRIVERS="listDrivers";
    private final static String MESSAGE="message";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<User>  list=ServiceFactory.getInstance().getUserService().findUsersByRole(DRIVER);
            request.setAttribute(LIST_OF_DRIVERS,
                                               ServiceFactory.getInstance().getUserService().findUsersByRole(DRIVER));

            request.getRequestDispatcher("jsp/AddCarPage.jsp").forward(request, response);
        }catch (ServiceException exc){
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }

    }
}
