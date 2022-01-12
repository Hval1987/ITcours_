package servlet.impl;

import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSignIn implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("singin");
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/SignInPage.jsp");
        dispatcher.forward(request,response);

    }
}
