package by.http.autopark.controller.commandimplement;

import by.http.autopark.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class we implement the command interface to go to the jsp registration page
 */

public class GoToReg implements Command {
    private static final String URL="url";
    private static final String COMMAND="command";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);
        request.getSession().setAttribute(URL,url);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/RegistrationPage.jsp");
        dispatcher.forward(request,response);

    }
}
