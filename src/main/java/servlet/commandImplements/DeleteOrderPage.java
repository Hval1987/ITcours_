package servlet.commandImplements;

import autopark.bean.Order;
import autopark.dao.DAOFactory;
import autopark.dao.exception.DAOException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteOrderPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Order> orders= null;

        try {
            orders = DAOFactory.getInstance().getDAOOrder().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter("command");
        System.out.println(url);
        HttpSession session=request.getSession();
        session.setAttribute("url",url);
        request.setAttribute("order",orders);
        request.getRequestDispatcher( "jsp/DeleteOrderPage.jsp").forward(request,response);

    }
}
