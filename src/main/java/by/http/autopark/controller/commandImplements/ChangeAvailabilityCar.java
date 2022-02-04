package by.http.autopark.controller.commandImplements;

import by.http.autopark.bean.Car;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * in this class, we implement the command interface
 * to go to the jsp page with all cars, where we can select
 * a car to change the status. in case of an exception,
 * we forward to the error page
 */

public class ChangeAvailabilityCar implements Command {
    private static final String MESSAGE="message";
    private static final String COMMAND="command";
    private static final String ALL_CARS="allcars";
    private static final String URL="url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       List<Car> list= new ArrayList<>();
        try {

            list=ServiceFactory.getInstance().getCarService().getAllCars();
        }catch(ServiceException exc){
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);
        HttpSession session=request.getSession();
        session.setAttribute(URL,url);

        request.setAttribute(ALL_CARS,list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/ChangeAvailabilityCarPage.jsp");
        dispatcher.forward(request,response);


    }
}
