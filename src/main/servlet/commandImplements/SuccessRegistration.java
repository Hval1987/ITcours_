package servlet.commandImplements;

import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class, we implement the command interface
 * to output information about successful user registration
 */

public class SuccessRegistration implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("jsp/SuccessRegistration.jsp").forward(request,response);
    }
}
