package by.http.autopark.controller.commandImplements;

import by.http.autopark.bean.ApprovedOrder;
import by.http.autopark.dao.DBResourceManager;
import by.http.autopark.servise.ServiceFactory;
import by.http.autopark.servise.exception.ServiceException;
import by.http.autopark.controller.Command;

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

    private static final String MESSAGE="message";
    private static final String USER_ID="userId";
    private static final String COMMAND="command";
    private static final String URL="url";
    private static final String ORDER="order";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<ApprovedOrder, String> apprOrd=null;
        try{
            apprOrd=ServiceFactory.getInstance().getApprovedOrderService().findApprovedDriversOrder(
                    (Integer) request.getSession().getAttribute(USER_ID));

        }catch(ServiceException exc){
            request.setAttribute(MESSAGE, DBResourceManager.getInstance().getMessageValue("message.service.exception"));
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }
        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);
        HttpSession session=request.getSession();
        session.setAttribute(URL,url);
        request.setAttribute(ORDER,apprOrd);

        request.getRequestDispatcher( "jsp/ShowAllDriverOrderPage.jsp").forward(request,response);
    }
}
