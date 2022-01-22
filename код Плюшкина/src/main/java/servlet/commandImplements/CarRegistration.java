package servlet.commandImplements;

import autopark.bean.Car;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.DataErrorException;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

public class CarRegistration implements Command {
    private static final String DRIVER_NAME="driver_name";
    private static final String REGISTRATION_NUMBER="regNumber";
    private static final String TYPE_OF_CAR="typeOfCar";
    @Inject
    Validator validator;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
          Car car=new Car();

        try {

            car.setDriverId((ServiceFactory.getInstance().getUserService().findUserByLogin(request.getParameter(DRIVER_NAME)).
                               getId()));

            car.setRegNumber(request.getParameter(REGISTRATION_NUMBER));

            car.setTransportType(request.getParameter(TYPE_OF_CAR));
            //add car..


            ServiceFactory.getInstance().getCarService().addCar(car);
            response.sendRedirect("Controller?command=SHOW_ALL_CARS");


        }catch (ServiceException | DataErrorException exc){
            System.out.println("ошибка слоя сервисов");

        }

    }
}
