package servlet.impl;

import autopark.bean.Car;
import autopark.bean.Order;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApproveCertainOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id=Integer.parseInt(request.getParameter("id"));
        Order order=null;
        List<Car> cars=null;
        try{
            cars=ServiceFactory.getInstance().getCarService().getAllCars();
            order= ServiceFactory.getInstance().getOrderService().findOrderInfo(id);
        }catch (ServiceException exc){
            System.out.println("Ошибка слоя сервисов");
        }
        request.setAttribute("order",order);
        request.setAttribute("cars",cars);

        request.getRequestDispatcher("jsp/ApproveCertainOrder.jsp").forward(request,response);



    }
}
