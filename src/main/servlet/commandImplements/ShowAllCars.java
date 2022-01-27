package servlet.commandImplements;

import autopark.bean.Car;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * in this class, we implement the command interface
 * to go to the jsp page with all cars. In case of an exception,
 * we forward to the error page
 */
public class ShowAllCars implements Command{

    private static final Logger logger= LoggerFactory.getLogger(ShowAllCars.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Car> list=null;

        try {

            list = ServiceFactory.getInstance().getCarService().getAllCars();
            logger.debug("получаем список всех автомобилей-> "+list);

       }catch(ServiceException exc){
            logger.warn(" ",exc);
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
       }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter("command");
        logger.debug("сохраняем в реквесте URL - "+url);
        HttpSession session=request.getSession();
        session.setAttribute("url",url);
        request.setAttribute("allcars",list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/ShowAllCars.jsp");
        dispatcher.forward(request,response);

    }
}
