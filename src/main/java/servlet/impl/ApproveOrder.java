package servlet.impl;

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
import java.util.List;

public class ApproveOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Order> orders= null;
        try {
            orders = DAOFactory.getInstance().getDAOOrder().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        System.out.println(orders);
        request.setAttribute("order",orders);


        System.out.println("approve orders");
        request.getRequestDispatcher( "jsp/ApproveOrderPage.jsp").forward(request,response);





    }
}
