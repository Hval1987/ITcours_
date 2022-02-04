package by.http.autopark.controller.commandImplements;

import by.http.autopark.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we implement a command to go to the error page
 * in case of an incorrect command for the controller
 */

public class NoCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("jsp/NoCommandPage.jsp").forward(request,response);
    }
}
