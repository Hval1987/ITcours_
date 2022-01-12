package servlet.impl;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCar implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("!!!!!!!!!!!");
        System.out.println(request.getParameter("id"));
        int tmpId=Integer.parseInt(request.getParameter("id"));
        System.out.println(tmpId);
        try {
            ServiceFactory.getInstance().getCarService().deleteCarById(tmpId);

        }catch(ServiceException exc){
            System.out.println("ошибка сллоя сервисов");

        }
        System.out.println("удаление завершено");
        response.sendRedirect("Controller?command=SHOW_ALL_CARS");

    }
}
