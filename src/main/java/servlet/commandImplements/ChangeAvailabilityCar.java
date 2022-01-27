package servlet.commandImplements;

import autopark.bean.Car;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       List<Car> list= new ArrayList<>();

        try {

            list=ServiceFactory.getInstance().getCarService().getAllCars();
        }catch(ServiceException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter("command");
        System.out.println(url);
        HttpSession session=request.getSession();
        session.setAttribute("url",url);

        request.setAttribute("allcars",list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/ChangeAvailabilityCarPage.jsp");
        dispatcher.forward(request,response);


    }
}
