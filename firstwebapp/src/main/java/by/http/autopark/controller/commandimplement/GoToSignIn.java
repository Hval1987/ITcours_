package by.http.autopark.controller.commandimplement;

import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class we implement the command interface to go to the authorization page
 */

public class GoToSignIn implements Command {

    private static final Logger logger= LoggerFactory.getLogger(GoToSignIn.class.getName());

    public static final String COMMAND="command";
    private static final String URL="url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND);
        request.getSession().setAttribute(URL,url);
        logger.debug("сохраняем в реквесте URL - "+url);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/SignInPage.jsp");
        dispatcher.forward(request,response);

    }
}
