package servlet.commandImplements;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeStatusByDriver implements Command {
    private static final String DRIVER_ID="id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idApproveOrd=Integer.parseInt(request.getParameter(DRIVER_ID));
        try {
            ServiceFactory.getInstance().getApprovedOrderService().ChangeOrderAsComplited(idApproveOrd);
        }catch(ServiceException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);
        }
        response.sendRedirect("Controller?command=SHOW_ALL_DRIVER'S_ORDERS");








    }
}
