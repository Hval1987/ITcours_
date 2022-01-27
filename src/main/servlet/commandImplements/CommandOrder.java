package servlet.commandImplements;

import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsp/CommandForOrder.jsp");
        requestDispatcher.forward(request,response);

    }
}
