package servlet.commandImplements;

import autopark.bean.Car;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.EntryCarInfoException;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarRegistration implements Command {


    private static final String DRIVER_NAME="driver_name";
    private static final String REGISTRATION_NUMBER="regNumber";
    private static final String TYPE_OF_CAR="typeOfCar";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
          Car car=new Car();

        try {

            car.setDriverId((ServiceFactory.getInstance().getUserService().findUserByName(request.getParameter(DRIVER_NAME)).
                               getId()));
            car.setRegNumber(request.getParameter(REGISTRATION_NUMBER));

            car.setTransportType(request.getParameter(TYPE_OF_CAR));
            //add car..


            ServiceFactory.getInstance().getCarService().addCar(car);
            response.sendRedirect("Controller?command=SHOW_ALL_CARS");

        }catch (EntryCarInfoException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("Controller?command=GO_TO_ADD_CAR_PAGE").forward(request, response);

        }
        catch (ServiceException  exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);


        }

    }
}
