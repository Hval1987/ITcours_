package servlet.commandImplements;

import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommandUser");
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsp/CommandUser.jsp");
        requestDispatcher.forward(request,response);
    }
}
