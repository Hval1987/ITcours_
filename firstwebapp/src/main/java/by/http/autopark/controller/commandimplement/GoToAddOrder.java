package by.http.autopark.controller.commandimplement;

import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class , we implement the command interface
 * to go to the order generation page .
 * in case of an error, we send it to the error page
 */
public class GoToAddOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/jsp.AddOrderPage.jsp").forward(request,response);
    }
}
