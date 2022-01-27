package servlet.commandImplements;

import autopark.servise.ServiceFactory;
import autopark.servise.exception.ServiceException;
import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCar implements Command {
    private static final String ID_DELETED_CAR="id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String x=request.getParameter(ID_DELETED_CAR);
        int tmpId=Integer.parseInt(request.getParameter(ID_DELETED_CAR));

        boolean flag = false;
        try {
           flag= ServiceFactory.getInstance().getCarService().deleteCarById(tmpId);

        }catch(ServiceException exc){
            request.setAttribute("message",exc.getMessage());
            request.getRequestDispatcher("jsp/ExceptionPage.jsp").forward(request, response);

        }

        if(flag) {
            response.sendRedirect("Controller?command=SHOW_ALL_CARS");
        }
        else{
            //go to message
            System.out.println("delete error");
            request.getRequestDispatcher("jsp/MessageErrorDelete.jsp").forward(request,response);

        }

    }
}
