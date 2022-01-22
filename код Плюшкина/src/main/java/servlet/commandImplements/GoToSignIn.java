package servlet.commandImplements;

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

        StringBuffer URL=request.getRequestURL();
        String url=URL.toString()+"?command="+request.getParameter("command");
        request.getSession().setAttribute("url",url);
        System.out.println(url);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/SignInPage.jsp");
        dispatcher.forward(request,response);

    }
}
