package servlet.impl;

import autopark.bean.Order;
import autopark.bean.User;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteUsersOrderPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommandOrder");

        List<Order> orders= null;
        try {
            orders = DAOFactory.getInstance().getDAOOrder().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        System.out.println(orders);
        request.setAttribute("order",orders);
        //request.setAttribute("listUser",listUser);
        System.out.println("show all orders");
        request.getRequestDispatcher( "jsp/DeleteUserOrder.jsp").forward(request,response);
    }
}
