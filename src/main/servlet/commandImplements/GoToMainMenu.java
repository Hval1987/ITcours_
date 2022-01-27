package servlet.commandImplements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * in this class we implement the command interface
 * to go to the jsp page of the main menu
 */

public class GoToMainMenu implements Command {
    private static final Logger logger= LoggerFactory.getLogger(GoToMainMenu.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String URL2=response.encodeURL(request.getContextPath());

        StringBuffer URL=request.getRequestURL();
        String url=URL.toString()+"?command="+request.getParameter("command")+"&message="+request.getParameter("message");
        HttpSession session=request.getSession();
        session.setAttribute("url",url);
        logger.debug("сохраняем в реквесте URL - "+url);

              RequestDispatcher requestDispatcher=request.getRequestDispatcher("jsp/MainMenuPage.jsp");
              requestDispatcher.forward(request,response);

          }
    }

