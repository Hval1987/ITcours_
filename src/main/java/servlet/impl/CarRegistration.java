package servlet.impl;

import autopark.bean.Car;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarRegistration implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Car car=new Car();

        try {
            car.setDriverId((ServiceFactory.getInstance().getUserService().
                    authorization(request.getParameter("driver_name")).
                               getId()));

            car.setRegNumber(request.getParameter("regNumber"));

            car.setTransportType(request.getParameter("typeOfCar"));

            //add car..
            ServiceFactory.getInstance().getCarService().addCar(car);
            response.sendRedirect("Controller?command=SHOW_ALL_CARS");


        }catch (ServiceException exc){
            System.out.println("ошибка слоя сервисов");

        }

    }
}
