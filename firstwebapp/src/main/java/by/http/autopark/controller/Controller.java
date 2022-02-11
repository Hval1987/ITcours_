package by.http.autopark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class provides a "connection" between the user and the system.
 * Monitors and directs commands from the user to the appropriate servlets
 */

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private final Logger logger= LoggerFactory.getLogger(Controller.class.getName());

    private final String COMMAND="command";
    private final CommandProvider provider=new CommandProvider();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

    }
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        /**
         * in this condition, we check whether cookies are disabled
         */
        if(request.getCookies()==null){
            logger.warn("куки отключены!");

            RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/CookiesDisabled.jsp");

            dispatcher.forward(request,response);
            return;

        }
        String command=request.getParameter(COMMAND);
        Command currentCommand=provider.getCommand(command);
        currentCommand.execute(request, response);


    }
}
