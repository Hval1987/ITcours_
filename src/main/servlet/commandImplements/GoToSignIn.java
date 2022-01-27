package servlet.commandImplements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * in this class we implement the command interface to go to the authorization page
 */

public class GoToSignIn implements Command {

    private static final Logger logger= LoggerFactory.getLogger(GoToSignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        StringBuffer URL=request.getRequestURL();
        String url=URL.toString()+"?command="+request.getParameter("command");
        request.getSession().setAttribute("url",url);
        logger.debug("сохраняем в реквесте URL - "+url);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/SignInPage.jsp");
        dispatcher.forward(request,response);

    }
}
