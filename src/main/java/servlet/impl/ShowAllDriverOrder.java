package servlet.impl;

import autopark.bean.ApprovedOrder;
import autopark.bean.Order;
import autopark.bean.User;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShowAllDriverOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommandOrder");

        List<ApprovedOrder> orders= null;
        HashMap<ApprovedOrder, String> apprOrd=null;
        try{
            apprOrd=ServiceFactory.getInstance().getApprovedOrderService().findApprovedDriversOrder(
                    (Integer) request.getSession().getAttribute("userId"));
            //получаем мапу по id вошедшего водителя
        }catch(ServiceException exc){
            System.out.println(exc);

        }

        System.out.println(apprOrd);
        request.setAttribute("order",apprOrd);

        System.out.println("show all orders");
        request.getRequestDispatcher( "jsp/ShowAllDriverOrderPage.jsp").forward(request,response);
    }
}
