package servlet.commandImplements;



import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowAllUsers implements Command{


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("show info");
//        List<Car> list = new DAOCarImpl().getAllCars();
//        request.setAttribute("allcars",list);
//        System.out.println("add");
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/ShowAllCars.jsp");
        dispatcher.forward(request,response);

    }
}
