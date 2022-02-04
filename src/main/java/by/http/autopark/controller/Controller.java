package by.http.autopark.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private final String COMMAND="command";
    private final CommandProvider provider=new CommandProvider();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

    }
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String command=request.getParameter(COMMAND);
        Command currentCommand=provider.getCommand(command);
        currentCommand.execute(request, response);

    }
}
