package servlet.impl;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCar implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setAttribute("listDrivers",
                    ServiceFactory.getInstance().getUserService().findUsersByRole("driver"));

            request.getRequestDispatcher("jsp/AddCarPage.jsp").forward(request, response);
        }catch (ServiceException exc){
            System.out.println("ошибка слоя сервисов");
        }

    }
}
