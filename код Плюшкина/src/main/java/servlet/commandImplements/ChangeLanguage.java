package servlet.commandImplements;

import servlet.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command {
    private static final String LOCALE="locale";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("change lang block");
        HttpSession session=request.getSession();
        String lang=request.getParameter(LOCALE);
        session.setAttribute("local",lang);

        String url= (String) request.getSession().getAttribute("url");
        System.out.println("!!! "+url);
        response.sendRedirect(url);

    }
}
