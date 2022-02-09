package by.http.autopark.controller.commandImplements;

import by.http.autopark.controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String COMMAND="command";
    private static final String URL="url";
    private static final String MESSAGE="message";

    private static final Logger logger= LoggerFactory.getLogger(GoToMainMenu.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        StringBuffer urlTmp=request.getRequestURL();
        String url=urlTmp.toString()+"?command="+request.getParameter(COMMAND)+"&message="+request.getParameter(MESSAGE);
        HttpSession session=request.getSession();
        session.setAttribute(URL,url);
        logger.debug("сохраняем в реквесте URL - "+url);

              RequestDispatcher requestDispatcher=request.getRequestDispatcher("jsp/MainMenuPage.jsp");
              requestDispatcher.forward(request,response);

          }
    }

