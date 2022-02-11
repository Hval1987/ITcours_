package by.http.autopark.controller.commandimplement;

import by.http.autopark.bean.Car;

import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ShowAllCars implements Command {


    private static final String MESSAGE="message";
    private static final String COMMAND="command";
    private static final String URL="url";
    private static final String ALL_CARS="allcars";

    private  final Logger logger= LoggerFactory.getLogger(ShowAllCars.class.getName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Car> list=null;

        try {

            list = ServiceFactory.getInstance().getCarService().getAllCars();
            logger.debug("получаем список всех автомобилей-> "+list);

       }catch(ServiceException exc){
            logger.error("ошибка слоя сервисов",exc);
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
       }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);
        logger.debug("сохраняем в реквесте URL - "+url);
        HttpSession session=request.getSession();
        session.setAttribute(URL,url);
        request.setAttribute(ALL_CARS,list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/ShowAllCars.jsp");
        dispatcher.forward(request,response);

    }
}
