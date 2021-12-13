package impl;

import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserSignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("welcome back");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        System.out.println("welcome back, "+ login);
        PrintWriter out = response.getWriter();
        out.println("<h1>welcome back, "+login+"<br/> Info about user->> <br/> login- " + login + "<br/> password- " + password +"</h1>");
        System.out.println("сработал блок  входа в систему ");
    }
}

