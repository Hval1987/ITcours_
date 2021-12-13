package impl;

import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoToReg implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("hello new user");
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/registration.jsp");
        dispatcher.forward(request,response);


        System.out.println("отработал блок перехода на страницу регистрации через паттерн команда");

    }
}
