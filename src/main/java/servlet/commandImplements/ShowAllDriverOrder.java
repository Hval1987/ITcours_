package servlet.commandImplements;

import autopark.bean.ApprovedOrder;
import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * in this class, we implement the command interface
 * to go to the jsp page with all orders for a specific driver.
 * In case of an exception, we forward to the error page
 */

public class ShowAllDriverOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<ApprovedOrder, String> apprOrd=null;
        try{
            apprOrd=ServiceFactory.getInstance().getApprovedOrderService().findApprovedDriversOrder(
                    (Integer) request.getSession().getAttribute("userId"));

        }catch(ServiceException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter("command");
        System.out.println(url);
        HttpSession session=request.getSession();
        session.setAttribute("url",url);
        request.setAttribute("order",apprOrd);

        request.getRequestDispatcher( "jsp/ShowAllDriverOrderPage.jsp").forward(request,response);
    }
}
