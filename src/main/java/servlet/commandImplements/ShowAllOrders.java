package servlet.commandImplements;

import autopark.bean.Order;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAllOrders")
public class ShowAllOrders implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommandOrder");
        List<Order> orders= null;
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter("command");
        System.out.println(url);
        HttpSession session=request.getSession();
        session.setAttribute("url",url);
        try {
            orders = DAOFactory.getInstance().getDAOOrder().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        System.out.println(orders);
        request.setAttribute("order",orders);
        System.out.println("show all orders");
        request.getRequestDispatcher( "jsp/ShowAllOrderPage.jsp").forward(request,response);
    }
}
