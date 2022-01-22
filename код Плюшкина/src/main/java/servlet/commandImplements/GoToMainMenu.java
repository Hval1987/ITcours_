package servlet.commandImplements;

import servlet.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToMainMenu implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String URL2=response.encodeURL(request.getContextPath());
        System.out.println(request.getContextPath());
        System.out.println(URL2);


        StringBuffer URL=request.getRequestURL();
        String url=URL.toString()+"?command="+request.getParameter("command")+"&message="+request.getParameter("message");
        System.out.println(url);
        HttpSession session=request.getSession();
        session.setAttribute("url",url);

//              request.setAttribute("Success","вы выполнили вход в систему");
              RequestDispatcher requestDispatcher=request.getRequestDispatcher("jsp/MainMenuPage.jsp");
              requestDispatcher.forward(request,response);


          }
    }

