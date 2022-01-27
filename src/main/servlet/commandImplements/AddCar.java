package servlet.commandImplements;

import autopark.bean.User;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddCar implements Command {
    private final static String DRIVER="driver";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<User>  list=ServiceFactory.getInstance().getUserService().findUsersByRole(DRIVER);
            request.setAttribute("listDrivers",
                                               ServiceFactory.getInstance().getUserService().findUsersByRole("driver"));

            request.getRequestDispatcher("jsp/AddCarPage.jsp").forward(request, response);
        }catch (ServiceException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }

    }
}
