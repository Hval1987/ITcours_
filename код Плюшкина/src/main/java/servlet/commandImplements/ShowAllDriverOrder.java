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

public class ShowAllDriverOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommandOrder");
        HashMap<ApprovedOrder, String> apprOrd=null;
        try{
            apprOrd=ServiceFactory.getInstance().getApprovedOrderService().findApprovedDriversOrder(
                    (Integer) request.getSession().getAttribute("userId"));
            //получаем мапу по id вошедшего водителя
        }catch(ServiceException exc){
            System.out.println(exc);

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
