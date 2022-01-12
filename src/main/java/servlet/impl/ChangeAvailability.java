package servlet.impl;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAvailability implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int tmpIdCar=Integer.parseInt(request.getParameter("id"));
        System.out.println(tmpIdCar+" id car avlbl");
        try{
            ServiceFactory.getInstance().getCarService().ChangeAvailAbilityCar(tmpIdCar);
        }catch (ServiceException exc){
            System.out.println(" ошибка слоя  Service");

        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");

        response.sendRedirect("Controller?command=CHANGE_AVAILABILITY_CAR");




    }
}
