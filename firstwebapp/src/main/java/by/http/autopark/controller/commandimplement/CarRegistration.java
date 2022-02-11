package by.http.autopark.controller.commandimplement;

import by.http.autopark.bean.Car;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.EntryCarInfoException;
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
 * to transfer an instance of the car class to the services layer for registration
 * in case of an error, we send it to the error page or indicate an error.
 */
public class CarRegistration implements Command {
    private final Logger logger= LoggerFactory.getLogger(CarRegistration.class.getName());

    private static final String MESSAGE="message";
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

            ServiceFactory.getInstance().getCarService().addCar(car);

            response.sendRedirect("Controller?command=SHOW_ALL_CARS");

        }catch (EntryCarInfoException exc){
            logger.error("ошибка ввода информации",exc);
            request.setAttribute(MESSAGE,exc.getMessage());
            request.getRequestDispatcher("Controller?command=GO_TO_ADD_CAR_PAGE").forward(request, response);

        }
        catch (ServiceException  exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE,"Извините, но в слое Service возникла ошибка");
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }

    }
}
