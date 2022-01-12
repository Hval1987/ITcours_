package servlet.impl;

import autopark.dao.exception.DAOException;
import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainMenu implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        System.out.println("!@@@@@@@@@@");
//              request.setAttribute("Success","вы выполнили вход в систему");
              RequestDispatcher requestDispatcher=request.getRequestDispatcher("jsp/MainMenuPage.jsp");
              requestDispatcher.forward(request,response);


          }
    }

